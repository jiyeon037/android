package com.example.user.a8_edittextexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// 1. 상속 + 메소드 구현
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // 2. 객체 선언
    EditText editText1, editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 3. 객체 할당
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button);
        // 4. 이벤트 설정
        button.setOnClickListener(this);
    }
    // 5. 이벤트 처리
    @Override
    public void onClick(View v) {
        // 사용자가 입력한 내용 얻기
        String id = editText1.getText().toString().trim();
        String pw = editText2.getText().toString().trim();
        // 입력 여부 검사
        if(id.equals("")||pw.equals("")){
            Toast.makeText(this,"아이디나 비밀번호가 입력되지 않았습니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        int num_int = Integer.parseInt(pw);         // 숫자로만 구성된 문자열을 정수로 변환
        double num_double = Double.parseDouble(pw); // 숫자로만 구성된 문자열을 실수로 변환
        String result = num_int + " / " + num_double;
        //String result = id + " / " + pw;
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
}
