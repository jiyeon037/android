package com.example.user.a11_viedoviewexam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    // 비디오뷰 객체
    VideoView videoView;
    // 재생버튼, 뒤로, 다음, 슬라이더바 등을 갖는 컨트롤러
    // android.widget.MediaController;
    MediaController mc;
    // 퍼미션 체크 확인용
    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;
    boolean permissionCK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** 타이틀바 제거 */
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);
        // 비디오뷰의 할당과 컨트롤러 생성
        videoView = (VideoView) findViewById(R.id.videoView);
        mc = new MediaController(this);
        // 비디오뷰에 컨트롤러를 연결한다.
        videoView.setMediaController(mc);
        // 퍼미션 체크를 먼저 해야한다.
       permissionCheck();
        // 퍼미션이 등록되었는지 확인
        if(permissionCK) startVideo();
    }

    // https://developer.android.com/training/permissions/requesting
    private void permissionCheck() {
        // Here, thisActivity is the current activity
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            permissionCK = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this,"동영상을 실행할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /** 핸드폰상의 동영상 재생하기 */
    private void startVideo() {
        // SD카드의 경로를 문자열로 얻는다. (내부 저장소)
        File sdcard = Environment.getExternalStorageDirectory();
        // SD카드의 절대경로에 동영상 파일 경로를 덧붙인다.
        String video_path = sdcard.getAbsolutePath() + "/BigBuck.mp4";
        // 비디오뷰에 재생할 파일의 경로를 지정한다.
        videoView.setVideoPath(video_path);
        // 재생 시작
        videoView.start();
    }
}
