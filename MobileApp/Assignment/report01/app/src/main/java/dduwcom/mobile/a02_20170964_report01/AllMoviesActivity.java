package dduwcom.mobile.a02_20170964_report01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AllMoviesActivity extends Activity {
    final static int UPDATE_ACTIVITY_CODE = 100;

    ListView lvMovies = null;
    MovieDBHelper helper;
    Cursor cursor;
    MyCursorAdapter myCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movie);

        lvMovies = (ListView)findViewById(R.id.lvMovies);
        helper = new MovieDBHelper(this);

        //MyCursorAdapter객체 생성. cursor는 null. DB의 최신 정보를 반영하려면 onResume에서 해야하기 때문에 생성할 때 cursor는 null인게 좋음.
        myCursorAdapter = new MyCursorAdapter(this, R.layout.listview_layout, null);
        lvMovies.setAdapter(myCursorAdapter);

//		리스트 뷰 클릭 처리 (Update)
        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllMoviesActivity.this, UpdateMovieActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, UPDATE_ACTIVITY_CODE);
            }
        });

//		리스트 뷰 롱클릭 처리
        lvMovies.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final long targetId = id;   // id 값을 다이얼로그 객체 내부에서 사용하기 위하여 상수로 선언
                TextView tvTitle = view.findViewById(R.id.tv_title);   // 리스트 뷰의 클릭한 위치에 있는 뷰 확인

                new AlertDialog.Builder(AllMoviesActivity.this)
                        .setTitle("영화 삭제")
                        .setMessage("'" + tvTitle.getText().toString() + "' 영화를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {

                            //                     삭제 수행
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = helper.getWritableDatabase();

                                String whereClause = MovieDBHelper.COL_ID + "=?";
                                String[] whereArgs = new String[]{String.valueOf(targetId)};

                                db.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);
                                helper.close();
                                readAllMovies();      // 삭제 상태를 반영하기 위하여 전체 목록을 다시 읽음
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
                return true;
            }
        });

    }

    private void readAllMovies() {
        //        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + MovieDBHelper.TABLE_NAME, null);

        myCursorAdapter.changeCursor(cursor);
        helper.close();
        //adapter에 넣은 cursor는 닫으면 안 됨. Activity가 닫힐 때(onDestroy()에서) 없애면 됨.
    }
    @Override
    protected void onResume() {
        super.onResume();
        readAllMovies();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cursor 사용 종료
        if (cursor != null) cursor.close();
    }


}
