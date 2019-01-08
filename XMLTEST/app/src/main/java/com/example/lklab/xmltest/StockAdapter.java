package com.example.lklab.xmltest;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class StockAdapter extends ArrayAdapter<Stock> {
    Activity activity;
    private int resource;

    public StockAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Stock> objects) {
        super(context, resource, objects);
        activity = (Activity) context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = activity.getLayoutInflater().inflate(resource, null);
        }
        Stock item = getItem(position);

        if (item != null) {
            TextView textStockNum = (TextView) itemView.findViewById(R.id.textView1);
            TextView textPartCodeX = (TextView) itemView.findViewById(R.id.textView2);
            TextView textBundleItems = (TextView) itemView.findViewById(R.id.textView3);
            TextView textBundleNum = (TextView) itemView.findViewById(R.id.textView4);
            TextView textBundleReminder = (TextView) itemView.findViewById(R.id.textView5);
            TextView textStockQty = (TextView) itemView.findViewById(R.id.textView6);

            textStockNum.setText(item.getStockNum());
            textPartCodeX.setText(item.getStockPartCodeX());
            textBundleItems.setText(item.getBundleItems());
            textBundleNum.setText(item.getBundleNum());
            textBundleReminder.setText(item.getBundleReminder());
            textStockQty.setText(item.getStockQty());
        }
        return itemView;
    }
}
