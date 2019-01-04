package com.example.user.a2_kakaoimagesearch.response;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.a2_kakaoimagesearch.adapter.ImageAdapter;
import com.example.user.a2_kakaoimagesearch.model.Image;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageResponse extends AsyncHttpResponseHandler {

    Activity activity;
    ImageAdapter adapter;
    ListView listView;  // 로딩중임을 표시하기 위한 포커스 조절을 위해 사용
    View footerView;    // 로딩중임을 표시하기 위해서 사용

    public ImageResponse(Activity activity, ImageAdapter adapter, ListView listView, View footerView) {
        this.activity = activity;
        this.adapter = adapter;
        this.listView = listView;
        this.footerView = footerView;
    }

    @Override
    public void onStart() {
        // 다이얼로그 대신, 숨겨진 footer를 표시해준다.
        footerView.setVisibility(View.VISIBLE);
        // 맨 마지막요소(footer)에 대한 강제 선택 (화면에 표시하기 위함)
        listView.setSelection(adapter.getCount() - 1);
    }

    @Override
    public void onSuccess(String content) {
        try {
            JSONObject json = new JSONObject(content);
            JSONObject meta = json.getJSONObject("meta");
            // 공통변수 얻기
            Image.is_end = meta.getBoolean("is_end");   // 스태틱이기 때문에 클래스로 바로 세팅 가능
            Image.total_count = meta.getInt("total_count");
            Image.pageable_count = meta.getInt("pageable_count");

            JSONArray documents = json.getJSONArray("documents");
            for (int i=0; i<documents.length(); i++) {
                JSONObject temp = documents.getJSONObject(i);
                Image image = new Image();
                image.setCollection(temp.getString("collection"));
                image.setThumbnail_url(temp.getString("thumbnail_url"));
                image.setImage_url(temp.getString("image_url"));
                image.setWidth(temp.getInt("width"));
                image.setHeight(temp.getInt("height"));
                image.setDisplay_sitename(temp.getString("display_sitename"));
                image.setDoc_url(temp.getString("doc_url"));
                image.setDatetime(temp.getString("datetime"));

                adapter.add(image);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(int statusCode, Throwable error, String content) {
        Toast.makeText(activity, "통신 실패", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {
        footerView.setVisibility(View.GONE);
    }
}
