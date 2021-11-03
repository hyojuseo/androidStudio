package com.example.webviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://www.z9star.co.kr/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        //인터넷들에 대한 셋팅. 자바스크립트의 부가적인 것을 허용해준다
        webView.getSettings().setJavaScriptEnabled(true);
        //특정 url주소를 튼다
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());


    }

    //다양한 안드로이드 키들을 입력했을때 동작을 지정해줄때 사용함
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //KEYCODE_BACK 뒤로가기. 뒤로가기를 눌렀을때. 뒤로갈 수 있게 되면
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack() ){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        //현재페이지의 url을 읽어올수있는 메서드(자주사용된다)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }
    }
}