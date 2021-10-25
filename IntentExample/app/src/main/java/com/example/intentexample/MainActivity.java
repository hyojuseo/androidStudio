package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test = findViewById(R.id.et_test);

        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = et_test.getText().toString(); //클릭시 str에 담아두지 않으면
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                intent.putExtra("str",str); //str은 실제로 들어가는 데이터
                                                    //subActivity로 쏜다.
                startActivity(intent);  //Activity 이동해주는 구문
            }
        });

    }
}
