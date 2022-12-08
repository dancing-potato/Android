package com.example.and1128_loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginProcessActivity extends AppCompatActivity {
    int loginResult = 0; // 로그인 결과를 저장할 변수
    // => 버튼의 리스너 구현체 내부에서 접근해야하므로 로컬변수 대신 멤버변수로 선언해야함

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);

        TextView tvResult = findViewById(R.id.tvResult);
        Button btnFinish = findViewById(R.id.btnFinish);

        // DB에 저장된 아이디, 패스워드를 읽어왓다고 가정
        String dbId = "admin";
        String dbPass = "1234";

        // LoginActivity 로 부터 전달받은 intent 객체 내의 아이디, 패스워드를 가져와서
        // DB에서 조회한 아이디, 패스워드와 비교하여
        // 둘다 일치할 경우 TextView 에 "로그인 성공" 출력,
        // 아닐 경우 TextView 에 "로그인 실패" 출력
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pass = intent.getStringExtra("pass");

        // 잘 전달되었는지 확인
//        Toast.makeText(this, id + " : " + pass, Toast.LENGTH_SHORT).show();

        Intent returnIntent = new Intent(LoginProcessActivity.this, LoginActivity.class);


        if(id.equals(dbId) && pass.equals(dbPass)){
            tvResult.setText("로그인 성공");
            loginResult = 1;
            returnIntent.putExtra("loginResult", "로그인 성공");

        } else {
            tvResult.setText("로그인 실패");
            loginResult = 0;

            if(!id.equals(dbId)){ // 로그인 실패 (아이디 없을 경우)
                returnIntent.putExtra("loginResult", "로그인 실패(아이디 없음)");
            } else { // 로그인 실패 (패스워드 틀렸을 경우
                returnIntent.putExtra("loginResult", "로그인 실패(패스워드 틀림)");
            }

        }

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(loginResult, returnIntent);
                finish();
            }
        });


    }
}
