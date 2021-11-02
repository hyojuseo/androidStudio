package com.example.naviexample;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.naviexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //오른쪽우측의 메일아이콘형태를 관리하는 구문
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = binding.drawerLayout; //메뉴바
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        //host가 될 fragment(content_main)을 불러온다.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        //NavController와 mAppBarConfiguration에 맞춰 ActionBar(title)를 설정해준다.
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //navigationView를 navController가 사용할 수 있도록 설정해준다.
        //Navigation의 item을 클릭시 fragment가 변경되도록 설정해주는 것.
        NavigationUI.setupWithNavController(navigationView, navController);

        //각 메뉴를 클릭했을때 해당 메뉴의 화면이 맞는지 Toast를 띄움
        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawer.closeDrawers();

                switch(item.getItemId()){
                    case R.id.nav_home:
                        showMsg("home");
                        return true;
                    case R.id.nav_gallery:
                        showMsg("gallery");
                        return true;
                    case R.id.nav_slideshow:
                        showMsg("slideshow");
                        return true;
                }
                return false;
            }
        });*/
    }

    private void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //hyoju
    //뒤로가기 버튼을 누르면 아래 메소드가 실행
    @Override
    public void onBackPressed(){   
        //뒤로가기를 눌렀을때.
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        //DrawerLayout이 열려있다면
        if(drawer.isDrawerOpen(GravityCompat.START)){
            //닫아주기
            drawer.closeDrawer(GravityCompat.START);
        } else{
            //DrawerLayout이 닫혀있다면
            //뒤로가기
            super.onBackPressed();
        }
    }

    //만들어진 템플릿 가져오기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //앱을 시작할때 옵션메뉴를 만들어둔다.
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //hyoju
    //옵션메뉴를 클릭했을 때.
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //해당되는
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //NavController가 mAppBarConfiguration을 통해 Up버튼 클릭시 수행할 action을 결정하도록 설정함
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //hyoju
    //각각 메뉴버튼을 클릭했을 때 이루어지는 액션을 여기서 실행(구버전)
    //@SuppressWarnings("StatementWithEmptyBody")
   /* @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this,"홈입니다",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_gallery:
                Toast.makeText(this,"갤러리입니다",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_slideshow:
                Toast.makeText(this,"슬라이드입니다",Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


}