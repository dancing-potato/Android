package com.example.and1027_simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        EditText edit1 = findViewById(R.id.edit1);
        EditText edit2 = findViewById(R.id.edit2);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);
        TextView tvResult = findViewById(R.id.tvResult);

        // 4개의 버튼에 리스너 연결하여 이벤트 처리
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                if(view.getId() == R.id.btnAdd){
//                    Toast.makeText(MainActivity.this, "더하기", Toast.LENGTH_SHORT).show();
//                }
                // EditText(edit1, edit2)에 입력된 숫자 2개 가져와서 정수로 변환 후
                // 연산하여 결과를 TextView(tvResult)에 출력
                // => 단, 숫자가 입력되지 않았을 경우 오류 메세지 출력 및 커서 요청
                // edit1.getText().toString().equals("")
                if(edit1.length() == 0){
                    Toast.makeText(MainActivity.this, "숫자1 입력 필수!", Toast.LENGTH_SHORT).show();
                    edit1.requestFocus();
                    return;
                } else if(edit2.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "숫자2 입력 필수!", Toast.LENGTH_SHORT).show();
                    edit2.requestFocus();
                    return;
                }

                // 입력받은 숫자 가져오기
                // => 만약, 정수와 실수를 구분하는 경우
//                if(edit1.getText().toString().contains(".") ||
//                   edit2.getText().toString().contains(".")){
//                    // 둘 중 하나라도 실수가 존재하면 실수 형태로 계산
//                } else {
//                    // 정수형태로 계산
//                }

                int num1 = Integer.parseInt(edit1.getText().toString());
                int num2 = Integer.parseInt(edit2.getText().toString());

                int result = 0;

                switch (view.getId()){
                    case R.id.btnAdd: result = num1 + num2; break;
                    case R.id.btnSub: result = num1 - num2; break;
                    case R.id.btnMul: result = num1 * num2; break;
                    case R.id.btnDiv:

                        // 두번째 피연산자가 0이면 예외가 발생하므로 주의!
                        if(num2 == 0){
                            Toast.makeText(MainActivity.this, "나눗셈에 0 사용 금지!", Toast.LENGTH_SHORT).show();
                            edit2.requestFocus();
                            tvResult.setText("계산결과 : 오류 발생!");
                            return;
                        }

                        result = num1 / num2;
                        break;
                }


                tvResult.setText("계산결과 : " + result);
            }
        };

        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);






    }
}