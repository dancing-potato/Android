package com.example.and1110_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUrl = findViewById(R.id.etUrl);
        Button btnGo = findViewById(R.id.btnGo);
        Button btnBack = findViewById(R.id.btnBack);
        WebView webView = findViewById(R.id.webView);

        // 입력된 URL에 접속하여 페이지 내용을 WebView 위젯에 표시
        // WebViewClient 서브클래스의 인스턴스 생성
        MyWebViewClient myWebViewClient = new MyWebViewClient();

        // WebView 객체의 setWebViewClient() 메서드를 호출하여
        // WebViewClient 서브클래스 인스턴스 전달
        webView.setWebViewClient(myWebViewClient);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 입력된 URL 가져오기
                String url = etUrl.getText().toString();

                // 만약, URL이 입력되지 않았을 경우 오류 메시지 출력 및 커서 요청
                if(url.equals("")){
                    Toast.makeText(MainActivity.this, "URL 입력 필수!", Toast.LENGTH_SHORT).show();
                    etUrl.requestFocus();
                    return;
                }

                // WebView 객체의 loadUrl() 메서드를 호출하여
                // EditText로 부터 가져온 URL 정보 전달
                webView.loadUrl(url);
            }
        });

        btnBack.setOnClickListener(view -> webView.goBack());


    }

    // WebView 위젯 동작을 위해 WebViewClient 클래스를 상속받는 서브클래스 정의 - 내부클래스
    class MyWebViewClient extends WebViewClient {
        // shouldOverrideUrlLoading() 메서드 오버라이딩
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

}