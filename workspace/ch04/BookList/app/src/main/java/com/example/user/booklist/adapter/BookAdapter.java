package com.example.user.booklist.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.booklist.R;
import com.example.user.booklist.model.Book;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book>{
    Activity activity;
    int resource;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        activity = (Activity)context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null){
            itemView = activity.getLayoutInflater().inflate(resource,null);
        }
        Book item = getItem(position);

        if(item != null){
            ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);
            TextView textView1 = (TextView)itemView.findViewById(R.id.textView1);
            TextView textView2 = (TextView)itemView.findViewById(R.id.textView2);
            TextView textView3 = (TextView)itemView.findViewById(R.id.textView3);

            imageView.setImageResource(item.getImage());
            textView1.setText(item.getSubject());
            textView2.setText(item.getWriter());
            textView3.setText(item.getPublisher());
        }
        return itemView;
    }
}
