package com.example.user.a1_simplespinnerexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/** 이벤트 처리를 위한 인터페이스 상속 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    Spinner spinner;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spinner);
        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        spinner.setOnItemSelectedListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = (String) spinner.getSelectedItem();
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    /**
     * spinner의 항목이 선택되었을 경우 호출된다.
     * @param parent - spinner 객체
     * @param view - 선택된 위치를 구성하는 View
     * @param position - 선택된 위치에 대한 인덱스
     * @param id - 선택된 위치에 대한 인덱스
     */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = (String) parent.getSelectedItem();
        String result = position + "번째 항목 >> " + selected;
        textView.setText(result);
    }
/** spinner의 선택 상태가 해제될 경우 호출된다. */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
