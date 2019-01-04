package com.example.user.a1_activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    // 이전 화면에서 넘어온 값을 저장할 변수
    String name;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = (TextView) findViewById(R.id.textView);
        Intent fromIntent = getIntent(); // 끄집어내기
        name = fromIntent.getStringExtra("name");
        age = fromIntent.getIntExtra("age",0); // 값이 없으면 디폴트(null)값 저장함으로 0으로 지정해줌
        String result = "<font color='blue'>" + name + "님</font>의 나이는 " +
                "<font color='red'>" + age + "세</font> 입니다.";
        textView.setText(Html.fromHtml(result)); // 문자열을 html어로 번역하여 텍스트로 출력
    }
}
