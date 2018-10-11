package com.example.user.a2_buttonexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

// 2. Listener를 직접 상속받아서 사용
/** (1) 터치 처리를 위하여 OnClickListener 인터페이스 상속 후,
 * onClick 메소드를 재정의한다.
 * */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /** (2) 사용하고자 하는 컴포넌트에 대한 객체 선언 */
    // 클래스 이름은 컴포넌트의 XML태그 이름
    Button button1, button2;
    ImageButton imageButton1, imageButton2;

    @Override
    // onCreate(MainActivity this, Bundle savedInstanceState)

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /** (3) 선언한 객체에 대한 할당 */
        // 객체이름 = (클래스 이름) findViewById(아이디값)
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
        /** (4) 버튼에 이벤트 연결 */
        // OnClickListener를 상속받는 객체를 파라미터로 전달한다.
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
    }

    /** (5) 이벤트 처리
     * OnClickListener 상속에 의하여 재정의 된 메소드 */
    @Override
    public void onClick(View v) {
        String msg = "";
        // 파라미터로 전달되는 View 객체는 눌러준 버튼 자신
        // 어떤 버튼이 눌러졌는지 판별하기 위한 id값을 추출한다.
        switch (v.getId()){
            case R.id.button1: msg = "button1";
                break;
            case R.id.button2: msg = "button2";
                break;
            case R.id.imageButton1: msg = "imagebutton1";
                break;
            case R.id.imageButton2: msg = "imagebutton2";
                break;
        }
        // 토스트 메시지의 사용
        // 파라미터 : Context, 내용, 표시시간
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show(); // 같은 클래스기때문에 this로 액티비티 줌
    }
}


//public class MainActivity extends AppCompatActivity {
//
//    // 1. Listener를 상속받는 클래스 만들기
//
//    /** 터치 처리를 위하여 OnClickListener 인터페이스 상속 후,
//     *  onClick 메소드를 재정의 한다.
//     */
//    class Click implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//
//            int id = v.getId(); // 전달된 자식클래스의 아이디 추출
//            String msg = "";
//            switch(id) {
//                case R.id.button1: msg = "버튼1";
//                    break;
//                case R.id.button2: msg = "버튼2";
//                    break;
//                case R.id.imageButton1: msg = "이미지버튼1";
//                    break;
//                case R.id.imageButton2: msg = "이미지버튼2";
//                    break;
//            }
//            Toast.makeText(getApplicationContext(),"버튼1",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /** 2. 사용하고자 하는 컴포넌트에 대한 객체 선언 */
//    // 클래스 이름은 컴포넌트의 XML 태그 이름
//    Button button1, button2;
//    ImageButton imageButton1, imageButton2;
//    Click click;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        /** 3. 선언한 객체에 대한 할당 */
//        // 객체이름 = (클래스 이름)findViewById(아이디값)
//        button1 = (Button)findViewById(R.id.button1);
//        button2 = (Button)findViewById(R.id.button2);
//        imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
//        imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
//        click = new Click();
//
//        /** 4. 버튼에 이벤트 연결 */
//        // OnClickListener를 상속받는 객체를 파라미터로 전달한다.
//        button1.setOnClickListener(click);
//        button2.setOnClickListener(click);
//        imageButton1.setOnClickListener(click);
//        imageButton2.setOnClickListener(click);
//    }
//}