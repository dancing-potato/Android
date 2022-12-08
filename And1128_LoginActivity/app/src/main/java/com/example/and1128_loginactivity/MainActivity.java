package com.example.and1128_loginactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메인화면의 로그인 버튼 클릭 시 LoginActivity 로 전환
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 0);
                // => onActivityResult() 메서드 오버라이딩 필수!
            }
        });

    }

    // 전환된 액티비티 종료 시 응답을 위해 자동으로 호출되는 메서드 onActivityResult() 오버라이딩
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case -1:
                Toast.makeText(this, "로그인 취소", Toast.LENGTH_SHORT).show();
                break;
            case 1: // 로그인 성공
                // 로그인 버튼을 "로그아웃" 으로 변경
                btnLoginActivity.setText("로그아웃");
        }

    }
}