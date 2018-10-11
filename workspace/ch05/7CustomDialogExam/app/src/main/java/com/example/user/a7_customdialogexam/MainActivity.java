package com.example.user.a7_customdialogexam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showLoginDialog();
    }
    /** 로그인 다이얼로그를 표시한다. */
    private void showLoginDialog() {
        // "/res/layout/dialog.xml" 파일을 로드하기
        final View loginView = getLayoutInflater().inflate(R.layout.dialog, null); // inflate로 객체화시켜서 메모리에 저장
        final EditText editText1 = (EditText) loginView.findViewById(R.id.editText1);
        final EditText editText2 = (EditText) loginView.findViewById(R.id.editText2);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 제목 설정
        builder.setTitle("로그인");
        // 아이콘 이미지 설정
        builder.setIcon(R.mipmap.ic_launcher);
        // 하드웨어 BackKey가 눌러졌을 때, 창이 닫히지 않도록 함
        builder.setCancelable(false);
        // Dialog에 Message 대신, XML 레이아웃을 포함시킨다.
        builder.setView(loginView);
        // OK 버튼의 추가 및 이벤트 정의
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user_id = editText1.getText().toString().trim();
                String user_pw = editText2.getText().toString().trim();
                String result = "아이디 : " + user_id + " / 비밀번호 : " + user_pw;

                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            }
        });

        // CANCEL 버튼의 추가 및 이벤트 정의
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "CANCEL 버튼 눌러짐",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // 설정한 정보로 창을 생성
        AlertDialog alertDialog = builder.create();
        // 창을 화면에 표시
        alertDialog.show();
    }
}
