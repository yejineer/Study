package dduwcom.mobile.a02_20170964_report01;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
    LayoutInflater inflater;
    Cursor cursor;
    int layout;

    public MyCursorAdapter(Context context, int layout, Cursor c) {  //layout도 전달받아서 사용할 수 있게
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        cursor = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return inflater.inflate(layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvMovieTitle = (TextView)view.findViewById(R.id.tv_title);
        TextView tvMovieActor = (TextView)view.findViewById(R.id.tv_actor);
        TextView tvMovieRated = (TextView)view.findViewById(R.id.tv_rated);

        tvMovieTitle.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE)));
        tvMovieActor.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_ACTOR)));
        tvMovieRated.setText(cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RATED)));

        ImageView imageView = (ImageView)view.findViewById(R.id.lv_img);
        //리스트뷰의 아이템의 이미지를 변경
        String title = tvMovieTitle.getText().toString();
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
}
