package com.example.user.a7_radioexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
    RadioGroup radioGroup;
    TextView textView2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        textView2 = (TextView)findViewById(R.id.textView2);
        button = (Button)findViewById(R.id.button);

        radioGroup.setOnCheckedChangeListener(this);
        button.setOnClickListener(this);
    }
    // 체크된 라디오버튼의 값 읽어오기
    @Override
    public void onClick(View v) {
        // 선택된 라디오 버튼의 ID값 얻기
        int checkedId = radioGroup.getCheckedRadioButtonId();
        // 획득한 ID값으로 라디오 버튼 객체 할당
        RadioButton radioButton = (RadioButton) findViewById(checkedId);
        //토스트메세지 출력
        Toast.makeText(this,radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    /**
     * (5) 1번의 결과로 추가된 메소드
     * @param group - 라디오 그룹 객체
     * @param checkedId - 선택된 RadioButton의 id
     * */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 파라미터로 전달된 ID값으로 라디오 버튼 객체 할당
        RadioButton radioButton = (RadioButton)findViewById(checkedId);
        // 텍스트뷰에 내용 출력
        textView2.setText(radioButton.getText().toString());
    }
}
