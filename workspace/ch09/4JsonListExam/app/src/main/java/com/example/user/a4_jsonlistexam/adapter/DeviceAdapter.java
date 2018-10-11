package com.example.user.a4_jsonlistexam.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.a4_jsonlistexam.R;
import com.example.user.a4_jsonlistexam.model.Device;

import java.util.List;

public class DeviceAdapter extends ArrayAdapter<Device> {
    Activity activity;
    int resource;

    public DeviceAdapter(@NonNull Context context, int resource, @NonNull List<Device> objects) {   // activity, 파일id, 리스트
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        // 최소 호출시에는 null이므로, 생성자의 파라미터로 전달받은
        // 리소스 아이디값을 사용하여 레이아웃을 생성한다.
        if(itemView == null) {
            itemView = activity.getLayoutInflater().inflate(resource,null);
        }
        // 전달받은 List에서 데이터를 하나 꺼낸다.
        Device item = getItem(position);
        // 데이터가 존재한다면, 레이아웃 객체에 포함된 컴포넌트들에게
        // 데이터를 세팅한다.
        if (item != null) {
            TextView textView1 = (TextView) itemView.findViewById(R.id.textView1);
            TextView textView2 = (TextView) itemView.findViewById(R.id.textView2);
            textView1.setText(item.getName());
            textView2.setText(item.getType());
        }
        // 다시 리스트 뷰에게 되돌려 준다.
        return itemView;
    }
}