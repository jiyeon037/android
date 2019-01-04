package com.example.user.a1_activityexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = (EditText)findViewById(R.id.editText);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1:
                // EditText에 사용자가 입력한 값
                String name = editText.getText().toString().trim();
                // 화면 이동을 위한 명시적 인텐트
                Intent intent = new Intent(this, ThirdActivity.class);
                // Intent에 파라미터 추가하기
                intent.putExtra("name", name); // intent에 담아버리기
                // 화면 이동 요청
                startActivity(intent);
                break;
            case R.id.button2:
                finish();
                break;
        }
    }
}
