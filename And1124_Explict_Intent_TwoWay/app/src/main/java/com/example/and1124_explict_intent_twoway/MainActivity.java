package com.example.and1124_explict_intent_twoway;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etNum1 = findViewById(R.id.etNum1);
        EditText etNum2 = findViewById(R.id.etNum2);
        Button btnAdd = findViewById(R.id.btnAdd);

        // 더하기 버튼 클릭 시 새 액티비티로 전환
        // => 이때, 명시적 인텐트를 사용하며, 입력받은 정수 2개를 포함하여 전달
        // => 또한, 계산결과를 리턴받기 위해 startActivity() 메서드가 아닌
        //    startActivityForResult() 메서드를 호출하여 새 액티비티로 전환
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText 위젯에 입력된 2개의 숫자 가져오기 (입력 여부 판별 생략)
                int num1 = Integer.parseInt(etNum1.getText().toString());
                int num2 = Integer.parseInt(etNum2.getText().toString());

                // Intent 객체 생성
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // Intent 객체에 데이터 저장
                intent.putExtra("num1", num1);
                intent.putExtra("num2", num2);

//                startActivity(intent); // 응답을 리턴받을 수 없는 액티비티 전환
                // startActivityForResult(인텐트객체, 요청코드)
                // => 요청코드(requestCode)는 여러 액티비티로부터 응답이 리턴되어야 하는 경우
                //    각 응답 액티비티를 구분할 목적으로 사용함 (구분이 필요없으면 0 이상 아무거나)
                startActivityForResult(intent, 0);
            }
        });
    } // onCreate() 메서드 끝

    // 다른 액티비티에서 응답을 전달받기 위해 startActivityForResult() 메서드를 호출한 경우
    // 해당 액티비티로 부터 finish() 메서드에 의해 다시 현재 액티비티로 돌아올때
    // onActivityResult() 메서드가 자동으로 호출되므로 오버라이딩 필수!
    // => setResult() 메서드로 전달한 resultCode 와 Intent 객체는 물론
    //    startActivityForResult() 메서드 호출 시 전달한 requestCode 도 함께 전달 됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 만약, 여러 액티비티로부터 응답이 돌아오는 경우
//        if(requestCode == 0){ // SecondActivity
//
//        } else if(requestCode == 1){
//
//        }

        if(resultCode == RESULT_OK){
            int result = data.getIntExtra("result", 0);
            Toast.makeText(this, "덧셈 결과 : " + result, Toast.LENGTH_SHORT).show();
        }

    }
}