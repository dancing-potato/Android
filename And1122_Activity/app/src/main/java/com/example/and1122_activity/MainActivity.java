package com.example.and1122_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNewActivity(View view){
        // 현재 액티비에서 새로운 액티비를 실행(전환)하기 위해서는
        // Intent 객체를 생성하여 전환할 액티비티 클래스를 지정하고
        // startActivity() 메서드를 호출하여 생성된 Intent 객체를 전달
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // ~~~ 에서, ~~~로
        // => 현재 객체 : MainActivity, 전환할 액티비티 : SecondActivity 클래스
        // => 주의! 새로운 액티비티로 전환하기 위해서는 해당 액티비티 클래스를
        //    반드시 AndroidManifests.xml 파일 내에 등록해야한다!
        startActivity(intent);
    }
}