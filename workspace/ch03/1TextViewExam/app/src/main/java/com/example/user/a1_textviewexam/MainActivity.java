package com.example.user.a1_textviewexam;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Dimension;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /** (1) 사용할 컴포넌트에 대한 객체를 선언한다 **/
    TextView textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main.xml에 포함시킨 컴포넌트에 대한 객체를 할당한다.
        setContentView(R.layout.activity_main);
        /** (2) 메모리에 만들어져있는 클래스 찾아오기 **/
        // View를 id값에 의해서 찾는다. --> findViewById(id값)
        // 상위 클래스에 대한 객체(View)로 리턴되므로, 명시적 형변환 필요함
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);

        /** (3) 내용 지정하기 */
        // 내용을 직접 지정하기
        textView3.setText("안녕하세요. 안드로이드");

        // strings.xml 파일의 내용을 참조하기
        textView4.setText(R.string.my_text);

        /** (4) 글자 색상 지정하기 */
        // 색상값 지정하기
        int color = Color.rgb(255,0,0);
        textView3.setTextColor(color);
        // colors.xml 파일의 내용을 참조하기
        Resources resources = getResources();
        int color2 = resources.getColor(R.color.my_blue);
        textView4.setTextColor(color2);

        /** (5) 배경 색상 지정하기 */
        textView3.setBackgroundColor(Color.rgb(255, 255, 0));
        textView4.setBackgroundResource(R.color.my_orange);

        /** (6) 글자 크기 속성 */
        textView3.setTextSize(30);
        float size = resources.getDimension(R.dimen.my_size);
        textView4.setTextSize(Dimension.DP,size);

        /** (7) Single Line 속성 */
        textView4.setSingleLine(true);

        /** (8) Ellipsize 속성 */
        textView4.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    }
}
