package com.example.user.a2_senderexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText1, editText2;
    Button button;
    String phoneNo,message;
    // android.telephony.SmsManager : 문자메세지 관리 객체
    SmsManager sms;
    // 눌림 상태 확인 : 여러번 전송되는 것 방지용
    boolean pressCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);
        sms = SmsManager.getDefault();
        button.setOnClickListener(this);
        // 퍼미션 체크를 먼저 해야한다.
        permissionCheck();
    }

    private void permissionCheck() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // 사용자가 입력한 내용으로 설정
        phoneNo = editText1.getText().toString().trim();
        message = editText2.getText().toString().trim();
    }

    // 하드웨어 키가 눌릴 때 호출된다.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(phoneNo == null || message == null) {
                    Toast.makeText(this,"전화번호나 메세지가 설정되지 않았습니다.",Toast.LENGTH_SHORT).show();
                    return false;
                }
                if(pressCheck == false) {
                    // 문자 발송하기
                    sms.sendTextMessage(phoneNo, null, message, null, null);
                    pressCheck = true;
                }
                return true;

            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
        }
        return false;
    }

    // 하드웨어 키를 뗄 때 호출된다.
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        pressCheck = false;
        return true;
    }
}
