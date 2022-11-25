package com.example.and1115_dialog2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int selectedItem = -1; // 선택 항목에 대한 인덱스를 저장할 변수 선언
    // => 리스너 구현체 내부에서 접근하기 위해서는 final로 선언하거나 멤버변수로 선언해야함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showListDialog(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("좋아하는 과목은?");

        // 목록 대화상자 출력을 위해 목록으로 사용할 데이터를 배열로 생성
        String[] listItems = {"Java", "JSP", "Android", "SPRING"};

        dialog.setItems(listItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, listItems[i], Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    // 라디오 형태
    public void showRadioListDialog(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("좋아하는 과목은?");
        String[] listItems = {"Java", "JSP", "Android", "SPRING"};

        dialog.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedItem = i;
            }
        });

        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, listItems[selectedItem], Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    // 체크박스 형태
    public void showCheckListDialog(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("좋아하는 과목은?");
        String[] listItems = {"Java", "JSP", "Android", "SPRING"};
        boolean[] checkItems = {true, true, false, false};

        dialog.setMultiChoiceItems(listItems, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index, boolean isChecked) {
                //Toast.makeText(MainActivity.this, i + " : " + b, Toast.LENGTH_SHORT).show();
                // checkItems[index] = isChecked;
            }
        });

        dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index) {
                String items = "";
                for(int i = 0; i < listItems.length; i++){
                    if(checkItems[i]){
                        items += listItems[i] + " ";
                    }
                }

                Toast.makeText(MainActivity.this, items, Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}