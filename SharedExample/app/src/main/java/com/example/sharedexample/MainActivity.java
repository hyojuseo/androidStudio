package com.example.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

//앱을 실행했을 때 editText가 나오고 입력하고 종료하면
//앱을 벗어났을 때 입력된 값이 다시 켰을 때 저장되어있게 한다.
//단 임시저장이기때문에 앱을 삭제했을 때 사라진다.
public class MainActivity extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save = (EditText)findViewById(R.id.et_save);

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        //두번째인자는 꺼내오는 것
        String value = sharedPreferences.getString("str","");
        et_save.setText(value);
    }

    //앱을 뒤로가기하거나 종료했을때(액티비티를 벗어났을때 액티비티가 파괴됨.)
    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);

        //저장할때 항상 Editor를 불러와줘야 된다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = et_save.getText().toString();
        editor.putString("str",value);

        //실질적인 save를 완료하는 구문
        editor.commit();
    }
}