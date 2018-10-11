package com.example.user.a2_parametersenderexam;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText1, editText2;
    Button button1, button2;
    TextView textView;
    AsyncHttpClient client;
    HttpResponse httpResponse;
    String URL_GET = "http://192.168.47.12/send_get.php";
    String URL_POST = "http://192.168.47.12/send_post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        client = new AsyncHttpClient();
        httpResponse = new HttpResponse(this);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 파라미터 정보를 저장할 수 있는 객체
        RequestParams params = new RequestParams();
        // 파라미터 정보 추가 (key, value)
        String user_id = editText1.getText().toString().trim();
        String user_pw = editText2.getText().toString().trim();
        params.put("user_id", user_id);
        params.put("user_pw", user_pw);

        switch (v.getId()) {
            case R.id.button1:  // GET 방식의 데이터 통신
                client.get(URL_GET, params, httpResponse);
                break;
            case R.id.button2:  // POST 방식의 데이터 통신
                client.post(URL_POST, params, httpResponse);
                break;
        }
    }
    class HttpResponse extends AsyncHttpResponseHandler {
        ProgressDialog dialog;
        Activity activity;

        public HttpResponse(Activity activity) {
            this.activity = activity;
        }


        /**
         * 통신 시작시에 실행된다
         */

        @Override
        public void onStart() {
            dialog = new ProgressDialog(activity);
            dialog.setMessage("잠시만 기다려주세요...");
            dialog.setCancelable(false);
            dialog.show();
            Log.d("[TEST]", getRequestURI().toString());
        }

        /**
         * 서버의 응답을 수신한 경우 실행된다
         */

        @Override
        public void onSuccess(String content) {
            textView.setText(content);
        }

        /**
         * 서버의 응답 수신에 실패한 경우 실행된다
         */
        @Override
        public void onFailure(int statusCode, Throwable error, String content) {
            String result = "결과 코드 : " + statusCode + "\n"
                    + "에러메시지 : " + error.getLocalizedMessage() + "\n"
                    + "에러내용 : " + content;
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
        }

        /**
         * 성공, 실패 여부에 상관없이 통신이 종료되면 실행된다
         */
        @Override
        public void onFinish() {
            dialog.dismiss();       // 강제 종료
            dialog = null;
        }

    }
}
