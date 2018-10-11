package com.example.user.a3_activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.a3_activityexam.model.Answer;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    Answer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView = (TextView) findViewById(R.id.textView);
        Intent fromIntent = getIntent();
        answer = (Answer)fromIntent.getSerializableExtra("answer");
        String result = "아이디 : " + answer.getId() + "\n"
                + "비밀번호 : " + answer.getPasswd() + "\n"
                + "이메일 : " + answer.getEmail() + "\n";
        textView.setText(result);
    }
}
