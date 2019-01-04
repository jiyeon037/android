package com.example.user.a2_preexam;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {
    EditText editText1, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
    }
    protected void onPause() {

        super.onPause();    // 반드시 부모클래스의 원래 기능을 호출해야 한다. 백키 누르는 순간 호출됨
        // 사용자가 입력한 값
        String name = editText1.getText().toString().trim();
        String id = editText2.getText().toString().trim();
        // 저장 처리
        // 1) 공통 정보 관리 객체 생성 --> 파일이름, 접근 권한
        SharedPreferences pref = getSharedPreferences("CONFIG", MODE_PRIVATE);
        // 2) 저장, 수정 객체 생성
        SharedPreferences.Editor editor = pref.edit();
        // 3) 내용 쓰기
        editor.putString("name", name);
        editor.putString("id", id);
        // 4) 저장
        editor.commit();    // CONFIG.xml 파일 만들어짐
    }
}
