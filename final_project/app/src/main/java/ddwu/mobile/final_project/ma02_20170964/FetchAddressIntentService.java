package ddwu.mobile.final_project.ma02_20170964;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FetchAddressIntentService extends IntentService { // Service의 일종인 IntentService클래스 상속. 위도/경도로부터 주소 가져옴. Manifest에 등록해야.

    final static String TAG = "FetchAddress";

    private Geocoder geocoder;
    private ResultReceiver receiver;


    public FetchAddressIntentService() {
        super("FetchLocationIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {    // 여기에 백그라운드 작업을 하면 됨.
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault()); // Activity였으면 this로 context넣어주면 되지만 Activity가 아닌 Service이므로.
                                                                                // 한국 폰이면 한국 주소를 기반으로
//        MainActivity의 startAddressService()가 전달한 Intent 로부터 위도/경도와 Receiver 객체 설정
        if (intent == null) return;
        double latitude = intent.getDoubleExtra(Constants.LAT_DATA_EXTRA, 0);
        double longitude = intent.getDoubleExtra(Constants.LNG_DATA_EXTRA, 0);
        receiver = intent.getParcelableExtra(Constants.RECEIVER);

        List<Address> addresses = null;

//        위도/경도에 해당하는 주소 정보를 Geocoder 에게 요청
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);   // 네트워크 사용해서 가져옴.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

//        결과로부터 주소 추출
        if (addresses == null || addresses.size()  == 0) {
            Log.e(TAG, getString(R.string.no_address_found));
            deliverResultToReceiver(Constants.FAILURE_RESULT, null);    // 결과가 제대로 오지 않았다는 메소드
        } else {    // 정상적으로 잘 반환했을 때. 알아온 주소로 결과를 끄집어 냄
            Address address = addresses.get(0); // 하나의 주소를 끄집어 냄
            ArrayList<String> addressFragments = new ArrayList<String>();

            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(Constants.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments));
        }
    }


//    ResultReceiver 에게 결과를 Bundle 형태로 전달
    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();   // intent와 유사하나 정보만 담기 위해 만들어진 객체
        bundle.putString(Constants.RESULT_DATA_KEY, message);   // bundle안에 주소를 넣음
        receiver.send(resultCode, bundle);  // 이 줄을 수행하자마자 MainActivity의 onReceiveResult로 전달 됨
    }

}
