package ddwu.mobile.map.exam.mygooglemaptest_completed;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MyGooglemapTest";        // 로그 TAG
    private final static int ZOOM_LEVEL = 17;                   // 지도 확대 배율
    private final static int PERMISSION_REQ_CODE = 100;         // permission 요청 코드
    private final static int LINE_COLOR = Color.RED;            // 선그리기 지정 색상
    private final static int LINE_WIDTH = 5;                    // 선그리기 두께


    private GoogleMap mGoogleMap;           // 구글맵 객체 저장 멥버 변수
    private LocationManager locManager;     // 위치 관리자
    private Location lastLocation;          // 앱 실행 중 최종으로 수신한 위치 저장 멤버 변수

    private Marker centerMarker;            // 현재 위치를 표현하는 마커 멤버 변수
    private MarkerOptions markerOptions;    // 마커 옵션
    private PolylineOptions lineOptions;    // 선 그리기 옵션


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위치관리자 준비
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 구글맵 준비
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mapReadyCallback);

        // 마커를 생성하기 위한 옵션 지정
        markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.androboy));

        // 선을 그리기 위한 옵션 지정
        lineOptions = new PolylineOptions();
        lineOptions.color(LINE_COLOR);
        lineOptions.width(LINE_WIDTH);

        if (checkPermission()) {
            lastLocation = locManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                if (checkPermission()) {
                    // 위치 정보 수신 시작 - 3초 간격, 5m 이상 이동 시 수신
                    locManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 3000, 5, locationListener);
                }
                break;
            case R.id.btnStop:
                // 위치 정보 수신 종료
                locManager.removeUpdates(locationListener);
                centerMarker.setSnippet("정지");
                centerMarker.showInfoWindow();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 위치 정보 수신 종료 - 위치 정보 수신 종료를 누르지 않았을 경우를 대비
        locManager.removeUpdates(locationListener);
    }


    /*Google Map 준비 시 호출할 CallBack 인터페이스*/
    OnMapReadyCallback mapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            // 로딩한 구글맵을 보관
            mGoogleMap = googleMap;

            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            // 기록한 최종 위치가 있을 경우와 없을 경우를 구분하여 구현
            LatLng lastLatLng;
            if (lastLocation != null) {
                lastLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            } else {
                lastLatLng = new LatLng(Double.valueOf(getString(R.string.init_lat)),
                        Double.valueOf(getString(R.string.init_lng)));     // 최종 위치가 없을 경우 지정한 곳으로 위치 지정
            }

            Log.i(TAG, "Start location: " + lastLatLng.latitude + ", " + lastLatLng.longitude);

            // 이동 시
             mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, ZOOM_LEVEL));

            // 애니메이션 효과로 이동 시
//            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, ZOOM_LEVEL));

            // 지정한 위치로 마커 위치 설정
            markerOptions.position(lastLatLng);
            centerMarker = mGoogleMap.addMarker(markerOptions);

            /*이하의 내용은 실습 1, 2 내용에 포함되어 있지 않은 지도 관련 이벤트 처리에 대한 예이므로 참고*/

//            마커 윈도우 클릭 시 이벤트 처리
            mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    LatLng markerPosition = marker.getPosition();
                    String loc = String.format("윈도우 클릭 - 위도:%f, 경도:%f",  markerPosition.latitude, markerPosition.longitude);
                    Toast.makeText(MainActivity.this, loc, Toast.LENGTH_SHORT).show();
                }
            });

//            map 클릭 시 이벤트 처리
            mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    String loc = String.format("클릭 - 위도:%f, 경도:%f", latLng.latitude, latLng.longitude);
                    Toast.makeText(MainActivity.this, loc, Toast.LENGTH_SHORT).show();
                }
            });

//            map 롱클릭 시 이벤트 처리
//            롱클릭 시 NewActivity 를 호출, 호출 시 intent에 현재 위치의 위도 경도를 저장하여 전달
            mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    Intent intent = new Intent(MainActivity.this, NewActivity.class);
                    intent.putExtra("latitude", latLng.latitude);
                    intent.putExtra("longitude", latLng.longitude);
                    startActivity(intent);
                }
            });
        }
    };


    /*위치 정보 수신 LocationListener*/
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.i(TAG, "Current Location : " + location.getLatitude() + ", " + location.getLongitude());

//            현재 수신한 위치 정보 Location을 LatLng 형태로 변환
            LatLng currentLoc = new LatLng(location.getLatitude(), location.getLongitude());

//            새로운 위치로 지도 이동
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, ZOOM_LEVEL));


            String snippet = String.format("위도:%f, 경도:%f", location.getLatitude(), location.getLongitude());

            // 새로운 위치로 마커의 위치 지정 및 정보 표시- 윈도우 표시를 안 할 경우 마커를 터치할 때 표시됨
            centerMarker.setPosition(currentLoc);
            centerMarker.setTitle("현재 위치");
            centerMarker.setSnippet(snippet);
            centerMarker.showInfoWindow();

//            현재 위치를 라인 정보로 추가
            lineOptions.add(currentLoc);
            mGoogleMap.addPolyline(lineOptions);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) { }
        @Override
        public void onProviderEnabled(String s) { }
        @Override
        public void onProviderDisabled(String s) { }
    };


    /* 필요 permission 요청 */
    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_REQ_CODE);
            return false;
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case PERMISSION_REQ_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission is granted!\nTry again!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission is denied!\n", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

