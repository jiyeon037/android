package com.example.user.jsonimagelisttest.response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.example.user.jsonimagelisttest.adapter.JobAdapter;
import com.example.user.jsonimagelisttest.model.Job;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JobResponse extends AsyncHttpResponseHandler {

    Activity activity;
    JobAdapter adapter;
    ProgressDialog dialog;

    public JobResponse(Activity activity, JobAdapter adapter){
        this.activity = activity;
        this.adapter = adapter;
    }
    // 통신 시작 시에 실행된다.

    @Override
    public void onStart() {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("잠시만 기다려주세요...");
        dialog.setCancelable(false);
        dialog.show();
    }
    // 통신 성공 시에 실행된다.

    @Override
    public void onSuccess(String content1) {
        try {
            JSONObject json = new JSONObject(content1);
            JSONObject job = json.getJSONObject("job");
            JSONArray item = job.getJSONArray("item");  // 한 단계씩..
            for(int i=0; i<item.length(); i++){
                JSONObject temp = item.getJSONObject(i);    // 중괄호니까 옵젝. 하나씩 하나씩 첫번째부터 꺼내옴
                int num = temp.getInt("num");
                String img = temp.getString("img");
                String subject = temp.getString("subject");
                String content = temp.getString("content");

                Job job1 = new Job(num, img, subject, content);
                adapter.add(job1);
                // adapter.add(new Job(num, img, subject, content));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    // 통신 실패 시에 실행된다.


    @Override
    public void onFailure(int statusCode, Throwable error, String content) {
        Toast.makeText(activity, "통신 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {
        dialog.dismiss();
        dialog = null;
    }
}




















