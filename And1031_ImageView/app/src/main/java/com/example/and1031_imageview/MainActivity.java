package com.example.and1031_imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int toggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.iv);
        ImageButton iBtn = findViewById(R.id.iBtn);

        // ImageButton 클릭 시 ImageView 이미지 변경
        iBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.btn_star_big_on);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle == 0){
                    Toast.makeText(MainActivity.this, "ImageView 클릭됨!", Toast.LENGTH_SHORT).show();
                }
                toggle = 0;
            }
        });

        // ImageView를 롱클릭 시 원래 이미지(ic_launcher.png)로 변경
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toggle = 1;
                iv.setImageResource(R.drawable.ic_launcher);
                return false;
//                return true;
            }
        });




    }
}