package com.example.and1128_loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etId = findViewById(R.id.etId);
        EditText etPass = findViewById(R.id.etPass);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCancle = findViewById(R.id.btnCancle);

        // 취소 버튼 클릭 시 현재 액티비티 종료
        // => 단, 취소의 의미를 전달하기 위해 응답 코드 (resultCode)를 -1로 리턴
        // => 응답받는 곳 (onActivityResult()) 에서 응답코드가 -1 일경우
        //    Toast 메세지로 "로그인 취소" 메세지 출력
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 리턴할 때 전달할 데이터 없이 응답코드(resultCode)만 전달할 경우
                // Intent 객체 생성 없이 setResult() 메서드에 응답코드만 지정
                setResult(-1);
                finish();
            }
        });

        // 로그인 버튼 클릭 시 아이디, 패스워드 값을 LoginProcessActivity 로 전환
        // => Intent 객체에 아이디, 패스워드 포함
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                String pass = etPass.getText().toString();

                Intent intent = new Intent(LoginActivity.this, LoginProcessActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("pass", pass);
                startActivityForResult(intent, 0);
            }
        });
    }

    // LoginProcessActivity 가 종료될 때 자동으로 호출될 onActivityResult() 메서드 오버라이딩
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // LoginProcessActivity 로 부터 전달받은 응답코드 (resultCode) 판별
        // 1: 현재 액티비티 종료하고 MainActivity로 돌아갈때 응답코드 (resultCode)를 1로 전달
        // 0: Toast 메시지로 실패 원인(LoginResult) 출력 후 작업 종료
        switch (resultCode){
            case 0:
                Toast.makeText(this, data.getStringExtra("loginResult"), Toast.LENGTH_SHORT).show();
                break;
            case 1:
//                Toast.makeText(this, data.getStringExtra("loginResult"), Toast.LENGTH_SHORT).show();
                setResult(resultCode);
                finish();
                break;
        }

    }
}
