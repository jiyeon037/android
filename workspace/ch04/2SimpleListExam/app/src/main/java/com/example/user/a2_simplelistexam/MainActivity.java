package com.example.user.a2_simplelistexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }
    /**
     * 
     * ListView의 항목이 선택되었을 경우 호출된다.
     * @param parent - ListView 객체
     * @param view - 선택된 위치를 구성하는 View
     * @param position - 선택된 위치에 대한 인덱스
     * @param id - 선택된 위치에 대한 인덱스
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);
        Toast.makeText(this,item,Toast.LENGTH_SHORT).show();
    }
}
