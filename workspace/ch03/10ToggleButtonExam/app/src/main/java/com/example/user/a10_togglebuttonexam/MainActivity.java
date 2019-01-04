package com.example.user.a10_togglebuttonexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/** (1) 이벤트 처리를 위한 인터페이스 상속 후, 메소드 재정의 */
public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    /** (2) 사용할 컴포넌트의 객체 선언 */
    TextView textView1, textView2;
    ToggleButton toggleButton1, toggleButton2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /** (3) 컴포넌트에 대한 객체 할당 */
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        button = (Button) findViewById(R.id.button);
        /** (4) 이벤트 설정 */
        toggleButton1.setOnCheckedChangeListener(this);
        toggleButton2.setOnCheckedChangeListener(this);
        button.setOnClickListener(this);
    }
    /** 이벤트 처리 */
    @Override
    public void onClick(View v) {
        // 각 ToggleButton의 선택상태 얻기
        boolean checked1 = toggleButton1.isChecked();
        boolean checked2 = toggleButton2.isChecked();
        String msg1 = "", msg2 = "";
        if(checked1){
            msg1 = "설정됨";
        } else {
            msg1 = "설정해제";
        }
        if(checked2){
            msg2 = "설정됨";
        } else {
            msg2 = "설정해제";
        }
        String result = "기본버튼 : " + msg1 + " / 이미지 버튼 : " + msg2;
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    // 토글버튼의 상태가 변경될 때마다 isChecked 값에 ON(true)/OFF(false) 상태가 전달된다.

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.toggleButton1:
                // 상위클래스 형태로 전달되므로, 기능을 사용하기 위해서는 명시적 객체 형변환
                ToggleButton toggleButton = (ToggleButton) buttonView;
                // 각 상태에 대해서 설정된 텍스트 출력
                if(isChecked){
                    textView1.setText(toggleButton.getTextOn());
                }else{
                    textView2.setText(toggleButton.getTextOff());
                }
                break;
            case R.id.toggleButton2:
                //textOn, textOff 속성에 공백을 설정하였으므로, 메소드로 값을 얻어올 수 있다
                if(isChecked){
                    textView1.setText("좋아요 눌러짐");
                }else{
                    textView2.setText("좋아요 취소됨");
                }
                break;
        }
    }
}
