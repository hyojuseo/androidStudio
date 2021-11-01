package com.example.listexample01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.list);

        List<String> data = new ArrayList<>();

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //this : 현재 액티비티
        list.setAdapter(adapter);   //연결됨

        data.add("test1");
        data.add("test2");
        data.add("test3");
        adapter.notifyDataSetChanged(); //이것을 해줘야 위 상태를 현재 저장을 하겠다 라는 의미.
    }
}