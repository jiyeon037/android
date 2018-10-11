package com.example.user.a6_dinamiclistitemexam.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.a6_dinamiclistitemexam.R;
import com.example.user.a6_dinamiclistitemexam.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    Activity activity;
    int resource;
    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        activity = (Activity)context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("[TEST]","position = " + position);
        // 1줄 화면 만들기
        View itemView = convertView;
        if(itemView == null){
            itemView = activity.getLayoutInflater().inflate(resource,null);
        }
        // 1줄 데이터 꺼내오기
        final Item item = getItem(position);
        // 1줄 화면 + 1줄 데이터
        if(item != null){
          Button button = (Button) itemView.findViewById(R.id.button);
          TextView textView = (TextView) itemView.findViewById(R.id.textView);
          textView.setText(item.getNum() + " >> " + item.getName());
          // 버튼의 이벤트 설정 및 처리 : 익명 클래스
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(item);
                }
            });
        }
        return itemView;
    }
}
