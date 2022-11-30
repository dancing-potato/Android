package com.example.and1122_three_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rGroup = findViewById(R.id.rGroup);
        Button btnNew = findViewById(R.id.btnNew);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // RadioButton에 따라 각각 다른 액티비티 전환
                int id = rGroup.getCheckedRadioButtonId();
                if(id == -1) return;

                Intent intent = null;

                switch (id){
                    case R.id.rbSecond:
                        intent = new Intent(MainActivity.this, SecondActivity.class);
//                        startActivity(intent);
                        break;
                    case R.id.rbThird:
                        intent = new Intent(MainActivity.this, ThirdActivity.class);
//                        startActivity(intent);
                        break;
                }

                startActivity(intent);
            }
        });


    }
}