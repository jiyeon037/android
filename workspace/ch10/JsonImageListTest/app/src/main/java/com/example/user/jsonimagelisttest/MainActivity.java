package com.example.user.jsonimagelisttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.jsonimagelisttest.adapter.JobAdapter;
import com.example.user.jsonimagelisttest.model.Job;
import com.example.user.jsonimagelisttest.response.JobResponse;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Job> list;
    JobAdapter adapter;
    ListView listView;
    AsyncHttpClient client;
    JobResponse response;
    String URL_JSON = "http://192.168.47.27/job.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new JobAdapter(this, R.layout.list_item, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        client = new AsyncHttpClient();
        response = new JobResponse(this, adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear(); // 기존 데이터 지움
        client.get(URL_JSON, response); // 서버에다가 요청하고 바로 구동
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Job item = adapter.getItem(position);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
/*        String result = item.getNum() + "\n"
                + item.getImg() + "\n"
                + item.getSubject() + "\n"
                + item.getContent();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show(); */
    }
}
