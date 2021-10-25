package com.example.image;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_test;
    Button btn_test;
    ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test = (EditText)findViewById(R.id.et_test);

        btn_test = (Button)findViewById(R.id.btn_test);
        btn_test.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,SubActivity.class);
            startActivity(intent);
        });

        test = (ImageView) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"히히",Toast.LENGTH_SHORT).show();    //원하는글씨를 적어 팝업으로 송출
                //getApplicationContext 는 메인엑티비티를 뜻함
            }
        });
    }
}