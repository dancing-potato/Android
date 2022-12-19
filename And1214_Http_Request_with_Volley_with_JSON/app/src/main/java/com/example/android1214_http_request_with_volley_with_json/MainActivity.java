package com.example.android1214_http_request_with_volley_with_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private Button btnRequest;
    private ListView movieListView;

    private static RequestQueue requestQueue;

    private MovieListAdatper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("일별 박스오피스 정보");

        btnRequest = findViewById(R.id.btnRequest);
        movieListView = findViewById(R.id.movieListView);

        // 일별 박스오피스 조회 버튼 리스너 연결
        btnRequest.setOnClickListener((v) -> {
            makeRequest();
        });

        // ListView(영화 목록) 항목 클릭 시 동작할 리스너 연결
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(adapter.getItem(position) instanceof Movie) {
                    Movie movie = (Movie)adapter.getItem(position);
                    Toast.makeText(MainActivity.this, movie.movieNm, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 요청큐가 비어있을 경우 생성
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }

    }

    private void makeRequest() {
        String key = "f5eef3421c602c6cb7ea224104795888";
        String targetDt = "20221208";

        String strUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
        strUrl += "?";
        strUrl += "key=" + key;
        strUrl += "&targetDt=" + targetDt;

        // StringRequest 객체를 사용하여 요청 정보 생성
        StringRequest request = new StringRequest(
                Request.Method.GET, // 요청 방식
                strUrl,             // 요청 URL
                response -> {       // 응답 리스너
//                    Log.i("요청 결과 >>>> ", response);
                    showMovieList(response);
                },
                error -> {          // 에러 리스너
                    Toast.makeText(this, "에러 발생! - " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );

        // 요청 객체를 요청 큐에 추가
        request.setShouldCache(false);
        requestQueue.add(request);

    }

    // ------------------- org.json.JSONXXX 객체 활용하는 방법 -------------------
//    private void showMovieList(String response) {
//
//        try {
//            // JSON 데이터 파싱하여 JSONObject 객체로 변환
//            // => org.json.JSONObject 객체 생성 시 파라미터로 JSON 데이터 전달하여 파싱
//            JSONObject jsonObject = new JSONObject(response);
//            Log.i("jsonObject >>> ", jsonObject.toString());
//
//            JSONObject boxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
//            Log.i("boxOfficeResult >>> ", boxOfficeResult.toString());
//
//            String boxofficeType = boxOfficeResult.getString("boxofficeType");
////            Log.i("boxofficeType >>> ", boxofficeType);
//            String showRange = boxOfficeResult.getString("showRange");
////            Log.i("showRange >>> ", showRange);
//
//            // -------- 박스오피스 목록을 배열로 추출하여 가공하기 --------
//            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
////            Log.i("dailyBoxOfficeList >>> ", dailyBoxOfficeList.toString());
//
//            // JSONArray 배열 크기만큼 for문을 사용하여 반복
//            for(int i = 0; i < dailyBoxOfficeList.length(); i++) {
//                // JSONArray 객체의 getJSONObject() 메서드를 호출하여 각 객체 가져오기
//                // => get() 메서드(리턴타입 : Object) 대신
//                //    getJSONObject() 메서드(리턴타입 : JSONObject) 사용 가능(형변환 불필요)
//                JSONObject movieInfo = dailyBoxOfficeList.getJSONObject(i);
////                Log.i("movieInfo >>> ", movieInfo.toString());
//                Log.i("movieNm >>> ", movieInfo.getString("movieNm"));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

    // ----------------- Gson 활용 방법 -----------------
    private void showMovieList(String response) {
        // JSON 데이터를 Gson 객체로 관리하기 위해 Gson 객체 생성
        Gson gson = new Gson();

        // Gson 객체의 fromJson() 메서드를 호출하여 JSON 데이터 파싱
        // => 파라미터 : JSON 데이터가 저장되어 있는 응답데이터(response) 와
        //              파싱 정보를 저장할 클래스를 지정(주의! 클래스명.class 형태로 지정)
        // => BoxOffice 클래스 타입을 지정하여 BoxOffice 객체를 리턴받아 BoxOffice 타입 변수에 저장
        BoxOffice boxOffice = gson.fromJson(response, BoxOffice.class);

        // BoxOffice 객체 - BoxOfficeResult 객체 - List(dailyBoxOfficeList) 객체 - Movie 객체순으로 접근
//        Log.i("박스오피스 목록 갯수 >>>> ", boxOffice.boxOfficeResult.dailyBoxOfficeList.size() + "");
//        Log.i("1위 영화 제목 >>>> ", boxOffice.boxOfficeResult.dailyBoxOfficeList.get(0).movieNm + "");
        
        // 영화 정보를 텍스트로 출력하는 대신 ListView 를 통해 표시해야하므로
        // BaseAdapter 클래스를 구현하는 MovieAdapter 객체를 사용하여 ListView 에 영화 목록 출력
        // => 이 때, 영화 정보가 담긴 List<Movie> 타입 객체를 MovieAdapter 객체에 전달 필요
        adapter = new MovieListAdatper(
                MainActivity.this, boxOffice.boxOfficeResult.dailyBoxOfficeList);
        movieListView.setAdapter(adapter);
    }


}












