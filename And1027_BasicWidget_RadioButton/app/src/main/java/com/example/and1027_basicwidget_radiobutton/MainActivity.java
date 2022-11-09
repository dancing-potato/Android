package com.example.and1027_basicwidget_radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rGroup = findViewById(R.id.rGroup);
        RadioButton rb1 = findViewById(R.id.rb1);
        RadioButton rb2 = findViewById(R.id.rb2);

        Button btnOk = findViewById(R.id.btnOk);
        Button btnReset = findViewById(R.id.btnReset);

//        rb1.setChecked(true);
        // 복수개의 RadioButton 이 하나의 RadioGroup 으로 묶여있을 경우
        // 각각의 리스너를 별도로 연결하지 않고 RadioGroup 객체에만 연결하면 모두 이벤트 처리 가능
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT).show();
//                if(checkedId == R.id.rb1){
//                    Toast.makeText(MainActivity.this, "rb1", Toast.LENGTH_SHORT).show();
//                }

                switch (checkedId) {
                    case R.id.rb1:
                        Toast.makeText(MainActivity.this, "식사 가능!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb2:
                        Toast.makeText(MainActivity.this, "식사 불가능!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 선택된 RadioButton 에 대한 항목 가져와서 출력
                // => RadioGroup 객체의 getCheckedRadioButtonId() 메서드를 호출하면
                //    선택된 RadioButton 위젯에 대한 ID 가져올 수 잇음!
//                if(rGroup.getCheckedRadioButtonId() == R.id.rb1){
//                    Toast.makeText(MainActivity.this, "rb1", Toast.LENGTH_SHORT).show();
//                }
                switch (rGroup.getCheckedRadioButtonId()) {
                    case R.id.rb1:
                        Toast.makeText(MainActivity.this, "식사 가능!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb2:
                        Toast.makeText(MainActivity.this, "식사 불가능!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rb1.setChecked(false);
//                rb2.setChecked(false);

                // RadioGroup의 clearCheck() 메서드를 호출하여 그룹 전체를 초기화
                rGroup.clearCheck();
            }
        });







    }
}