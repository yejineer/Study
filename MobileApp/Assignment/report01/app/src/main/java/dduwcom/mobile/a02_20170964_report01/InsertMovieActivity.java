package dduwcom.mobile.a02_20170964_report01;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class InsertMovieActivity extends Activity {
    EditText etAddTitle;
    EditText etAddActor;
    EditText etAddDirector;
    EditText etAddRated;
    EditText etAddTime;
    ImageView imageView;

    MovieDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_movie);

        etAddTitle = findViewById(R.id.et_addTitle);
        etAddActor = findViewById(R.id.et_addActor);
        etAddDirector = findViewById(R.id.et_addDirector);
        etAddRated = findViewById(R.id.et_addRated);
        etAddTime = findViewById(R.id.et_addTime);
        imageView = findViewById(R.id.add_img);

        imageView.setImageResource(R.mipmap.ic_launcher);
        helper = new MovieDBHelper(this);
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_AddMovie:
//			DB 데이터 삽입 작업 수행
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues row = new ContentValues();
                row.put(MovieDBHelper.COL_TITLE, etAddTitle.getText().toString());
                row.put(MovieDBHelper.COL_ACTOR, etAddActor.getText().toString());
                row.put(MovieDBHelper.COL_DIRECTOR, etAddDirector.getText().toString());
                row.put(MovieDBHelper.COL_RATED, etAddRated.getText().toString());
                row.put(MovieDBHelper.COL_TIME, etAddTime.getText().toString());

                long result = db.insert(MovieDBHelper.TABLE_NAME, null, row);
                helper.close();

                String msg = result > 0 ? "영화 추가 성공!" : "영화 추가 실패!";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                finish();
                break;
            case R.id.btn_AddCancel:
//			DB 데이터 삽입 취소 수행
                finish();
                break;
        }
    }
}
