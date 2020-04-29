package ddwu.mobile.final_project.ma02_20170964;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TourStreetAdapter extends CursorAdapter {

    public static final String TAG = "MyTourStreetAdapter";

    private LayoutInflater inflater;
    private Cursor cursor;
    private int layout;

    public TourStreetAdapter(Context context, int layout, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cursor = c;
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listview_tourstreet, null);
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        viewHolder.tvName = view.findViewById(R.id.tvStreetName);
        viewHolder.tvAddress = view.findViewById(R.id.tvStreetAddr);
        viewHolder.tvStoreNum = view.findViewById(R.id.tvStreetStoreNum);
        viewHolder.tvLength = view.findViewById(R.id.tvStreetLength);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.tvName.setText(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_NAME)));
        viewHolder.tvAddress.setText(cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_ADDR)));
        viewHolder.tvStoreNum.setText("점포수: " + cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_STORENUM)));
        viewHolder.tvLength.setText("총 길이: " + cursor.getString(cursor.getColumnIndex(StreetDBHelper.COL_LENGTH)) + " m");
    }

    //    ※ findViewById() 호출 감소를 위해 필수로 사용할 것
    static class ViewHolder {
        public TextView tvName = null;
        public TextView tvAddress = null;
        public TextView tvStoreNum = null;
        public TextView tvLength = null;
    }
}