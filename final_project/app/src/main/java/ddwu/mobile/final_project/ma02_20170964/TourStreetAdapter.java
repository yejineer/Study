package ddwu.mobile.final_project.ma02_20170964;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TourStreetAdapter extends BaseAdapter {

    public static final String TAG = "MyTourStreetAdapter";

    private LayoutInflater inflater;
    private Context context;
    private int layout;
    private ArrayList<TourStreetDto> list;


    public TourStreetAdapter(Context context, int resource, ArrayList<TourStreetDto> list) {
        this.context = context;
        this.layout = resource;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public TourStreetDto getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return list.get(position).get_id();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "getView with position : " + position);
        View view = convertView;
        ViewHolder viewHolder = null;

        if (view == null) {
            view = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.tvStoreName);
            viewHolder.tvAddress = view.findViewById(R.id.tvStoreAddr);
            viewHolder.tvStoreNum = view.findViewById(R.id.tvStoreNum);
            viewHolder.tvLength = view.findViewById(R.id.tvLength);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TourStreetDto dto = list.get(position);

        viewHolder.tvName.setText(dto.getName());
        viewHolder.tvAddress.setText(dto.getAddress());
        viewHolder.tvStoreNum.setText("점포수: " + dto.getStoreNum());
        viewHolder.tvLength.setText("총 길이: " + dto.getLength() + " m");

        return view;
    }

    public void setList(ArrayList<TourStreetDto> list) {
        this.list = list;
    }


    //    ※ findViewById() 호출 감소를 위해 필수로 사용할 것
    static class ViewHolder {
        public TextView tvName = null;
        public TextView tvAddress = null;
        public TextView tvStoreNum = null;
        public TextView tvLength = null;
    }
}