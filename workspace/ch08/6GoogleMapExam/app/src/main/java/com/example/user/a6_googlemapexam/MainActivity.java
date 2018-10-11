package com.example.user.a6_googlemapexam;

import android.Manifest;        // 자동으로 안될 때 있음.. 이럴 땐 수동으로 넣어줘야 함
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements LocationListener, OnMapReadyCallback {

    GoogleMap googleMap;
    MapFragment fragment;
    LocationManager lm;     // 위치 정보 객체
    Marker marker;          // 지도에 표시할 마커 객체
    boolean permissionCK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);
        fragment.getMapAsync(this); // 구글맵 초기화
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        permissionCheck();
    }

    private void permissionCheck() {
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            }
        } else {        // 퍼미션 체크가 되어 있으면
            permissionCK = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        readyMap();
    }

    /** 위치 수신을 시작하기 위한 메소드 */
    @SuppressLint("MissingPermission")
    public void readyMap() {
        String provider = lm.getBestProvider(new Criteria(), true);
        if(provider == null) {
            Toast.makeText(this,"위치정보를 사용가능한 상태가 아닙니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = lm.getLastKnownLocation(provider);
        if(location != null) {
            onLocationChanged(location);
            lm.requestLocationUpdates(provider,1000,1,this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        /** 구글 맵에 위치 설정하기 */
        // 현재 위치 객체
        LatLng position = new LatLng(lat, lng);
        if(marker == null) {    // 마커가 없을 경우, 새로 생성하여 지도에 추가
            MarkerOptions options = new MarkerOptions();
            options.position(position);
//            options.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
            marker = googleMap.addMarker(options);
        } else {                // 이미 존재하는 경우, 위치만 갱신
            marker.setPosition(position);
        }
        // 지도 배율 설정 : zoom : 1~21 (값이 커질수록 확대)
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(position, 19);
        // 현재 위치로 맵의 카메라 이동
        googleMap.animateCamera(camera);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {       // 구글맵 초기화 하는 곳
        this.googleMap = googleMap;
        if(permissionCK) readyMap();
    }
}
