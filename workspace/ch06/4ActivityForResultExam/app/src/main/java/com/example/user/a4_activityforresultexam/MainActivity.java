package com.example.user.a4_activityforresultexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String memo = textView.getText().toString();
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("memo", memo);
        // 수정 내용을 되돌려 줄 것을 예약한 화면 이동
        // --> 이동할 화면에 대하여 일련번호를 지정한다.
        startActivityForResult(intent,100);
    }

    /**
     * startActivityResult()에 의해서 화면이 이동되었다가
     * 이 화면으로 되돌아 올 경우, 자동으로 실행된다.
     * @param requestCode - Activity에 설정한 일련번호
     * @param resultCode - 되돌아 오기 전, setResult()에서 설정한 결과값 (OK / CANCEL)
     * @param data - 되돌아 오기 전, setResult()에 설정한 Intent
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100) {
            if(resultCode == RESULT_OK) {
                String edit = data.getStringExtra("edit");
                textView.setText(edit);
            }
        }
    }
}
