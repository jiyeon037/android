package com.example.user.a2_preexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(this);
    }
    // 이 화면으로 진입하는 경우
    @Override
    protected void onResume() {
        super.onResume();       // 반드시 부모클래스의 원래 기능을 호출해야 한다.
        // 데이터를 읽는다.
        // 1) 공통 정보 관리 객체 생성 --> 파일 이름, 접근 권한 : CONFIG.xml
        SharedPreferences pref = getSharedPreferences("CONFIG", MODE_PRIVATE); // S..P.. : 설정 파일을 관리하는 클래스 (get~ 은 액티비티 안에 있는 메소드임)
        // 2) 변수값 추출
        String name = pref.getString("name", "설정안됨");
        String id = pref.getString("id", "알 수 없음");
        // 3) 결과 출력
        String result = "이름 : " + name + "\n" + "아이디 : " + id;
        textView.setText(result);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }
}
