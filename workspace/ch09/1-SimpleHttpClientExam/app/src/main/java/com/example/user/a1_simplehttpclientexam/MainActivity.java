package com.example.user.a1_simplehttpclientexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    TextView textView;
    AsyncHttpClient client;      // 요청 통신 객체 선언
    HttpResponse httpResponse;  // 응답 처리 객체 선언 (이너클래스로 해줄 것)
    String URL = "http://192.168.47.12/hello.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(this);

        client = new AsyncHttpClient();
        httpResponse = new HttpResponse();
    }

    @Override
    public void onClick(View v) {
        // 주어진 URL로 통신을 시도한다. client 객체는 웹 서버에게 요청을 하고,
        // httpResponse 객체는 응답을 기다렸다가 명령을 수행한다.
        client.get(URL, httpResponse);
    }
    // 통신 결과를 받아서 처리할 클래스 - inner class 형태로 정의한다
    class HttpResponse extends AsyncHttpResponseHandler {
        // 서버로부터 200 코드(OK 코드)가 오면 자동호출
        @Override
        public void onSuccess(String content) {
            textView.setText(content);
        }
        // 서버로부터 200 코드가 아닌 것이 오면 자동호출
        @Override
        public void onFailure(int statusCode, Throwable error, String content) {
            String result = "결과 코드 : " + statusCode + "\n"
                    + "에러메시지 : " + error.getLocalizedMessage() + "\n"
                    + "에러내용 : " + content;
            textView.setText(result);
        }
    }
}










