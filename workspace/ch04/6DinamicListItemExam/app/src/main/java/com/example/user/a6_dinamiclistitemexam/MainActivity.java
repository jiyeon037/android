package com.example.user.a6_dinamiclistitemexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.a6_dinamiclistitemexam.adapter.ItemAdapter;
import com.example.user.a6_dinamiclistitemexam.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /** 일련번호를 처리하기 위한 전역변수. 이 값이 1씩 증가하면서 객체에 할당 될 것이다. */
    public static int NUM = 0;
    /** 리스트뷰 관련 객체 선언 */
    List<Item> list;
    ItemAdapter adapter;
    ListView listView;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new ItemAdapter(this, R.layout.list_item, list);
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        listView.setAdapter(adapter);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String input = editText.getText().toString().trim();
        NUM++;
        // 사용자가 입력한 내용을 List에 역순으로 추가 --> ListView에 출력
        // adapter.add(객체) : 뒤에 추가시켜 나감, 0 -> 1 -> 2, 최신 저장값은 제일 뒤에
        // adapter.insert(객체) : 특정 위치에 삽입, 0 -> 1 -> 2, 최신 저장값은 제일 앞에
        Item item = new Item(NUM, input);
        adapter.insert(item,0);
        // EditText에 입력된 내용 삭제
        editText.setText("");
    }
}
