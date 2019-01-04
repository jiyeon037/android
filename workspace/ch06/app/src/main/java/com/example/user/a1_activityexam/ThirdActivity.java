package com.example.user.a1_activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    Button button1, button2;
    String name;    // 이전 화면에서 넘어온 값을 저장할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        editText = (EditText)findViewById(R.id.editText);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        // 이전 화면의 Intent
        Intent fromIntent = getIntent();
        name = fromIntent.getStringExtra("name");

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                // EditText에 사용자가 입력한 값
                String input = editText.getText().toString().trim();
                int age = Integer.parseInt(input);
                // 화면 이동을 위한 명시적 인텐트
                Intent intent = new Intent(this, ResultActivity.class);
                // Intent에 파라미터에 이전 화면에서 넘어온 값과 이번 화면에서
                // 생성된 값을 누적하여 추가시킨다.
                intent.putExtra("name", name);
                intent.putExtra("age",age);
                // 화면 이동 요청
                startActivity(intent);
                break;
            case R.id.button2:
                // 현재 화면의 종료 --> 이전 화면 돌아가기
                finish();
                break;
        }
    }
}
