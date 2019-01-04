package com.example.user.a3_simplejsonexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    TextView textView;
    AsyncHttpClient client;
    HttpResponse httpResponse;
    String URL_JSON = "http://192.168.47.12/simple.json" + "";

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
        client.get(URL_JSON, httpResponse);
    }

    class HttpResponse extends AsyncHttpResponseHandler {
        @Override
        public void onSuccess(String content) {
            try {
                JSONObject json = new JSONObject(content);
                JSONObject device = json.getJSONObject("device");
                String name = device.getString("name");
                String type = device.getString("type");
                String result = name + " / " + type;
                textView.setText(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, Throwable error, String content) {
            textView.setText(content);
        }
    }
}










