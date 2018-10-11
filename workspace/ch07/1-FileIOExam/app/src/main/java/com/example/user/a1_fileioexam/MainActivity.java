package com.example.user.a1_fileioexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.a1_fileioexam.helper.FileHelper;

import org.w3c.dom.Text;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button1, button2;
    TextView textView2;

    @Override
    // 널포인트입셉션은 클래스가 초기화 되지 않았을 때 나는 오류
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        textView2 = (TextView) findViewById(R.id.textView2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        permissionCheck();
    }

    private void permissionCheck() { // developer.android.com 에서 가져온 것. 퍼미션체크 안하면 에러도 안나고 동작도 안됨.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // 저장하거나 읽어올 파일의 경로
        String fileName = "mymemo.txt";
        File dir = Environment.getExternalStorageDirectory();
        String filePath = dir.getAbsolutePath() + "/" + fileName;
        String encType = "utf-8";

        switch (v.getId()) {
            case R.id.button1:  // 저장하기
                String content = editText.getText().toString().trim();
                boolean result = FileHelper.getInstance().writeString(filePath, content, encType);
                String msg = "저장 성공";
                if(!result) {
                    msg = "저장 실패";
                }
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:  // 불러오기
                String read = FileHelper.getInstance().readString(filePath,encType);
                textView2.setText(read);
                break;
        }
    }
}
