package com.example.user.a1_jsonimagelistitem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.a1_jsonimagelistitem.adapter.ColumnAdapter;
import com.example.user.a1_jsonimagelistitem.model.Column;
import com.example.user.a1_jsonimagelistitem.response.ColumnResponse;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Column> list;
    ColumnAdapter adapter;
    ListView listView;
    AsyncHttpClient client;
    ColumnResponse response;
    String URL_JSON = "http://192.168.47.12/column.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new ColumnAdapter(this, R.layout.list_item, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        client = new AsyncHttpClient();
        response = new ColumnResponse(this, adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        client.get(URL_JSON, response);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Column item = adapter.getItem(position);
        String result = item.getNum() + "\n"
                + item.getImg() + "\n"
                + item.getSubject() + "\n"
                + item.getContent();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
