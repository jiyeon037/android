package com.example.user.jsonimagelisttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.jsonimagelisttest.model.Job;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    TextView textView1, textView2;
    ImageView imageView;
    Job item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent fromIntent = getIntent();
        item = (Job)fromIntent.getSerializableExtra("item");

        textView1.setText(item.getSubject());
        textView2.setText(item.getContent());
        ImageLoader.getInstance().displayImage(item.getImg(),imageView);
    }
}
