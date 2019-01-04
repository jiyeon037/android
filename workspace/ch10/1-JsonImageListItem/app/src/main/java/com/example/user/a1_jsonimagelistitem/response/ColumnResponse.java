package com.example.user.a1_jsonimagelistitem.response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.example.user.a1_jsonimagelistitem.adapter.ColumnAdapter;
import com.example.user.a1_jsonimagelistitem.model.Column;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ColumnResponse extends AsyncHttpResponseHandler {

    Activity activity;
    ColumnAdapter adapter;
    ProgressDialog dialog;

    public ColumnResponse(Activity activity, ColumnAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }
    /** 통신 시작 시에 실행된다. */
    @Override
    public void onStart() {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("잠시만 기다려주세요...");
        dialog.setCancelable(false);
        dialog.show();
    }
    /** 통신 성공 시에 실행된다. */
    @Override
    public void onSuccess(String content1) {
        try {
            JSONObject json = new JSONObject(content1);
            JSONObject column = json.getJSONObject("column");
            JSONArray item = column.getJSONArray("item");
            for (int i=0; i<item.length(); i++) {
                JSONObject temp = item.getJSONObject(i);
                int num = temp.getInt("num");
                String img = temp.getString("img");
                String subject = temp.getString("subject");
                String content = temp.getString("content");

                Column column1 = new Column(num, img, subject, content);
                adapter.add(column1);   // adapter를 통해서 리스트에 저장시켜줌
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /** 통신 실패 시에 실행된다. */
    @Override
    public void onFailure(int statusCode, Throwable error, String content) {
        Toast.makeText(activity, "통신 실패", Toast.LENGTH_SHORT).show();
    }
    /** 성공, 실패 여부에 상관없이 통신이 종료되면 실행된다. */
    @Override
    public void onFinish() {
        dialog.dismiss();
        dialog = null;
    }
}


