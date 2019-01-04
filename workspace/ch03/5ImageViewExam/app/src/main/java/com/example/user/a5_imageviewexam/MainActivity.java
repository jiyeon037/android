package com.example.user.a5_imageviewexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

// 1. 상속 + 메소드 구현
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // 2. 사용할 객체 선언
    Button button1, button2;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 3. 객체 할당
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        imageView = (ImageView)findViewById(R.id.imageView);
        // 4. 이벤트 세팅
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    // 5. 이벤트 처리
    // 눌러진 버튼에 따라서 ImageView에 표시되는 그림과
    // 이미지 크기 설정 방식 변경
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                imageView.setImageResource(R.drawable.img1);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case R.id.button2:
                imageView.setImageResource(R.drawable.img2);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
        }
    }
}
