package com.example.user.a3_cameraexam.helper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.util.Calendar;

public class PhotoHelper {
    // 싱글톤 객체 생성 시작
    private static PhotoHelper instance = null;

    public static PhotoHelper getInstance() {
        if (instance == null) instance = new PhotoHelper();

        return instance;
    }

    private PhotoHelper() {}
    // 싱글톤 객체 생성 끝

    /**
     * DCIM 디렉토리 하위에 새로 저장될 사진 파일의 이름을 생성한다.
     * @return 경로 문자열  */
    public String getNewPhotoPath() {
        Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH) + 1;
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mi = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        String fileName = String.format("p%04d-%02d-%02d %02d-%02d-%02d.jpg", yy, mm, dd, hh, mi, ss);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        if(!dir.exists()) dir.mkdirs(); // 폴더 없으면 폴더 생성
        String photoPath = dir.getAbsolutePath() + "/" + fileName;
        Log.d("[TEST]", "photoPath = " + photoPath);
        return photoPath;
    }

    // 큰 이미지를 스마트폰 크기로 줄이기
    public Bitmap getThumb(Activity activity, String path) {
        // 실제 이미지를 저장할 객체
        Bitmap bmp = null;
        /* 1) 화면 해상도 얻기 */
        // 해상도 관리 객체
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // 가로, 높이 크기 얻기
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        // 긴 축을 골라내기
        int maxScale = deviceWidth;
        if(deviceWidth < deviceHeight) maxScale = deviceHeight;

        /* 이미지 크기 얻기 */
        // 비트맵 이미지 로더의 옵션을 설정하기 위한 객체
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 비트맵을 바로 로드하지 말고 정보만 읽어오라고 설정
        options.inJustDecodeBounds = true;
        // 비트맵 파일 읽어오기 - 옵션에 의해서 정보만 읽어들이게 된다.
        BitmapFactory.decodeFile(path, options);
        // 폭과 넓이 중에서 긴 축을 기준으로 삼기 위하여 별도로 값을 저장
        int fscale = options.outHeight;
        if (options.outWidth > options.outHeight) fscale = options.outWidth;
        /* 3) 이미지 리사이징 */
        // 이미지의 길이가 스마트폰보다 크면
        if (maxScale < fscale) {
            // 이미지의 사이즈를 maxScale로 나누어서 샘플링 사이즈 계산
            // ex) 이미지의 긴축이 2400px일 경우, maxScale이 800이면, 3으로 지정된다.
            int sampleSize = fscale / maxScale;
            // 새 비트맵 옵션 생성
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            // 샘플사이즈 설정 --> 3으로 지정하면 1/3 크기가 된다.
            options2.inSampleSize = sampleSize;
            // 실제로 비트맵을 읽어온다.
            bmp = BitmapFactory.decodeFile(path, options2);
        } else {
            // 사이즈가 적당하면 그냥 읽는다.
            bmp = BitmapFactory.decodeFile(path);
        }
        return bmp;
    }
}
