package com.example.user.a4_jsonlistexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.a4_jsonlistexam.adapter.DeviceAdapter;
import com.example.user.a4_jsonlistexam.model.Device;
import com.example.user.a4_jsonlistexam.response.DeviceResponse;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    List<Device> list;
    DeviceAdapter adapter;
    ListView listView;
    AsyncHttpClient client;
    DeviceResponse response;
    String URL_JSON = "http://192.168.47.12/list.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new DeviceAdapter(this,R.layout.list_item,list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        client = new AsyncHttpClient();
        response = new DeviceResponse(this, adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        client.get(URL_JSON, response);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device item = adapter.getItem(position);
        String result = item.getName() + " / " + item.getType();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
