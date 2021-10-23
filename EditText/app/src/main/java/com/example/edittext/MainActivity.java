package com.example.edittext;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_id;
    Button btn_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //onCreate : 어플을 처음실행했을때 실행되는 실행주기
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);   //main.xml의 et_id와 연결이 된 것.
        btn_test = findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_id.setText("테스트입니다");    //텍스트값을 써주고 싶을때 여기서 동적으로 사용하는 것
                                                //버튼을 눌렀을때 텍스트에 setText내용을 써넣게 됨.
            }
        });
    }
}