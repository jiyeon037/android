package com.example.user.a4_rolloverbuttonexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// 2. 직접 상속 방법으로 이벤트 처리 (코드가 좀 더 심플해진다)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String msg = "";

        switch (v.getId()){
            case R.id.button1: msg="버튼1";
            break;
            case R.id.button2: msg="버튼2";
            break;
            case R.id.button3: msg="버튼3";
            break;
        }

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}


//// 1. 익명 클래스의 방법으로 이벤트 처리 (부속화면에서 주로 사용)
//public class MainActivity extends AppCompatActivity {
//
//    Button button1, button2, button3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        button1 = (Button)findViewById(R.id.button1);
//        button2 = (Button)findViewById(R.id.button2);
//        button3 = (Button)findViewById(R.id.button3);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"버튼1",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"버튼2",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"버튼3",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
