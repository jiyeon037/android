package com.example.user.a5locationexam;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    TextView textView1, textView2, textView3;
    LocationManager lm; // 위치 정보 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        permissionCheck();
    }
    private void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {     // 허락되어있지 않으면
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }
        }
    }

    // 위치 수신을 시작하기 위한 메소드 재정의
    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        // 현재 사용 가능한 하드웨어 이름 얻기
        // LocationManager.GPS_PROVIDER / LocationManager.NETWORK_PROVIDER
        // true : 켜져서 사용가능한 장치만 알려줌 (on 되어있는 장치)
        String provider = lm.getBestProvider(new Criteria(), true);
        Toast.makeText(this,"BestProvider : " + provider, Toast.LENGTH_SHORT).show();
        if(provider == null) {
            Toast.makeText(this, "위치 정보를 사용가능한 상태가 아닙니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        // 해당 장치가 마지막으로 수신한 위치 얻기
        // 이 함수에서는 퍼미션 체크하는 기능을 쓰지 않겠다고 막아버림
        Location location = lm.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);    // 이벤트 함수 강제 호출
        }
        // 위치 정보 취득 시작
        // --> 하드웨어 이름, 갱신시간주기(ms), 갱신거리주기(m), 이벤트핸들러
        lm.requestLocationUpdates(provider, 400, 1, this);
    }
    // 위치 수신을 종료하기 위한 메소드 재정의
    @Override
    protected void onPause() {
        super.onPause();
        // 위치정보 수신 종료
        lm.removeUpdates(this);
    }
    // 위도와 경도 기반으로 주소를 리턴하는 메소드
    public String getAddress(double lat, double lng) {
        String str_address = null;
        // 위치정보를 활용하기 위한 구글 OpenAPI 객체
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        // 주소 목록을 담기 위한 List
        List<Address> list = null;
        try {
            // 전달 파라미터 --> 위도, 경도, 결과수
            list = geocoder.getFromLocation(lat, lng, 1);
            if(list.size() > 0) {
                Address address = list.get(0);   // 첫번째 데이터 꺼내기
                str_address = address.getCountryName() + " "    // 국가
                        + address.getAdminArea() + " "          // 시
                        + address.getLocality() + " "           // 구
                        + address.getThoroughfare() + " "       // 동
                        + address.getFeatureName();             // 번지
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str_address;
    }
    // 지정된 시간, 거리마다 한번씩 호출된다
    @Override
    public void onLocationChanged(Location location) {  // 이것만 사용
        // 현재 위치 얻기
        double lat = location.getLatitude();    // 위도
        double lng = location.getLongitude();   // 경도
        textView1.setText(String.valueOf(lat));
        textView2.setText(String.valueOf(lng));
        textView3.setText(getAddress(lat,lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
