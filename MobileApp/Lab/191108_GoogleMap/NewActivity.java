package ddwu.mobile.map.exam.mygooglemaptest_completed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

public class NewActivity extends AppCompatActivity {

    LatLng currentLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 37.6067869);
        double longitude = intent.getDoubleExtra("longitude", 127.0422166);

        currentLatLng = new LatLng(latitude, longitude);

        TextView tvText = (TextView)findViewById(R.id.tvNewText);
        tvText.setText("위도: " + latitude + " 경도: " + longitude);
    }
}
