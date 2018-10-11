package com.example.user.jsonimagelisttest.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.jsonimagelisttest.R;
import com.example.user.jsonimagelisttest.model.Job;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class JobAdapter extends ArrayAdapter<Job> {
    Activity activity;
    int resource;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public JobAdapter(@NonNull Context context, int resource, @NonNull List<Job> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
        imageLoaderInit();
    }

    private void imageLoaderInit() {
        imageLoader = ImageLoader.getInstance();
        if(!imageLoader.isInited()) {
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(activity);
            imageLoader.init(configuration);
        }
        // 이미지 다운로더의 옵션 설정
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        builder.showImageOnLoading(R.drawable.ic_stub);
        builder.showImageForEmptyUri(R.drawable.ic_empty);
        builder.showImageOnFail(R.drawable.ic_error);
        options = builder.build();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 1줄 화면 만들기
        View itemView = convertView;
        if(itemView == null) {
            itemView = activity.getLayoutInflater().inflate(resource, null);
        }
        Job item = getItem(position);

        if(item != null){
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            TextView textView1 = (TextView) itemView.findViewById(R.id.textView1);
            TextView textView2 = (TextView) itemView.findViewById(R.id.textView2);

            imageLoader.displayImage(item.getImg(), imageView, options);
            textView1.setText(item.getSubject());
            textView2.setText(item.getContent());
        }
        return itemView;    // 한 줄 꾸민거 돌려주기
    }
}

