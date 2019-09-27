package dduwcom.mobile.a02_20170964_report01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btn_OpenAllMovie:
                intent = new Intent(this, AllMoviesActivity.class);
                break;
            case R.id.btn_AddNewMovie:
                intent = new Intent(this, InsertMovieActivity.class);
                break;
        }

        if (intent != null) startActivity(intent);
    }
}
