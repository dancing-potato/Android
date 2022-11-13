package com.example.and1031_switch_togglebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch sw1 = findViewById(R.id.sw1);
        TextView tv = findViewById(R.id.tv);
        Switch swSub = findViewById(R.id.swSub);

        // Switch 이벤트 연결 (CheckBox 와 동일한 setOnCheckedChangeListener 사용)
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                Toast.makeText(MainActivity.this, isChecked + "", Toast.LENGTH_SHORT).show();
//                if(isChecked){
//                    // Wi-Fi 스위치가 켜지면 TextView(tv) 와 Switch(swSub) 표시하도록 설정
//                    tv.setVisibility(View.VISIBLE);
//                    swSub.setVisibility(View.VISIBLE);
//                } else {
//                    tv.setVisibility(View.GONE);
//                    swSub.setVisibility(View.GONE);
//                }

                // 위 로직을 if-else 없이 구현
                int result = isChecked ? View.VISIBLE : View.GONE;
                tv.setVisibility(result);
                swSub.setVisibility(result);

//                tv.setVisibility(isChecked ? View.VISIBLE : View.GONE);
//                swSub.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });


    }
}