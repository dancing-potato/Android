package com.example.and1115_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        * 대화상자(Dialog)
        * - 사용자에게 알림을 전달하거나, 사용자로부터 상호작용을 수행하는 별도의 창
        * - AlertDialog.Builder 클래스 사용하여 다이얼로그를 생성하고,
        *   setXXX() 메서드를 사용하여 다이얼로그 설정하고,
        *   show() 메서드를 사용하여 다이얼로그 표시
        * */
    }

    public void showDialog(View view){
//        Toast.makeText(this, "이벤트 연결됨", Toast.LENGTH_SHORT).show();
        // 1. AlertDialog.Builder 클래스를 활용하여 다이얼로그 객체 생성
        // => 파라미터로 Context 객체 전달(this 또는 MainActivity.this 또는 getApplicationContext())
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        // 2. AlertDialog.Builder 객체의 setXXX() 메서드를 호출하여 다이얼로그 설정 작업 수행
        // 2-1. 다이얼로그에 표시할 제목 설정
        dialog.setTitle("다이얼로그 제목");
        // 2-2. 다이얼로그에 표시할 내용 설정
        dialog.setMessage("다이얼로그 본문");
        // 2-3. 아이콘 설정
        dialog.setIcon(R.mipmap.ic_launcher_round);

        // 2-4. 다이얼로그에 버튼 부착 (기본 버튼 3개 제공됨)
        // => 메서드에 따른 버튼 역할의 차이는 없으며, 관례적인 의미에 맞게 사용
//        dialog.setPositiveButton("확인", null);
        dialog.setNegativeButton("취소", null);
        dialog.setNeutralButton("중립버튼", null);

        // 다이얼로그 버튼에 이벤트 연결
        // => 일반 Button 객체의 OnClickListener와 동일한 방법으로 이벤트 처리 가능
        //    단, DialogInterface.OnClickListener 사용하는 점이 다름
        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "확인 버튼 누름!", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. AlertDialog.Builder 객체의 show() 메서드를 호출하여 다이얼로그 표시
        dialog.show();
    }

    public void showDialog2(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("영화 정보");
        dialog.setMessage("영화 줄거리...................................");
        dialog.setIcon(R.drawable.mov05);

        // 4단계로 구현된 리스너 연결
        dialog.setPositiveButton("확인", listener);
        dialog.setNegativeButton("취소", listener);
        dialog.setNeutralButton("상세보기", listener);

        dialog.show();
    }

    // 다이얼로그 내의 버튼에 리스너 연결 4단계 구현
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
//            Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT).show();

            switch (i){
                case -1:
                    Toast.makeText(MainActivity.this, "확인 클릭됨!", Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(MainActivity.this, "취소 클릭됨!", Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(MainActivity.this, "상세보기 클릭됨!", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };


}