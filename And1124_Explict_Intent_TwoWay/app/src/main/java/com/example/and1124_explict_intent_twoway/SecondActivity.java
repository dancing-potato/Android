package com.example.and1124_explict_intent_twoway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // MainActivity 로부터 전달받은 Intent 객체 가져와서 데이터(num1, num2) 꺼내기
        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1", 0);
        int num2 = intent.getIntExtra("num2", 0);

//        Toast.makeText(this, num1 + ", " + num2, Toast.LENGTH_SHORT).show();

        int result = num1 + num2;

        Button btnReturn = findViewById(R.id.btnReturn);
        // 돌아가기 버튼 클릭 시 현재 액티비티 종료
        // => 단, 정수 2개의 덧셈 결과를 새 Intent 객체에 저장하여 리턴
//        btnReturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Intent 객체 생성
//                // => MainActivity 에서 이동할 때 설정한 정보와 반대로 설정 필요
//                Intent returnIntent = new Intent(SecondActivity.this, MainActivity.class);
//
//                // putExtra()
//                returnIntent.putExtra("result", result);
//
//                // 현재 액티비티에서 리턴할 Intent 객체를 전달하기 위해서는
//                // setResult() 메서드를 호출하여 응답코드(ResultCode)와 인텐트 객체 전달
//                setResult(RESULT_OK, returnIntent);
//
//                finish();
//                // MainActivity 클래스의 onActivityResult() 메서드가 자동으로 호출되고
//                // setResult() 메서드에 포함된 정보가 전달됨
//            }
//        });

        // -------------------------------------------------------------

        // 만약, 계산 즉시 현재 액티비티를 종료하고 돌아가려면
        // 버튼 이벤트 없이 바로 응답 작업 구현하면 된다!
        Intent returnIntent = new Intent(SecondActivity.this, MainActivity.class);
        returnIntent.putExtra("result", result);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
