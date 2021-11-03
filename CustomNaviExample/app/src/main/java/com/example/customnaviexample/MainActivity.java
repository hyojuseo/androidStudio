package com.example.customnaviexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

    }

    //이곳에서 드로우했을때의 상태값
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        //슬라이드 했을때
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }
        //오픈완료됐을때
        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }
        //슬라이드 메뉴가 닫혔을때
        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }
        //상태가 변경됐을때
        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}