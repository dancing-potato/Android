package com.example.android1214_http_request_with_volley_with_json;

import java.util.ArrayList;
import java.util.List;

/*
박스오피스 목록 검색 결과를 객체로 관리하는 BoxOfficeResult 클래스 정의
- 박스오피스 종류, 검색일자, 영화목록을 각각의 멤버변수에 저장
*/
public class BoxOfficeResult {
    String boxofficeType; // 박스오피스 종류
    String showRange; // 검색일자(범위)
    // 검색 결과인 박스오피스 목록을 저장하기 위해 List 타입 변수 선언 및 객체 생성
    // => 제네릭타입으로 영화 정보 1개를 저장하는 Movie 타입 지정
    //    (단, Movie 클래스를 먼저 정의해야함)
    List<Movie> dailyBoxOfficeList = new ArrayList<Movie>();
}
