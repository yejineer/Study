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
            viewHolder.tvStoreNum = view.findViewById(R.id.tvStorePhone);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TourStreetDto dto = list.get(position);

        viewHolder.tvName.setText(dto.getName());
        viewHolder.tvAddress.setText(dto.getAddress());
        viewHolder.tvStoreNum.setText("점포수: " + dto.getStoreNum());


        /*작성할 부분*/
        /*dto 에 저장하고 있는 이미지 주소를 사용하여 이미지 파일이 내부저장소에 있는지 확인
         * ImageFileManager 의 내부저장소에서 이미지 파일 읽어오기 사용
         * 이미지 파일이 있을 경우 bitmap, 없을 경우 null 을 반환하므로 bitmap 이 있으면 이미지뷰에 지정
         * 없을 경우 GetImageAsyncTask 를 사용하여 이미지 파일 다운로드 수행 */


////        dto 에 기록한 이미지 주소를 사용하여 이미지 파일을 읽어오기 수행
//        Bitmap savedBitmap = imageFileManager.getSavedBitmapFromInternal(dto.getImageLink());
//
////        파일에서 이미지 파일을 읽어온 결과에 따라 파일 이미지 사용 또는 네트워크 다운로드 수행
//        if (savedBitmap != null) {
//            viewHolder.ivImage.setImageBitmap(savedBitmap);
//            Log.d(TAG,  "Image loading from file");
//        } else {
//            viewHolder.ivImage.setImageResource(R.mipmap.ic_launcher);  // 이미지를 다운받기 전엔 기본 이미지로 설정
//            GetImageAsyncTask task = new GetImageAsyncTask(viewHolder);
//            task.execute(dto.getImageLink());
//            Log.d(TAG,  "Image loading from network");
//        }

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
    }
}