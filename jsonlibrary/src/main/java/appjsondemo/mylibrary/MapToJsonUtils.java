package appjsondemo.mylibrary;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouyunfang
 */
public class MapToJsonUtils {


    public static JSONObject Map2Json(Map<String, Object> map){
        JSONObject Jsonobject = new JSONObject();
        List<KeyValue> paramList = new ArrayList<KeyValue>();
        for (String key : map.keySet()) {
            paramList.add(new KeyValue(key, map.get(key)));
        }
        try {
            params2Json(Jsonobject, paramList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Jsonobject;
    }

    //生成JSONObject 对象
    private  static void params2Json(final JSONObject jsonObject, final List<KeyValue> paramList) throws JSONException {
        HashSet<String> arraySet = new HashSet<String>(paramList.size());
        LinkedHashMap<String, JSONArray> tempData = new LinkedHashMap<String, JSONArray>(paramList.size());
        for (int i = 0; i < paramList.size(); i++) {
            KeyValue kv = paramList.get(i);
            final String key = kv.key;
            if (TextUtils.isEmpty(key)) continue;

            JSONArray ja = null;
            if (tempData.containsKey(key)) {
                ja = tempData.get(key);
            } else {
                ja = new JSONArray();
                tempData.put(key, ja);
            }

            ja.put(RequestParamsHelper.parseJSONObject(kv.value));

            if (kv instanceof ArrayItem) {
                arraySet.add(key);
            }
        }

        for (Map.Entry<String, JSONArray> entry : tempData.entrySet()) {
            String key = entry.getKey();
            JSONArray ja = entry.getValue();
            if (ja.length() > 1 || arraySet.contains(key)) {
                jsonObject.put(key, ja);
            } else {
                Object value = ja.get(0);
                jsonObject.put(key, value);
            }
        }
    }



    private final static class ArrayItem extends KeyValue {
        public ArrayItem(String key, Object value) {
            super(key, value);
        }
    }
}
