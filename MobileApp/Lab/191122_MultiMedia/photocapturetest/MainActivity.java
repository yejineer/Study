package ddwu.com.mobile.multimedia.photo.photocapturetest_completed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    /*DATA*/
//    final static int PERMISSION_REQ_CODE = 100;       // TODO: 외부저장소의 공용폴더에 저장할 때 사용할 것
    private final static int REQUEST_TAKE_THUMBNAIL = 100;  // 저화질 이미지 사용 요청
    private static final int REQUEST_TAKE_PHOTO = 200;  // 원본 이미지 사용 요청
    private File photoFile;

    /*UI*/
    private ImageView mImageView;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);

//        외부 저장소 확인해보기
        Log.i(TAG, getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
        Log.i(TAG, getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
    }


    @Override
    protected void onPause() {
        super.onPause();
//        if (photoFile != null) photoFile.delete();
    }


    public void onClick(View v)  {
        switch(v.getId()) {
            case R.id.btnCapture:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_THUMBNAIL);
                }
                break;
            case R.id.btnSave:
//                 TODO: 외부저장소의 공용 폴더에 저장할 때 사용할 것
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQ_CODE);
//                }

                dispatchTakePictureIntent();
                break;
            case R.id.btnAddGallery:
                galleryAddPic();
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_THUMBNAIL && resultCode == RESULT_OK) { // 저화질 이미지 요청 결과 처리
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {  // 고화질 이미지 요청 결과 처리
            setPic();
        }
    }




    /*원본 사진 요청*/
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 요청을 처리할 수 있는 카메라 앱이 있을 경우
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // 사진을 저장할 파일 생성
            photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // 파일을 정상 생성하였을 경우
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "ddwu.com.mobile.multimedia.photo.fileprovider",    // 다른 앱에서 내 앱의 파일을 접근하기 위한 권한명 지정
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    /*이미지뷰에 지정할 수 있는 크기로 이미지를 변경하여 표시*/
    private void setPic() {
        // 이미지뷰의 가로/세로 크기 확인
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // 원본 사진의 이미지 가로/세로 크기 확인
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;    // 메모리에 로딩하진 않고 실제 크기만 확인 시 true
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // 원본사진과 이미지뷰의 크기 비율 계산
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        // 지정한 옵션에 따라 비트맵을 메모리에 로딩
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }



    /*현재 시간을 사용한 파일명으로 앱전용의 외부저장소에 파일 정보 생성*/
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);  // TODO: 외부저장소의 공용폴더에 저장할 때 사용할 것
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",   /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.i(TAG, "Created file path: " + mCurrentPhotoPath);
        return image;
    }


    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File (mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }




//    TODO: 외부저장소의 공용폴더에 저장할 때 사용할 것
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQ_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "외부저장소 쓰기 권한 획득!", Toast.LENGTH_SHORT).show();
//                    dispatchTakePictureIntent();
//                } else {
//                    finish();
//                    Toast.makeText(this, "외부저장소 쓰기 권한 없음", Toast.LENGTH_SHORT).show();
//                }
//
//        }
//    }


}
