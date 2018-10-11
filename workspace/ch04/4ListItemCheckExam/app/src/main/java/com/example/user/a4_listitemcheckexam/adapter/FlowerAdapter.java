package com.example.user.a4_listitemcheckexam.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.a4_listitemcheckexam.R;
import com.example.user.a4_listitemcheckexam.model.Flower;

import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower>{
    Activity activity;
    int resource;

    public FlowerAdapter(@NonNull Context context, int resource, @NonNull List<Flower> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
    }
    // ListView에 의해서 호출되는 메소드. getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 1. 한줄 화면 만들기
        View itemView = convertView;
        if(itemView == null){
            itemView = activity.getLayoutInflater().inflate(resource, null);
        }
        // 2. 한줄 데이터 얻기 (List 클래스에 저장된 데이터)
        final Flower item = getItem(position);
        // 3. 한줄 화면 + 한줄 데이터 결합
        if(item != null) { // 데이터가 있으면
            CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            TextView textView1 = (TextView) itemView.findViewById(R.id.textView1);
            TextView textView2 = (TextView) itemView.findViewById(R.id.textView2);
            imageView.setImageResource(item.getImage());
            textView1.setText(item.getTitle());
            textView2.setText(item.getDescription());

            /** 체크박스의 이벤트 처리 .. 부속화면이므로 익명클래스로 처리함 */
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // 체크가 되었을 경우 데이터 객체의 setter를 사용하여 체크 상태 변경
                    // Anonymous 클래스 안에서 사용해야 하므로, item 객체는 상수(final)로 생성해야 한다.
                    item.setCheck(isChecked);
                }
            });
            checkBox.setChecked(item.isCheck());
        }
        // 4. 한줄 화면 돌려주기
        return itemView;
    }
}
