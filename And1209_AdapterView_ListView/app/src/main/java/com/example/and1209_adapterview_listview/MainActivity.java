package com.example.and1209_adapterview_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView 위젯 연결
        listView = findViewById(R.id.listView);

        // ListView 위젯에 표시할 데이터를 String[] 타입으로 생성하거나
        // java.util.List 타입 객체를 생성해도 됨
        String[] items = {
                "감귤", "깻잎", "양파", "레몬", "제육철판볶음밥", "매일두유", "생강",
                "상추", "비건브라우니", "미트프리탕수육", "두부크럼블", "무설탕쿠키",
                "아시아노밋피자", "비건초코파이", "아임리얼스무디"
        };

        // List 객체 생성 시
//        List<String> itemList = Arrays.asList(items);
        // ------------------------------------------------------------------------
        // ArrayAdapter 객체 생성
        // => 제네릭타입은 목록으로 표시할 실제 데이터의 데이터타입
        // => 파라미터로 현재 액티비티(= 컨텍스트), 리스트뷰의 모양, 표시할 데이터 객체 전달
        //    (이 때, 리스트뷰 모양은 안드로이드에서 제공하는 기본 형태 사용하므로 android.R.layout 패키지 활용)
        // 1. ListView 의 목록을 기본 목록 형태로 표시(= 단일 선택)
        //    (android.R.layout.simple_list_item_1 사용)
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//            this, android.R.layout.simple_list_item_1, items
//        );
        // ----------------------
        // 2. ListView 의 목록을 라디오버튼 선택 형태로 표시(= 단일 또는 다중 선택)
        // => (android.R.layout.simple_list_item_single_choice 사용)
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_single_choice, items
//        );

        // ListView 객체의 setChoiceMode() 메서드를 호출하여 선택 형식 지정 필수!
        // => CHOICE_MODE_XXX 상수를 활용
        // => 미지정 시 클릭은 가능하지만 선택 액션이 일어나지 않는다!
        //    (= 기본값이 비선택 모드로 동작함!)
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 단일 선택 모드(주로 사용)
//        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // 다중 선택 모드
        // ----------------------
        // 2. ListView 의 목록을 체크박스 선택 형태로 표시(= 단일 또는 다중 선택)
        // => (android.R.layout.simple_list_item_multiple_choice 사용)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_multiple_choice, items
        );

        // ListView 객체의 setChoiceMode() 메서드를 호출하여 선택 형식 지정 필수!
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // 단일 선택 모드(주로 사용)


        // -----------------------------------------------------------------------
        // ListView 객체의 setAdapter() 메서드를 호출하여 ArrayAdapter 객체를 전달
        listView.setAdapter(adapter);
        // -----------------------------------------------------------------------
        // ListView 항목 클릭 시 동작할 이벤트 처리를 위해 OnItemClickListener 연결
//        listView.setOnItemClickListener((adapterView, view, i, l) -> {}); // 람다식
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                // ListView 항목에서 클릭된 항목의 인덱스번호가 int 타입으로 전달됨
//                Toast.makeText(MainActivity.this, index + "", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, items[index], Toast.LENGTH_SHORT).show();
            }
        });


    }
}













