package com.example.and1025_basicwidget_padding_margin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);

        btn4.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "버튼4 클릭", Toast.LENGTH_SHORT).show();
        });
        btn5.setOnClickListener(view ->
            Toast.makeText(MainActivity.this, "버튼5 클릭", Toast.LENGTH_SHORT).show()
        );




    }
}
