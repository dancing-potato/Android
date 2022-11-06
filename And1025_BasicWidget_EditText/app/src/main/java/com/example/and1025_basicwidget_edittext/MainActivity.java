package com.example.and1025_basicwidget_edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditText와 Button, TextView 위젯 ID 가져와서 연결
        EditText edit = findViewById(R.id.edit);
        Button btnOk = findViewById(R.id.btnOk);
        TextView tv = findViewById(R.id.tv);

        // Button 객체에 OnClickListener 를 연결하여 버튼 위젯 클릭 이벤트 처리하도록 구현
        // => Button 객체의 setOnClickListener() 메서드를 호출하여
        //    OnClickListener 인터페이스 구현체를 파라미터로 전달
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText 위젯에서 입력받은 텍스트를 가져와서 출력
                String str = edit.getText().toString(); // 오류 발생! 리턴타입이 Editable 이므로 저장불가
                // => toString() 메서드 호출하여 형변환 필수!

                // 1. Toast 메세지로 출력
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                
                // 2. TextView 위젯에 출력
                tv.setText(str);
            }
        });

        // EditText 위젯에 키보드 입력을 감지하는 리스너 연결
        edit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                // 주의! Toast 에서 출력할 메세지는 파라미터 타입이 문자열이므로
                // 정수 데이터를 전달할 경우 문법 오류가 발생하지 않고, 실행 시 논리적 오류 발생함
                // => 반드시 정수 -> 문자열로 변환하여 전달해야함!
                Toast.makeText(MainActivity.this, keyCode + "", Toast.LENGTH_SHORT).show();

                // onKey() 메서드에 전달된 keyCode 값을 비교하여 입력받은 키 판별 가능
                // => KeyEvent.KEYCODE_XXX 상수를 사용하여 원하는 키 지정
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    String str = edit.getText().toString();
                    tv.setText(str);
                }

                return false;
            }
        });






    }
}