package com.example.and1122_activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// 액티비티 클래스 정의 시 반드시 AppCompatActivity 클래스 상속 필수!
public class SecondActivity extends AppCompatActivity {
    // 액티비티 클래스 사용을 위해서는 해당 액티비티가 생성될때
    // 자동으로 호출되는 onCreate() 메서드 오버라이딩 필수! => 자바의 main() 메서드와 유사
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 액티비티 클래스 하나당 화면을 구성하는 레이아웃(XML 파일) 하나를 연결
        // => 연결을 위해 setContentView() 메서드를 호출하여
        //    액티비티 실행 시 표시할 레이아웃(XML) 파일을 지정
        setContentView(R.layout.second_activity);

        Button btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
