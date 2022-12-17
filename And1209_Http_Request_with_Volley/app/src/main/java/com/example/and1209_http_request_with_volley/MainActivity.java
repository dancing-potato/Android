package com.example.and1209_http_request_with_volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etUrl;
    private Button btnRequest, btnLogin;
    private TextView tvResponse;

    // 샘플 요청 URL(영화 정보 - 일별 박스오피스 목록)
    // http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20221208

    // 요청(Request) 객체를 저장할 com.android.volley.RequestQueue 타입 변수 선언
    // => 주의! 반드시 의존성 패키지 com.android.volley 라이브러리 추가 필수!
    private static RequestQueue requestQueue;

    // 주의사항! AndroidManifest.xml 파일에 권한 설정 필수!
    // 1) INTERNET 접근 권한 허용 : uses-permission 태그 사용
    //    <uses-permission android:name="android.permission.INTERNET"/>
    // 2) HTTP 프로토콜 요청 허용
    //    application 태그 내에 android:usesClearTextTraffic 속성값을 true 로 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.etUrl);
        btnRequest = findViewById(R.id.btnRequest);
        btnLogin = findViewById(R.id.btnLogin);
        tvResponse = findViewById(R.id.tvResponse);
        tvResponse.setMovementMethod(new ScrollingMovementMethod());

        btnRequest.setOnClickListener((v) -> {
            makeRequest();
        });

        btnLogin.setOnClickListener((v) -> {
            showLoginDialog();
        });

        // 처음 앱 시작 시 요청 큐(RequestQueue) 가 비어있을 경우 객체 생성
        if (requestQueue == null) {
            // Volley 클래스의 newRequestQueue() 메서드를 호출하여 요청큐 생성
            // => 파라미터 : 현재 액티비티 객체
            requestQueue = Volley.newRequestQueue(this);
        }

    }

    // 요청 정보를 생성하여 요청을 수행할 makeRequest() 메서드 정의
    public void makeRequest() {
        // 입력받은 URL 가져와서 변수 strUrl 에 저장
        String strUrl = etUrl.getText().toString();

        // 입력받은 URL 을 사용하여 요청 정보를 문자열 형태로 생성하기 위한 StringRequest 객체 생성
        // 파라미터
        // 1) 요청 방식(Request.Method.XXX 상수 활용)
        // 2) 요청 URL
        // 3) 응답에 대한 리스너 구현 => Response.Listener 구현체 활용
        // 4) 에러에 대한 리스너 구현 => Response.ErrorListener 구현체 활용
//        StringRequest request = new StringRequest(); // GET 방식 요청일 경우 객체 생성 형태
//        StringRequest request = new StringRequest() {}; // POST 방식 요청일 경우 객체 생성 형태
        StringRequest request = new StringRequest(
                Request.Method.GET, // 요청 방식을 GET 방식으로 지정
                strUrl, // 요청 URL
                new Response.Listener<String>() { // 응답 리스너
                    @Override
                    public void onResponse(String response) { // 응답 전달 시 자동으로 호출됨
                        // 응답 메세지를 TextView 에 출력하기 위해 showMessage() 메서드 호출
                        showMessage("[ 응답데이터 ]\n" + response);
                    }
                },
                new Response.ErrorListener() { // 에러 리스너
                    @Override
                    public void onErrorResponse(VolleyError error) { // 에러 전달 시 자동으로 호출됨
                        // Exception 클래스를 활용한 예외 처리와 유사
                        showMessage("[ 에러데이터 ]\n" + error.getMessage());
                    }
                }
        ) {
            // POST 방식 요청일 경우(StringRequest 객체 생성 시 Request.Method.POST 지정)
            // StringRequest 객체 생성자 뒤에 메서드 오버라이딩 블럭({})을 추가하여 getParams() 메서드 오버라이딩 필요
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // POST 방식으로 전달해야할 파라미터가 있을 경우
                // Map 계열 객체 생성하여 키=값 형태로 파라미터를 저장 후 리턴
                // ex) key=f5eef3421c602c6cb7ea224104795888&targetDt=20221208 데이터 전달 시
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "f5eef3421c602c6cb7ea224104795888");
                params.put("targetDt", "20221208");

                return params;
            }
        };

        // 요청 객체(StringRequest) 생성 완료 후 요청 큐(RequestQueue 객체)에 요청 객체 추가
        // => RequestQueue 객체의 add() 메서드를 호출하여 StringRequest 객체 전달
        // => 만약, 이전 요청에서 사용된 응답 결과를 사용하지 않도록 설정하려면
        //    StringRequest 객체의 setShouldCache() 메서드를 호출하여 false 값 전달(새로운 요청 생성)
        request.setShouldCache(false);
        requestQueue.add(request);
        showMessage("요청 완료!");
    }

    private void showMessage(String msg) {
        // TextView 위젯에 전달받은 문자열 출력(응답데이터, 에러데이터, 요청 상황 등)
        // => 기존 텍스트 유지하면서 추가(append())
        tvResponse.append(msg + "\n");
    }

    // ------------------ 로그인 기능 ---------------------
    private void makeLoginRequest() {

    }

    private void showLoginDialog() {
        // 1. View 클래스의 inflate() 메서드를 호출하여 외부 레이아웃 연결
        View dialogView =
                View.inflate(MainActivity.this, R.layout.login_dialog, null);

        // 2. 1번에서 생성한 View 객체의 findViewById() 메서드를 호출하여 login_dialog.xml 의 위젯 연결
        EditText etId = dialogView.findViewById(R.id.etId);
        EditText etPasswd = dialogView.findViewById(R.id.etPasswd);

        // 3. 다이얼로그 생성을 위해 AlertDialog.Builder 객체 생성
        //    (패키지는 android.app 또는 androidx.appcompat.app
        // => 파라미터 : 현재 액티비티
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        // 4. 다이얼로그에 표시할 정보 설정
        // 4-1) 다이얼로그 제목 설정
        dialog.setTitle("Login Dialog");
        // 5-2) 다이얼로그에 표시할 버튼 설정
        dialog.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // 5-2) 다이얼로그에 표시할 버튼 설정
        dialog.setNegativeButton("취소", null);

        // 5-4) 다이얼로그에 표시할 레이아웃(View 객체) 설정
        dialog.setView(dialogView);

        // 6. 다이얼로그 표시
        dialog.show();
    }

}













