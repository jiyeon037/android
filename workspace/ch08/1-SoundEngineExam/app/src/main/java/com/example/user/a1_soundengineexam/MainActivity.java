package com.example.user.a1_soundengineexam;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** 음성 인식 종류를 구별하기 위한 값 */
    private final int SEARCH = 1;   // 음성 검색 (구글)
    private final int SPEECH = 2;   // 음성 인식
    Button button1, button2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:  // 구글검색
                callSoundEngine(SEARCH);
                break;
            case R.id.button2:  // 음성인식
                callSoundEngine(SPEECH);
                break;
        }
    }
    // 음성인식을 위한 내장 App을 호출한다.
    private void callSoundEngine(int type) {
        String intent_action = null;
        if(type == SEARCH) {
            intent_action = RecognizerIntent.ACTION_WEB_SEARCH;
        } else {
            intent_action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH;
        }
        try {
            Intent intent = new Intent(intent_action);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성 인식");
            if (type == SEARCH) {       // 웹은 결과를 받지 못하므로, 그냥 호출
                startActivity(intent);
            } else {                    //  음성인식 결과를 받기위한 호출
                startActivityForResult(intent, type);
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "이 기능을 지원하지 않는 단말입니다.", Toast.LENGTH_SHORT).show();
        }
    }
    // 음성인식인 경우, 화면 이동에 대한 결과를 받기 위한 메소드 재정의
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SPEECH) {
            if(data != null) {
                // 인식된 모든 결과 리스트
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                // 첫번째 데이터가 가장 정확도 높다.
                String str = results.get(0);
                // 출력
                textView.setText(str);
            }
        }
    }
}
