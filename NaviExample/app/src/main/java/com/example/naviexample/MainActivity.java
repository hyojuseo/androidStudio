package com.example.naviexample;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //hyoju
    //각각 메뉴버튼을 클릭했을 때 이루어지는 액션을 여기서 실행
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.nav_home){
            //empty
        }else if(id == R.id.nav_gallery){
            //empty
        }else if(id == R.id.nav_slideshow){
            //empty
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}