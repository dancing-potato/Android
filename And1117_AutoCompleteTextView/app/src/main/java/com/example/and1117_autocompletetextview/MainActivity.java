package com.example.and1117_autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        MultiAutoCompleteTextView multi = findViewById(R.id.multi);

        // 자동완성에 사용될 후보 단어 목록을 저장할 String[] 배열 생성
        String[] items = {
          "Java", "Javascript", "JSP Model 1", "JSP Model 2",
          "Android", "HTML", "HTTP 기초", "Apache Server",
          "자바", "자바스크립트"      
        };
        
        // ===========================================================================
        // AutoCompleteTextView : 자동완성 기준에 적합한 갯수의 글자가 입력되면
        //                        관련된 단어가 있을 경우 목록으로 표시하고
        //                        해당 목록의 단어를 선택하면 자동으로 입력하는 위젯
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, items);

        autoCompleteTextView.setAdapter(adapter);

        // ================================================================================
        // MultiAutoCompleteTextView : AutoCompleteTextView 와 기본 동작은 동일하지만
        // 하나의 단어만 자동완성하는 것이 아니라, 단어 완성 후 콤마(,)를 붙여서
        // 다음 단어도 자동완성으로 동작하도록 하는 위젯
        // => 기본적인 설정은 거의 유사함
        List<String> items2 = Arrays.asList(items);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, items2);
        multi.setAdapter(adapter2);

        // 자동완성 단어를 콤마(,)로 구분하기 위해 CommaTokenizer 객체 생성
        MultiAutoCompleteTextView.CommaTokenizer tokenizer =
                new MultiAutoCompleteTextView.CommaTokenizer();
        multi.setTokenizer(tokenizer);





    }
}