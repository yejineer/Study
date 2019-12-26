package ddwu.mobile.final_project.ma02_20170964;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;


public class StreetActivity extends AppCompatActivity implements OnMapReadyCallback {

    final static String TAG = "StreetActivity";

    /*UI*/
    private TextView tvStreetName;
    private TextView tvStreetAddr;
    private TextView tvStreetInfo;
    private GoogleMap mGoogleMap;
    private MarkerOptions markerOptions;
    private Marker centerMarker;
    /*DATA*/
    // TODO: Place 클라이언트 객체 선언
    private PlacesClient placesClient;

    private LatLngResultReceiver latLngResultReceiver;
    Location loc;
    LatLng streetLoc;

    TourStreetDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street);
        tvStreetName = (TextView)findViewById(R.id.tv_streetName);
        tvStreetAddr = (TextView)findViewById(R.id.tv_streetAddr);
        tvStreetInfo = (TextView)findViewById(R.id.tv_streetInfo);

        /* MainActivity가 전달한 intent에서 latitude, hardness가져와 centerMarker찍기(없으면 도로명주소로)*/
        dto = (TourStreetDto)getIntent().getSerializableExtra("dto");
        tvStreetName.setText(dto.getName());
        tvStreetAddr.setText(dto.getAddress());
        tvStreetInfo.setText(dto.getStreetInfo());
        Log.d(TAG, "위도: " + dto.getLatitude() + " 경도: " + dto.getHardness());

        latLngResultReceiver = new LatLngResultReceiver(new Handler()); // IntentService가 생성하는 결과 수신용 ResultReceiver

        mapLoad();

        // TODO: Places 초기화 및 클라이언트 생성
        Places.initialize(getApplicationContext(), getString(R.string.google_api_key));
        placesClient = Places.createClient(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        Log.d(TAG, "Map ready");

        if (dto.getLatitude() != "" && dto.getHardness() != "") {
            streetLoc = new LatLng(Double.valueOf(dto.getLatitude()), Double.valueOf(dto.getHardness()));
            settingMap();
        } else {
            Log.d(TAG, "startLatLngService시작함!");
            startLatLngService(dto.getAddress());
        }

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                // TODO: 마커의 InfoWindow 클릭 시 이벤트 처리
                final String placeId = marker.getTag().toString();    // 마커의 setTag()로 저장한 Place ID 확인
                List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.PHONE_NUMBER, Place.Field.ADDRESS);
                FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields).build();    // 요청 생성

                // 요청 처리 및 요청 성공/실패 리스너 지정
                placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                    @Override
                    public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                        Place place = fetchPlaceResponse.getPlace();
                        Log.i(TAG, "Place found: " + place.getName());  // 장소 명 확인
                        Log.i(TAG, "Phone: " + place.getPhoneNumber()); // 전화번호 확인
                        Log.i(TAG, "Address: " + place.getAddress());   // 주소 확인
                        Intent intent = new Intent(StreetActivity.this, DetailActivity.class);
                        intent.putExtra("name", place.getName());
                        intent.putExtra("phone", place.getPhoneNumber());
                        intent.putExtra("address", place.getAddress());
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.e(TAG, "Place not found: " + e.getMessage());
                        }
                    }
                });
            }
        });
    }

    /*구글맵을 멤버변수로 로딩*/
    private void mapLoad() {
        // SupportMapFragment 는 하위 호환 고려 시 사용, activity_main 의 MapFragment 도 동일한 타입으로 선언
        SupportMapFragment mapFragment =
                (SupportMapFragment)StreetActivity.this.getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(StreetActivity.this);      // 매개변수 this: MainActivity 가 OnMapReadyCallback 을 구현하므로
    }

    private void settingMap() {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(streetLoc, 17));
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 17));

        markerOptions = new MarkerOptions();
        markerOptions.position(streetLoc);
//        mGeoDataClient = Places.getGeoDataClient(MainActivity.this);
        centerMarker = mGoogleMap.addMarker(markerOptions);

        // 마커 정보 지정
        markerOptions= new MarkerOptions();
        markerOptions.position(streetLoc);
        markerOptions.title(dto.getName());
        markerOptions.snippet("총 길이: " + dto.getLength() + " m");

        // 지도에 마커 추가 후 추가한 마커 정보 기록
        centerMarker = mGoogleMap.addMarker(markerOptions);
        centerMarker.showInfoWindow();
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearch:
                mGoogleMap.clear();
                // TODO: 장소 정보 요청
                new NRPlaces.Builder().listener(placesListener)
                        .key(getString(R.string.google_api_key))
                        .latlng(loc.getLatitude(), loc.getLongitude())
                        .radius(500)
                        .type("cafe")
                        .language("ko", "KR")
                        .build()
                        .execute();
                break;
        }
    }

    // TODO: PlaceListener 구현
    PlacesListener placesListener = new PlacesListener() {
        @Override
        public void onPlacesFailure(PlacesException e) {

        }

        @Override
        public void onPlacesStart() {

        }

        @Override
        public void onPlacesSuccess(final List<noman.googleplaces.Place> places) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (noman.googleplaces.Place place : places) {
                        markerOptions.title(place.getName());
                        markerOptions.position(new LatLng(place.getLatitude(), place.getLongitude()));
                        Marker newMarker = mGoogleMap.addMarker(markerOptions);
                        newMarker.setTag(place.getPlaceId());   // Marker의 setTag()를 사용하여 Place ID를 보관
                        Log.d(TAG, "placeID: " + place.getPlaceId());
                    }
                }
            });
        }

        @Override
        public void onPlacesFinished() {

        }
    };

    /* 주소 → 위도/경도 변환 IntentService 실행 */
    private void startLatLngService(String address) {
        Log.d(TAG, "startLatLngService 내부 들어옴!");
        Intent intent = new Intent(this, FetchLatLngIntentService.class);
        intent.putExtra(Constants.RECEIVER, latLngResultReceiver);
        intent.putExtra(Constants.ADDRESS_DATA_EXTRA, address);
        startService(intent);
    }

    /* 주소 → 위도/경도 변환 ResultReceiver */
    class LatLngResultReceiver extends ResultReceiver {
        public LatLngResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            Log.d(TAG, "onReceiveResult 들어옴!");

            ArrayList<LatLng> latLngList = null;

            if (resultCode == Constants.SUCCESS_RESULT) {
                if (resultData == null) return;
                latLngList = (ArrayList<LatLng>) resultData.getSerializable(Constants.RESULT_DATA_KEY);
                if (latLngList == null) {
                    Toast.makeText(StreetActivity.this, "주소에 해당하는 위도/경도가 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    LatLng latlng = latLngList.get(0);
                    streetLoc = new LatLng(latlng.latitude, latlng.longitude);
                    Log.d(TAG, String.valueOf(latlng.latitude) + String.valueOf(latlng.longitude));
                    settingMap();
                }
            } else {
                Toast.makeText(StreetActivity.this, "주소 → 위도/경도 실패!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
