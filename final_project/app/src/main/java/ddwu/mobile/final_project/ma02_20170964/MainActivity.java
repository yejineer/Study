package ddwu.mobile.final_project.ma02_20170964;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

    EditText etTarget;
    ListView lvList;

    TourStreetAdapter adapter;
    ArrayList<TourStreetDto> resultList;
    TourStreetXmlParser parser;

    StreetDBHelper helper;
    Cursor cursor;

    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTarget = findViewById(R.id.etTarget);
        lvList = findViewById(R.id.lvList);

        resultList = new ArrayList();

        helper = new StreetDBHelper(this);
        parser = new TourStreetXmlParser();
        adapter = new TourStreetAdapter(this, R.layout.listview_tourstreet, resultList);

        InputStream inputStream = getResources().openRawResource(R.raw.tour_street);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        resultList = parser.parse(reader);

        adapter.setList(resultList);    // Adapter 에 파싱 결과를 담고 있는 ArrayList 를 설정
        adapter.notifyDataSetChanged();
        lvList.setAdapter(adapter);
        /* DB 저장 코드 */
        insertDB(resultList);
        Log.i(TAG, "db저장");
        //		리스트 뷰 클릭 처리
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StreetActivity.class);
                intent.putExtra("dto", resultList.get(position));
                startActivity(intent);
            }
        });

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButton = (RadioButton)findViewById(R.id.radio_keyword);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_keyword:
                        radioButton = (RadioButton)findViewById(R.id.radio_keyword);
                        break;
                    case R.id.radio_area:
                        radioButton = (RadioButton)findViewById(R.id.radio_area);
                        break;
                }
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                String input = etTarget.getText().toString().trim();
                Toast.makeText(this, "clicked!" + input, Toast.LENGTH_SHORT).show();
                searchStreets(input, radioButton);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + StreetDBHelper.TABLE_NAME, null);

//        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        helper.close();
        //adapter에 넣은 cursor는 닫으면 안 됨. Activity가 닫힐 때(onDestroy()에서) 없애면 됨.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cursor 사용 종료
        if (cursor != null) cursor.close();
    }

    private void searchStreets(String keyword, RadioButton radioButton) {
//        DB에서 데이터를 읽어와 Adapter에 설정
        SQLiteDatabase db = helper.getReadableDatabase();

        if (radioButton.getId() == R.id.radio_keyword) {
            cursor = db.rawQuery("select * from " + StreetDBHelper.TABLE_NAME +
                    " where " + StreetDBHelper.COL_NAME + " like '%" + keyword + "%'", null);
        } else if (radioButton.getId() == R.id.radio_area) {
            cursor = db.rawQuery("select * from " + StreetDBHelper.TABLE_NAME +
                    " where " + StreetDBHelper.COL_ADDR + " like '%" + keyword + "%'", null);
        }

        Log.d(TAG, "select * from " + StreetDBHelper.TABLE_NAME + " where " + StreetDBHelper.COL_NAME +
                " LIKE '%" + keyword + "%';");
        resultList.clear();
        while(cursor.moveToNext()) {
            TourStreetDto dto = new TourStreetDto();
            dto.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            dto.setName(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_NAME)));
            dto.setStreetInfo(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_INFO)));
            dto.setAddress(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_ADDR)));
            dto.setLength(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_LENGTH)));
            dto.setStoreNum(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_STORENUM)));
            dto.setInstitution(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_INSTT)));
            dto.setLatitude(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_LAT)));
            dto.setHardness(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_HAR)));
            resultList.add(dto);
        }
        adapter.notifyDataSetChanged(); // DB의 내용으로 갱신한 contactList의 내용을 ListView에 반영하기 위해 호출
        helper.close();
    }

    public void insertDB(ArrayList<TourStreetDto> list) {
        //			DB 데이터 삽입 작업 수행

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(StreetDBHelper.TABLE_NAME, null, null); // 안하면 insert문 계속 쌓임
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

            db.insert(StreetDBHelper.TABLE_NAME, null, row);

        }
        helper.close();

    }
}