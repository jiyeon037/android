package com.example.user.a3_cameraexam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.a3_cameraexam.helper.PhotoHelper;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    Button button;
    String photoPath = null;    // 촬영 결과물이 저장될 경로
    Bitmap bmp = null;           // 메모리로 로드한 이미지가 저장될 객체
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        permissionCheck();
    }

    private void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }
    }

    @Override
    public void onClick(View v) {
        // 저장될 사진 파일 경로
        photoPath = PhotoHelper.getInstance().getNewPhotoPath();
        File file = new File(photoPath);
        Uri uri = null;
        // 카메라 App 호출을 위한 암묵적 Intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {    // N : 누가버전
            uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", file);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }

        // 저장될 경로를 파라미터로 설정 --> URI
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(AUDIO_SERVICE, false);
        // 카메라 호출 --> 저장 결과를 받기 위한 설정 필요함
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100) {
//            if(data != null) {
                // 촬영 결과물을 갤러리에 등록한다.
                Intent photoIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + photoPath));
                sendBroadcast(photoIntent);
                // 기존에 표시되던 이미지 제거
                imageView.setImageBitmap(null);

                 // 그림이 저장된 객체가 존재한다면, 메모리 해제
                 // bmp는 gc의 수집 대상이 아니므로,
                 // 반드시 메모리 해제를 위해서 recycle()을 호출해야 한다.
                 if (bmp != null) {
                     bmp.recycle();
                     bmp = null;
                 }
                 // 썸네일 이미지 얻기
                bmp = PhotoHelper.getInstance().getThumb(this, photoPath);
                 imageView.setImageBitmap(bmp);
 //           }
        }
    }

    /** bmp 객체는 App이 종료되어도 메모리에 여전히 남아있기 때문에,
     *   App 종료시에 강제로 메모리에서 해제시켜야 한다.  */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bmp != null) {
            bmp.recycle();
            bmp = null;
        }
    }
}
