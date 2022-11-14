package com.example.and1101_picture_viewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox checkStart;
    TextView tvPet;
    RadioGroup rGroup;
    Button btnReset, btnFinish;
    ImageView ivPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkStart = findViewById(R.id.checkStart);
        tvPet = findViewById(R.id.tvPet);
        rGroup = findViewById(R.id.rGroup);
        btnReset = findViewById(R.id.btnReset);
        btnFinish = findViewById(R.id.btnFinish);
        ivPet = findViewById(R.id.ivPet);

        // 1. 체크박스 체크 시 나머지 모든 항목 표시 (tvPet, rGroup, ivPet)
        checkStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // isChecked가 true 일 경우 VISIBLE
                //    ""       false 일 경우 INVISIBLE or GONE
//                if(isChecked){
//                    tvPet.setVisibility(View.VISIBLE);
//                    rGroup.setVisibility(View.VISIBLE);
//                    ivPet.setVisibility(View.VISIBLE);
//                } else {
//                    tvPet.setVisibility(View.INVISIBLE);
//                    rGroup.setVisibility(View.INVISIBLE);
//                    ivPet.setVisibility(View.INVISIBLE);
//                }

//                tvPet.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
//                rGroup.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
//                ivPet.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);

                int result = isChecked ? View.VISIBLE : View.INVISIBLE;
                tvPet.setVisibility(result);
                rGroup.setVisibility(result);
                ivPet.setVisibility(result);
            }
        });

        // 2. 라디오버튼 선택 시 선택된 라디오버튼에 해당하는 이미지를 ivPet에 표시
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                // 동등 비교를 한다??? switch
//                switch (id){
//                    case R.id.rbDog: ivPet.setImageResource(R.drawable.dog); break;
//                    case R.id.rbCat: ivPet.setImageResource(R.drawable.cat); break;
//                    case R.id.rbRabbit: ivPet.setImageResource(R.drawable.rabbit); break;
//                }

                int image = 0;
                switch (id){
                    case R.id.rbDog: image = R.drawable.dog; break;
                    case R.id.rbCat: image = R.drawable.cat; break;
                    case R.id.rbRabbit: image = R.drawable.rabbit; break;
                }
                ivPet.setImageResource(image);
            }
        });


        // 3. 초기화 버튼 클릭 시 숨김 처리 (tvPet, rGroup, ivPet) 및 체크박스와 라디오버튼 선택 해제
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tvPet.setVisibility(View.INVISIBLE);
//                rGroup.setVisibility(View.INVISIBLE);
//                ivPet.setVisibility(View.INVISIBLE);

                checkStart.setChecked(false);
                rGroup.clearCheck();
                ivPet.setImageResource(0);
            }
        });


        // 4. 종료 버튼 클릭 시 프로그램 끝내기(종료)
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 프로그램을 종료하려면 finish() 메서드 호출
//                finish();
                finishAndRemoveTask();
            }
        });


    }
}