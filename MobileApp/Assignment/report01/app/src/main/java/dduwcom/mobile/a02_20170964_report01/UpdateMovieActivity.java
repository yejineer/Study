package dduwcom.mobile.a02_20170964_report01;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateMovieActivity extends Activity {

    EditText etUpdateTitle;
    EditText etUpdateActor;
    EditText etUpdateDirector;
    EditText etUpdateRated;
    EditText etUpdateTime;
    MovieDBHelper helper;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);

        Intent intent = getIntent(); //intent에서 id값 가져와 그 id값으로 DB읽어오기
        id = intent.getLongExtra("id", 0);

        etUpdateTitle = findViewById(R.id.et_updateTitle);
        etUpdateActor = findViewById(R.id.et_updateActor);
        etUpdateDirector = findViewById(R.id.et_updateDirector);
        etUpdateRated = findViewById(R.id.et_updateRated);
        etUpdateTime = findViewById(R.id.et_updateTime);

        helper = new MovieDBHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + MovieDBHelper.TABLE_NAME +
                                    " where " + MovieDBHelper.COL_ID + "=?",new String[] { String.valueOf(id)});
        while(cursor.moveToNext()) {
            etUpdateTitle.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE)));
            etUpdateActor.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR)));
            etUpdateDirector.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR)));
            etUpdateRated.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RATED)));
            etUpdateTime.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TIME)));
        }
        cursor.close();
        helper.close();

        ImageView imageView = (ImageView)findViewById(R.id.update_img);
        //리스트뷰의 아이템의 이미지를 변경
        String title = etUpdateTitle.getText().toString();
        if ("드래곤 길들이기3".equals(title))
            imageView.setImageResource(R.mipmap.dragon3);
        else if ("알라딘".equals(title))
            imageView.setImageResource(R.mipmap.aladdin);
        else if ("오션스 8".equals(title))
            imageView.setImageResource(R.mipmap.oceans8);
        else if ("캡틴 마블".equals(title))
            imageView.setImageResource(R.mipmap.captainmarvel);
        else if ("콜레트".equals(title))
            imageView.setImageResource(R.mipmap.colette);
        else
            imageView.setImageResource(R.mipmap.ic_launcher);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_updateUpdate:
//			DB 데이터 업데이트 작업 수행
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues row = new ContentValues();
                row.put(MovieDBHelper.COL_TITLE, etUpdateTitle.getText().toString());
                row.put(MovieDBHelper.COL_ACTOR, etUpdateActor.getText().toString());
                row.put(MovieDBHelper.COL_DIRECTOR, etUpdateDirector.getText().toString());
                row.put(MovieDBHelper.COL_RATED, etUpdateRated.getText().toString());
                row.put(MovieDBHelper.COL_TIME, etUpdateTime.getText().toString());
                String whereClause = "_id=?";
                String[] whereArgs = new String[] {String.valueOf(id)};

                long result = db.update(MovieDBHelper.TABLE_NAME, row,whereClause, whereArgs);
                helper.close();

                String msg = result > 0 ? "영화 수정 성공!" : "영화 수정 실패!";
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                finish();
                break;

            case R.id.btn_updateCancel:
//                DB 데이터 업데이트 작업 취소
                finish();
                break;
        }
    }
}
