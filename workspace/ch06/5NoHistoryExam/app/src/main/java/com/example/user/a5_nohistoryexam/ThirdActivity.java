package com.example.user.a5_nohistoryexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        // 대상 Activity가 호춛되면서 이전의 History 내역을 삭제한다.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // 대상 Activity가 기존에 실행되고 있었다면,
        // 새로 Activity를 생성하지 않고, 실행중인 화면을 불러온다.
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        // finish();
    }
}
