package com.example.user.a6_checkboxexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
// 1. 상속 + 메소드 구현

public class MainActivity extends AppCompatActivity
implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
// 2. 객체 선언
    Button button;
    CheckBox checkBox1, checkBox2, checkBox3;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 3. 객체 할당
        button = (Button) findViewById(R.id.button);
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        textView2 = (TextView)findViewById(R.id.textView2);
        // 4. 이벤트 설정
        button.setOnClickListener(this);
        checkBox1.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        checkBox3.setOnCheckedChangeListener(this);
    }
    // 5. 이벤트 처리
    // 체크된 값 읽어오기
    @Override
    public void onClick(View v) {
        String msg = "";
        // 체크박스의 상태 점검 --> true = 체크됨 / false = 체크안됨
        if(checkBox1.isChecked()){
            // text 속성에 적용되어 있는 값을 문자열로 읽기
            msg += checkBox1.getText().toString() + " ";
        }
        if(checkBox2.isChecked()){
            msg += checkBox2.getText().toString() + " ";
        }
        if(checkBox3.isChecked()){
            msg += checkBox3.getText().toString();
        }
        if (msg.equals("")){
            msg = "체크된 것이 없습니다";
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /** (5) (1)번의 결과로 추가된 메소드 --> 체크박스의 선택상태가 변경될 경우, 자동 실행된다.
     * @param buttonView - 체크박스 자신
     * @param isChecked - true = 체크됨 / false = 체크안됨
     */

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String msg = buttonView.getText().toString();
        if(isChecked){
            msg += "가 체크되었습니다.";
        } else {
            msg += "가 체크 해제 되었습니다.";
        }
        textView2.setText(msg);
    }
}
