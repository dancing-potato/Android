package com.example.and1209_adapterview_listview_custormize;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // list.xml 레이아웃의 1개 목록에 해당하는 데이터를 배열로 생성
    // 1. 이미지 파일 ID 저장하는 배열
    private int[] imageId = {
        R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4, R.drawable.item5
        , R.drawable.item6, R.drawable.item7, R.drawable.item8, R.drawable.item9, R.drawable.item10
        , R.drawable.item11, R.drawable.item12, R.drawable.item13, R.drawable.item14, R.drawable.item15
    };

    // 2. 상품명 저장
    private String[] titles = {
            "감귤", "깻잎", "양파", "레몬", "제육철판볶음밥"
            , "매일두유", "생강", "상추", "비건브라우니", "미트프리탕수육"
            , "두부크럼블", "무설탕쿠키", "아시아노밋피자", "비건초코파이", "아임리얼스무디"
    };

    // 3. 상품 설명(제조사) 저장
    private String[] contents = {
            "과일엔", "대한민국농수산대한민국농수산대한민국농수산대한민국농수산", "대한민국농수산", "과일엔", "풀무원"
            , "오뚜기오뚜기오뚜기오뚜기오뚜기오뚜기오뚜기오뚜기오뚜기오뚜기", "대한민국농수산", "대한민국농수산", "베지가든", "오뚜기"
            , "풀무원", "베지가든", "오뚜기", "베지푸드", "이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼이츠리얼"
    };

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView 위젯 ID 연결
        listView = findViewById(R.id.listView);

        // ArrayAdapter 대신 커스터마이징 클래스(MyListViewAdapter) 인스턴스 생성
        // => 파라미터로 현재 액티비티 객체 전달(커스터마이징 객체 내에서 접근하기 위함)
        MyListViewAdapter adapter = new MyListViewAdapter(this);

        // ListView 객체의 setAdapter() 메서드를 호출하여 어댑터 객체(MyListViewAdapter 객체) 전달
        listView.setAdapter(adapter);

        // ListView 목록 중 하나 클릭했을 경우 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(MainActivity.this, titles[position], Toast.LENGTH_SHORT).show();
                // 클릭된 상품의 상세 정보를 다이얼로그로 표시(listview_dialog.xml 내용 출력)
                // 1. View 클래스의 inflate() 메서드를 호출하여 외부 레이아웃 연결
                // => OnItemClickListener 구현체 내부이므로 this 대신 MainActivity.this 사용
                View dialogView =
                        View.inflate(MainActivity.this, R.layout.listview_dialog, null);

                // 2. 1번에서 생성한 View 객체의 findViewById() 메서드를 호출하여 listview_dialog.xml 의 위젯 연결
                ImageView ivItemImage = dialogView.findViewById(R.id.ivItemImage);
                TextView tvTitle = dialogView.findViewById(R.id.tvTitle);
                TextView tvContent = dialogView.findViewById(R.id.tvContent);

                // 3. ImageView 와 TextView 위젯에 데이터 표시
                ivItemImage.setImageResource(imageId[position]);
                tvTitle.setText(titles[position]);
                tvContent.setText(contents[position]);
                // 만약, TextView 위젯에 스크롤 기능을 추가하려면 ScrollView 위젯 대신
                // TextView 객체의 setMovementMethod() 메서드를 호출하여
                // android.text.method.ScrollingMovementMethod 객체를 파라미터로 전달
                tvContent.setMovementMethod(new ScrollingMovementMethod());
                
                // -------- 여기서부터 Adapter 클래스 정의 getView() 메서드 내용과 달라짐
                // 4. 다이얼로그 생성을 위해 AlertDialog.Builder 객체 생성
                //    (패키지는 android.app 또는 androidx.appcompat.app
                // => 파라미터 : 현재 액티비티
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                // 5. 다이얼로그에 표시할 정보 설정
                // 5-1) 다이얼로그 제목 설정
                dialog.setTitle("상품 상세정보");
                // 5-2) 다이얼로그에 표시할 버튼 설정
                dialog.setPositiveButton("닫기", null);
                // 5-3) 다이얼로그에 표시할 레이아웃(View 객체) 설정
                dialog.setView(dialogView);

                // 6. 다이얼로그 표시
                dialog.show();
            }
        });
    }

    // ListView 위젯 커스터마이징을 위해 BaseAdapter 클래스를 상속받는 서브클래스 정의(내부클래스로 정의)
    // => 추상메서드 4개(getCount(), getItem(), getItemId(), getView()) 구현 필수! (단축키 : Ctrl + I)
    private class MyListViewAdapter extends BaseAdapter {
        private Context context; // 액티비티(컨텍스트)를 전달받아 저장할 멤버변수 선언

        // 생성자 정의 - Context 객체 전달받아 초기화(단축키 : Alt + Insert)
        public MyListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            // ListView 위젯에 표시할 항목(= 레이아웃 = list.xml 파일)의 반복 횟수 지정하는 메서드
            // => 반복하고자 하는 횟수를 리턴
            //    (표시할 데이터 갯수와 동일하므로 배열 크기 리턴)
            // => 리턴되는 값만큼 getView() 메서드가 자동으로 반복 호출됨
            return titles.length;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            // ListView 위젯에 표시할 1개의 데이터(= 레이아웃을 담은 View 객체)를 생성하여 리턴하는 메서드
            // => 외부 레이아웃(list.xml)을 연결하여
            //    해당 레이아웃 내의 ImageView, TextView 위젯에 접근하여 데이터를 설정(표시)하고
            //    해당 레이아웃을 담은 View 객체 리턴하는 용도의 메서드
            // ---------------------------------------------------------------------------------
            // 1. android.view.View 클래스의 inflate() 메서드를 호출하여 사용할 외부 레이아웃 연결
            // => 파라미터 : 현재 액티비티, 레이아웃 파일(list.xml), 뷰그룹객체(null)
            // => 단, 현재 클래스가 Adapter 계열로, Activity 객체를 전달하기 위해서는
            //    외부로부터 액티비티 객체를 전달받아 저장해야함(Context 타입 변수 context 활용)
            //    (또는 액티비티클래스명.this 사용도 가능 => ex. MainActivity.this)
            View listViewLayout = View.inflate(context, R.layout.list, null);

            // 2. 1번에서 생성한 View 객체의 findViewById() 메서드를 호출하여 list.xml 의 위젯 연결
            // => 주의! 현재 액티비티의 findViewById() 메서드 호출 시
            //    액티비티와 연결된 레이아웃(activity_main.xml) 의 위젯에 접근하므로
            //    반드시 외부 액티비티가 저장된 View 객체(listViewLayout)의 findViewById() 메서드 호출
            // => ImageView 위젯 1개, TextView 위젯 2개 연결
            ImageView ivItemImage = listViewLayout.findViewById(R.id.ivItemImage);
            TextView tvTitle = listViewLayout.findViewById(R.id.tvTitle);
            TextView tvContent = listViewLayout.findViewById(R.id.tvContent);
            
            // 3. ImageView 와 TextView 위젯에 데이터 표시
            // => 배열의 데이터 접근 시 getView() 메서드 파라미터 중 int 타입 position 값 활용
            // 3-1) ImageView 위젯에 이미지 표시 -> ImageView 객체의 setImageResource()
            ivItemImage.setImageResource(imageId[position]);
            // 3-2) TextView 위젯에 텍스트 표시 -> TextView 객체의 setText()
            tvTitle.setText(titles[position]);
            tvContent.setText(contents[position]);

            // 4. 생성된 View 객체 리턴
            return listViewLayout;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}













