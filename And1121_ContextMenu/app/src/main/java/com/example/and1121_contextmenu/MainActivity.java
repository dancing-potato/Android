package com.example.and1121_contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    LinearLayout baseLayout;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        baseLayout = findViewById(R.id.baseLayout);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        // 컨텍스트 메뉴를 위젯에 등록하기 위해서는
        // registerForContextMenu() 메서드를 호출하여 파라미터로 위젯 객체를 전달
        // => 아직 어떤 메뉴가 표시될지는 결정되지 않은 상태
        registerForContextMenu(btn1);
        registerForContextMenu(btn2);
    }

    // onCreateContextMenu() 메서드를 오버라이딩하여 해당 위젯에 메뉴 표시
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // 메뉴 등록을 위해서 MenuInflater 객체 필요
        MenuInflater menuInflater = getMenuInflater();

        if(v == btn1){ // v.getId() == R.id.btn1
            menuInflater.inflate(R.menu.menu1, menu);
            menu.setHeaderTitle("배경색 선택");

        } else if(v == btn2){ // v.getId() == R.id.btn2
            menuInflater.inflate(R.menu.menu2, menu);
            menu.setHeaderTitle("이미지 작업 선택");
        }
    }

    // onContextItemSelected() 메서드를 오버라이딩하여 선택된 메뉴에 대한 작업 수행
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);

        switch (item.getItemId()) {
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.itemRotate:
                iv.setRotation(iv.getRotation() + 30);
                break;
            case R.id.itemExpand:
                iv.setScaleX(iv.getScaleX() + 1);
                iv.setScaleY(iv.getScaleY() + 1);
        }

        return true;
    }
}