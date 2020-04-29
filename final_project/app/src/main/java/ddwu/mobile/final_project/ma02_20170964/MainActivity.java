package ddwu.mobile.final_project.ma02_20170964;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    final static int PERMISSION_REQ_CODE = 100;
    public static Context context;
    EditText etTarget;
    ListView lvList;
    public SharedPreferences prefs; // 앱 설치 후 최초 실행 한 것인지 확인 위해

    // data
    TourStreetAdapter adapter;
    TourStreetXmlParser parser;

    // DB
    StreetDBHelper helper;
    Cursor cursor;

    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        checkPermission();

        etTarget = findViewById(R.id.etTarget);
        lvList = findViewById(R.id.lvList);
        prefs = getSharedPreferences("Pref", MODE_PRIVATE);

        helper = new StreetDBHelper(this);
        parser = new TourStreetXmlParser();
        adapter = new TourStreetAdapter(this, R.layout.listview_tourstreet, cursor);
        lvList.setAdapter(adapter);

        //		리스트 뷰 클릭 처리
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, StreetActivity.class);
                TourStreetDto dto = new TourStreetDto();
                dto.set_id(cursor.getInt(cursor.getColumnIndex(StreetDBHelper.COL_ID)));
                dto.setName(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_NAME)));
                dto.setStreetInfo(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_INFO)));
                dto.setAddress(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_ADDR)));
                dto.setLength(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_LENGTH)));
                dto.setStoreNum(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_STORENUM)));
                dto.setInstitution(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_INSTT)));
                dto.setLatitude(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_LAT)));
                dto.setHardness(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_HAR)));
                intent.putExtra("dto", dto);
                startActivity(intent);
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton = (RadioButton) findViewById(R.id.radio_keyword);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_keyword:
                        radioButton = (RadioButton) findViewById(R.id.radio_keyword);
                        break;
                    case R.id.radio_area:
                        radioButton = (RadioButton) findViewById(R.id.radio_area);
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        /* 앱 설치 후, 최초 실행일 경우에만 XML파싱해서 ArrayList<>에 저장. 업데이트는 메뉴에서 */
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("데이터 받아오기");
            builder.setMessage("앱 설치 후 최초 한 번은 데이터를 받아와야 합니다.");
            builder.setPositiveButton("받아오기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    prefs.edit().putBoolean("isFirstRun", false).apply();
                    getData_insertDB();
                }
            });
            builder.setCancelable(false);

            Dialog dlg = builder.create(); //대화 상자 생성, 표시 X
            dlg.setCanceledOnTouchOutside(false);
            dlg.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) cursor.close();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                String input = etTarget.getText().toString().trim();
                searchStreets(input, radioButton);
                break;
        }
    }

    public void getData_insertDB() {
        /* 내부 xml파일 파싱해서 resultList로 받아오고, 리스트뷰에 출력 */
        InputStream inputStream = getResources().openRawResource(R.raw.tour_street);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        /* DB 저장 코드 */
        insertAllDB(parser.parse(reader));
    }

    private void searchStreets(String keyword, RadioButton radioButton) {
//        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();

        if (radioButton.getId() == R.id.radio_keyword) {
            cursor = db.rawQuery("select * from " + StreetDBHelper.TABLE_NAME_STREET +
                    " where " + StreetDBHelper.COL_NAME + " like '%" + keyword + "%'", null);
        } else if (radioButton.getId() == R.id.radio_area) {
            cursor = db.rawQuery("select * from " + StreetDBHelper.TABLE_NAME_STREET +
                    " where " + StreetDBHelper.COL_ADDR + " like '%" + keyword + "%'", null);
        }

        Log.d(TAG, "select * from " + StreetDBHelper.TABLE_NAME_STREET + " where " + StreetDBHelper.COL_NAME +
                " LIKE '%" + keyword + "%';");
        adapter.changeCursor(cursor);
        helper.close();
    }

    public void insertAllDB(ArrayList<TourStreetDto> list) {
        //			DB 데이터 삽입 작업 수행

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(StreetDBHelper.TABLE_NAME_STREET, null, null); // 안하면 insert문 계속 쌓임
        for (TourStreetDto dto : list) {
            ContentValues row = new ContentValues();
            row.put(StreetDBHelper.COL_NAME, dto.getName());
            row.put(StreetDBHelper.COL_INFO, dto.getStreetInfo());
            row.put(StreetDBHelper.COL_ADDR, dto.getAddress());
            row.put(StreetDBHelper.COL_LENGTH, dto.getLength());
            row.put(StreetDBHelper.COL_STORENUM, dto.getStoreNum());
            row.put(StreetDBHelper.COL_INSTT, dto.getInstitution());
            row.put(StreetDBHelper.COL_LAT, dto.getLatitude());
            row.put(StreetDBHelper.COL_HAR, dto.getHardness());

            db.insert(StreetDBHelper.TABLE_NAME_STREET, null, row);
        }
        helper.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent_home = new Intent(this, MainActivity.class);
                startActivity(intent_home);
                break;
            case R.id.menu_favorite:
                Intent intent_scrap = new Intent(this, FavoriteActivity.class);
                startActivity(intent_scrap);
                break;
            case R.id.menu_update:
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("데이터 업데이트");
                builder.setMessage("데이터를 업데이트하시겠습니까?");
                builder.setIcon(R.mipmap.ic_update);
                builder.setPositiveButton("받아오기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getData_insertDB();
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.setCancelable(false);
                Dialog dlg = builder.create(); //대화 상자 생성, 표시 X
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();
                break;
            case R.id.menu_quit:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setTitle("앱 종료");
                builder2.setIcon(R.mipmap.question);
                builder2.setMessage("앱을 종료하시겠습니까?");
                builder2.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder2.setNegativeButton("취소", null);
                builder2.show();
                Dialog dlg2 = builder2.create(); //대화 상자 생성, 표시 X
                dlg2.setCanceledOnTouchOutside(false);
                dlg2.show();
                break;
        }
        return true;
    }

    /* 필요 permission 요청 */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                        PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQ_CODE) {
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                    grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                // 퍼미션 미획득 시 액티비티 종료
                Toast.makeText(this, "앱 실행을 위해 권한 허용이 필요함", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}