package com.example.android1214_http_request_with_volley_with_json;

/*
영화 1개 정보를 저장하는 Movie 클래스 정의
- JSON 객체의 각 파라미터에 해당하는 멤버변수를 선언하고
  파싱된 데이터를 저장하는 용도의 클래스
- 일반적으로 사용하는 DTO(Bean, VO) 클래스와 동일한 역할
- 단, JSON 데이터 파싱용이므로 GETTER/SETTER 는 불필요
- 주의! JSON 객체의 파라미터명과 변수명이 완벽하게 동일해야함
*/
public class Movie {
    String rnum;
    String rank;
    String rankInten;
    String rankOldAndNew;
    String movieCd;
    String movieNm;
    String openDt;
    String salesAmt;
    String salesShare;
    String salesInten;
    String salesChange;
    String salesAcc;
    String audiCnt;
    String audiInten;
    String audiChange;
    String audiAcc;
    String scrnCnt;
    String showCnt;
}















