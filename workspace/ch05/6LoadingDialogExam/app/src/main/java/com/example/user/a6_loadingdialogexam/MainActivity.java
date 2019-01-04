package com.example.user.a6_loadingdialogexam;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                showLoadingDialog();
                break;
            case R.id.button2:
                showTransLoadingDialog();
                break;
        }
    }

    private void showLoadingDialog() {
        // 로딩 다이얼로그 객체
        ProgressDialog dialog = new ProgressDialog(this);
        // 다이얼로그에 표시할 메시지
        dialog.setMessage("잠시만 기다려 주세요...");
        // 다이얼로그가 닫힐 경우에 대한 이벤트 정의 (필요에 따라서 추가한다.)
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(), "로딩 취소됨", Toast.LENGTH_SHORT).show();
            }
        });
        // 다이얼로그를 화면에 표시하기
        dialog.show();
    }
    /** 아이폰 스타일의 투명한 로딩창 표시하기 */
    private void showTransLoadingDialog() {
        //스타일 리소스를 적용한 다이얼로그 객체
        Dialog dialog = new Dialog(this, R.style.transDialog);
        // 프로그레스바 컴포넌트를 레이아웃 XML없이 직접 생성
        // --> 파라미터는 Context객체를 요구하므로, Activity를 전달한다.
        ProgressBar progressBar = new ProgressBar(this);
        // 다이얼로그에 프로그레스바 추가
        // --> 파라미터 : 프로그레스바 컴포넌트, 가로/세로 사이즈 정보를 갖는 객체
        // android.view.ViewGroup.LayoutParams
        dialog.addContentView(progressBar, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        // 다이얼로그가 닫힐 경우에 대한 이벤트 정의 (필요에 따라서 추가한다.)
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(),"로딩 취소됨",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // 다이얼로그를 화면에 표시하기
        dialog.show();
    }
}
