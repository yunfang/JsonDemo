package appjsondemo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import appjsondemo.mylibrary.KeyValue;
import appjsondemo.mylibrary.MapToJsonUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView id_tv = (TextView) findViewById(R.id.id_tv);

        Map<String, Object> map = new HashMap<String, Object>();

        List<String> list = new ArrayList<String>();
        Bean bean = new Bean();
        bean.setIndex(1);
        bean.setName("beanName");
        list.add("index0");
        list.add("index1");
        list.add("index2");

        map.put("list", list);
        map.put("Bean",bean);

        JSONObject jsonObject = MapToJsonUtils.Map2Json(map);
        System.out.println(jsonObject);
        id_tv.setText(jsonObject.toString());

    }
}
