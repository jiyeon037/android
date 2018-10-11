package com.example.user.a3buttoneventexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// 5. 안드로이드 방법
public class MainActivity extends AppCompatActivity {
    /** (1) 버튼 객체를 선언한다. */
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        Toast.makeText(this,"버튼",Toast.LENGTH_SHORT).show();
    }
}


//// 3. 익명클래스를 사용
//public class MainActivity extends AppCompatActivity {
//    /** (1) 버튼 객체를 선언한다. */
//    Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        /** (2) 버튼 객체를 할당한다. */
//        button = (Button) findViewById(R.id.button);
//        /** (3) 버튼 이벤트 처리 */
//        button.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"button",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}


//// 4. 다형성을 이용한 익명클래스를 이용
//public class MainActivity extends AppCompatActivity {
//    /** (1) 버튼 객체를 선언한다. */
//    Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        /** (2) 버튼 객체를 할당한다. */
//        button = (Button)findViewById(R.id.button);
//        /** (3) 버튼 이벤트 처리 */
//        View.OnClickListener click = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"버튼",Toast.LENGTH_SHORT).show();
//            }
//        };
//              // OnClickListener를 상속받는 객체를 파라미터로 전달한다.
//        button.setOnClickListener(click);
//    }
//}
