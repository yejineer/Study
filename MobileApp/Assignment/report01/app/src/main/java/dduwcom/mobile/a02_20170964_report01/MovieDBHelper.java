package dduwcom.mobile.a02_20170964_report01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDBHelper extends SQLiteOpenHelper {
    final static String TAG = "MovieDBHelper";
    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";
    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_ACTOR = "actor";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_RATED = "rated";
    public final static String COL_TIME = "time";


    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement," +
                "" + COL_TITLE + " TEXT, " + COL_ACTOR + " TEXT," + COL_DIRECTOR + " TEXT, " + COL_RATED + " TEXT, " + COL_TIME + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, '드래곤 길들이기3', '제이 바루첼, 아메리카 페레라, 케이트 블란쳇', '딘 데블로이스', 'ALL', '104분');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '알라딘', '나오미 스캇, 윌 스미스, 메나 마수드', '가이 리치', 'ALL', '128분');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '오션스 8', '산드라 블록, 케이트 블란쳇, 앤 해서웨이', '게리 로스', '12+', '110분');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '캡틴 마블', '브리 라슨, 사무엘 L.잭슨, 주드 로', '애너 보든, 라이언 플렉', '12+', '124분');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '콜레트', '키이라 나이틀리, 도미닉 웨스트, 엘리너 톰린슨', '워시 웨스트모어랜드', '15+', '112분');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

