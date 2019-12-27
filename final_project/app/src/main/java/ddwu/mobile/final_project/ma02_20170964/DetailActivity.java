package ddwu.mobile.final_project.ma02_20170964;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvPhone;
    TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = findViewById(R.id.tvStoreName);
        tvPhone = findViewById(R.id.tvStorePhone);
        tvAddress = findViewById(R.id.tvStoreAddr);

        // TODO: MainActivity 로부터 전달받은 장소 세부정보를 intent에서 획득 후 화면에 표시
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvPhone.setText(intent.getStringExtra("phone"));
        tvAddress.setText(intent.getStringExtra("address"));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClose:
                finish();
                break;
        }
    }
}
