package ddwu.mobile.final_project.ma02_20170964;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StreetDBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "street_db";
    public final static String TABLE_NAME = "street_table";
    public final static String COL_ID = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_INFO = "info";
    public final static String COL_ADDR = "addr";
    public final static String COL_STORENUM = "store_num";
    public final static String COL_INSTT = "institution";
    public final static String COL_LAT = "latitude";
    public final static String COL_HAR = "hardness";

    private String streetInfo;      // 거리 정보
    private String address;         // 도로명 주소
    private String storeNum;        // 점포 수
    private String institution;     // 관리 기관 명
    private String latitude;        // 위도
    private String hardness;        // 경도

    public StreetDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COL_ID + " integer primary key autoincrement,"
                + COL_NAME + " TEXT, " + COL_INFO + " TEXT, " + COL_ADDR + " TEXT, " + COL_STORENUM + " TEXT, "
                + COL_INSTT + " TEXT, " + COL_LAT + " TEXT, " + COL_HAR + " TEXT);");

//		샘플 데이터

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

}
