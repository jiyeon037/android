package com.example.user.a1_jsonimagelistitem.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.a1_jsonimagelistitem.R;
import com.example.user.a1_jsonimagelistitem.model.Column;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class ColumnAdapter extends ArrayAdapter<Column> {
    Activity activity;
    int resource;
    ImageLoader imageLoader;        // 이미지 다운로더
    DisplayImageOptions options;     // 이미지 다운로더 옵션

    public ColumnAdapter(@NonNull Context context, int resource, @NonNull List<Column> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
        imageLoaderInit();
    }
    /** 이미지 로더 초기화 */
    private void imageLoaderInit() {
        // 이미지 다운로더 초기화
        imageLoader = ImageLoader.getInstance();
        if(!imageLoader.isInited()) {
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(activity);
            imageLoader.init(configuration);
        }
        // 이미지 다운로더의 옵션 설정
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();
        // 다운로드 동안 표시할 임시 이미지
        builder.showImageOnLoading(R.drawable.ic_stub);
        // 이미지가 지정되지 않은 경우 사용될 이미지
        builder.showImageForEmptyUri(R.drawable.ic_empty);
        // 다운로드 실패시에 사용할 이미지
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
        // 1줄 데이터 꺼내오기
        Column item = getItem(position);
        // 1줄 화면 + 1줄 데이터
        if (item != null) {
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            TextView textView1 = (TextView) itemView.findViewById(R.id.textView1);
            TextView textView2 = (TextView) itemView.findViewById(R.id.textView2);
            // 이미지 다운로드 처리
            // --> 이미지 URL, 이미지 뷰 객체, 옵션설정값
            imageLoader.displayImage(item.getImg(), imageView, options);
            textView1.setText(item.getSubject());
            textView2.setText(item.getContent());
        }
        return itemView;
    }
}

















