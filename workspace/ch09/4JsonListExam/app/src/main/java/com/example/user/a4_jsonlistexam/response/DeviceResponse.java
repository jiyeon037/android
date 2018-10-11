package com.example.user.a4_jsonlistexam.response;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.example.user.a4_jsonlistexam.adapter.DeviceAdapter;
import com.example.user.a4_jsonlistexam.model.Device;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// 통신 처리 결과를 위한 클래스 정의
public class DeviceResponse extends AsyncHttpResponseHandler{
    Activity activity;
    DeviceAdapter adapter;
    ProgressDialog dialog;

    public DeviceResponse(Activity activity, DeviceAdapter adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }
    // 통신 시작시에 실행된다
    @Override
    public void onStart() {
        dialog = new ProgressDialog(activity);
        dialog.setMessage("잠시만 기다려주세요...");
        dialog.setCancelable(false);
        dialog.show();
    }

    // 통신 성공시에 실행된다
    @Override
    public void onSuccess(String content) {
        try {
            JSONObject json = new JSONObject(content);
            JSONArray device = json.getJSONArray("device");
            for(int i=0; i<device.length(); i++){
                JSONObject item = device.getJSONObject(i);
                String name = item.getString("name");
                String type = item.getString("type");
                adapter.add(new Device(name, type));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 통신 실패시에 실행된다
    @Override
    public void onFailure(int statusCode, Throwable error, String content) {
        Toast.makeText(activity,"통신 실패", Toast.LENGTH_SHORT).show();
    }
    // 성공 실패 여부에 상관없이 통신이 종료되면 실행된다
    @Override
    public void onFinish() {
        dialog.dismiss();
        dialog = null;
    }
}

















