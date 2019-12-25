package ddwu.mobile.final_project.ma02_20170964;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
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

        //		리스트 뷰 클릭 처리
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "clicked!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, StreetActivity.class);
//                intent.putExtra("dto", resultList.get(position));
//                startActivity(intent);
            }
        });
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearch:
                
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
}
