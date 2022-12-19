package com.example.android1214_http_request_with_volley_with_json;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// android.widget.BaseAdapter 클래스를 상속받는 MovieListAdapter 클래스 정의
public class MovieListAdatper extends BaseAdapter {
    private Context context; // 액티비티 객체를 전달받을 Context 타입 변수
    private List<Movie> dailyBoxOfficeList; // 영화 목록 정보 저장할 List<Movie> 타입 변수

    // 파라미터 생성자 정의
    // => Context 객체 초기화, List<Movie> 객체 초기화
    public MovieListAdatper(Context context, List<Movie> dailyBoxOfficeList) {
        this.context = context;
        this.dailyBoxOfficeList = dailyBoxOfficeList;
    }

    // 표시할 목록(영화 정보 목록) 갯수 리턴 => List 객체 크기 리턴
    @Override
    public int getCount() {
        return dailyBoxOfficeList.size();
    }

    // 목록 중 선택된(= 클릭된) 특정 항목 정보를 리턴
    @Override
    public Object getItem(int position) { // 클릭된 대상의 인덱스가 전달됨
        // List<Movie> 객체의 position 값에 해당하는 객체 리턴하면 영화 1개 정보 리턴됨
        return dailyBoxOfficeList.get(position);
    }

    // ListView 위젯에 각각의 영화 정보를 목록으로 표시하기 위해
    // 목록 중 1개의 정보를 생성하는 메서드 => getCount() 메서드만큼 반복 호출됨
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // View.inflate() 메서드를 호출하여 영화 1개 정보를 표시할 외부 레이아웃 가져오기(= 연결)
        View movieLayout = View.inflate(context, R.layout.movie_item, null);

        // movie_item.xml 레이아웃에 포함된 위젯 연결
        ImageView ivMoviePoster = movieLayout.findViewById(R.id.ivMoviePoster);
        TextView tvMovieNm = movieLayout.findViewById(R.id.tvMovieNm);
        TextView tvAudiCnt = movieLayout.findViewById(R.id.tvAudiCnt);

        // 각 위젯에 각각의 영화 정보 출력(List 객체 인덱스는 파라미터 position 값 사용)
        // 단, 영화 포스터는 임시 그림파일 사용
        ivMoviePoster.setImageResource(R.mipmap.ic_launcher);
        // List 객체인 dailyBoxOfficeList 객체의 get(position).XXX 형식으로 멤버변수 접근
        tvMovieNm.setText(dailyBoxOfficeList.get(position).movieNm);
        tvAudiCnt.setText("관객 수 : " + dailyBoxOfficeList.get(position).audiCnt);

        // 생성된 View 객체 리턴
        return movieLayout;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
