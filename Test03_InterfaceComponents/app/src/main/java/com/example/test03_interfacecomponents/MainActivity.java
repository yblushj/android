package com.example.test03_interfacecomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] texts = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private int[] images = {
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < texts.length; i++){
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("text_view", texts[i]);
            showitem.put("image_view", images[i]);
            listitem.add(showitem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                listitem,
                R.layout.lists_items,
                new String[]{"text_view", "image_view"},
                new int[]{R.id.text_view, R.id.image_view});

        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, texts[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}