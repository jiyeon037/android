package com.example.user.a3_activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.a3_activityexam.model.Answer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText1, editText2, editText3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 입력값 받기
        String id = editText1.getText().toString().trim();
        String password = editText2.getText().toString().trim();
        String email = editText3.getText().toString().trim();

        Answer answer = new Answer(id, password, email);
        // 화면 이동을 위한 명시적 인텐트
        Intent intent = new Intent(this,ResultActivity.class);
        // 인텐트에 객체 포함시키기
        intent.putExtra("answer", answer);
        // 화면 이동요청
        startActivity(intent);

        }
}
