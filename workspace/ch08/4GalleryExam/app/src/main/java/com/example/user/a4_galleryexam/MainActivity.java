package com.example.user.a4_galleryexam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.a4_galleryexam.helper.FileUtils;
import com.example.user.a4_galleryexam.helper.PhotoHelper;

// 기능적으로 중요한 예제 (이번 챕터에서 제일 중요)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView;
    Button button;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        // 마시멜로 이상 버전에서는 개인 보안에 관련된 내용은 무조건 퍼미션체크 해줘야 함
        permissionCheck();
    }
    private void permissionCheck() {    // 코드는 똑같고 퍼미션만 바뀜
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // 갤러리를 호출하기 위한 암묵적 인텐트
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // 이미지 파일만 필터링 => MIME 형태 (kitkat 이상 버전용)
        intent.setType("*/*");
        // 구글 클라우드에 싱크된 이미지 파일 제외
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Uri photoUri = data.getData();
                Log.d("[TEST]", " photoUri = " + photoUri);
                // 이 형식의 값을 그대로 ImageView에 출력할 경우 이미지 파일이 너무 커서 제대로 값을 보여줄 수 없으므로, 썸네일을 생성해야 한다.
                // 하지만, 썸네일을 생성하기 위해서는 사진파일의 실제 경로명이 필요하다.
                // 선택한 사진의 경로 얻기
                String filePath = FileUtils.getPath(this, photoUri);

                imageView.setImageBitmap(null);
                if(bmp != null) {
                    bmp.recycle();
                    bmp = null;
                }
                bmp = PhotoHelper.getInstance().getThumb(this, filePath);
                imageView.setImageBitmap(bmp);
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if(bmp != null) {
            bmp.recycle();
            bmp = null;
        }
    }
}
