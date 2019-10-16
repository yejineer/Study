# 앱 설명
- 네이버의 책 검색을 수행하는 앱을 작성하시오.
- AsyncTaskNaver_with_XMLParser_and_File_sample 사용  
- 작성 부분
  - MyBookAdapter의 getView() 메소드
  - MyBookAdapter의 GetImageAsyncTask 내부의 onPostExecute() 메소드
  - MainActivity의 리스트뷰 onItemLongClick() 메소드
- 상황
  - 앱 종료
    - 내부 · 외부 파일 삭제 X
  - 앱 삭제
    - 내부 · 외부 파일 삭제 O
- 자바 클래스
  - [ImageFileManager.java](MobileApp/Lab/191016_AppData_File/src/ImageFileManager.java) : ImageFile들을 관리하는 클래스
  - [MainActivity.java](MobileApp/Lab/191016_AppData_File/src/MainActivity.java)
  - [MyBookAdapter.java](MobileApp/Lab/191016_AppData_File/src/MyBookAdapter.java)
  - [NaverBookDto.java](MobileApp/Lab/191016_AppData_File/src/NaverBookDto.java)
  - [NaverBookXmlParser.java](MobileApp/Lab/191016_AppData_File/src/NaverBookXmlParser.java)
    
## MyBookAdapter의 getView() 메소드
```java
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "getView with position : " + position);
        View view = convertView;
        ViewHolder viewHolder = null;

        if (view == null) {
            view = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = view.findViewById(R.id.tvTitle);
            viewHolder.tvAuthor = view.findViewById(R.id.tvAuthor);
            viewHolder.ivImage = view.findViewById(R.id.ivImage);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        NaverBookDto dto = list.get(position);

        viewHolder.tvTitle.setText(dto.getTitle());
        viewHolder.tvAuthor.setText(dto.getAuthor());

        /*작성할 부분*/
        /*dto 에 저장하고 있는 이미지 주소를 사용하여 이미지 파일이 내부저장소에 있는지 확인
        * ImageFileManager 의 내부저장소에서 이미지 파일 읽어오기 사용
        * 이미지 파일이 있을 경우 bitmap, 없을 경우 null 을 반환하므로 bitmap 이 있으면 이미지뷰에 지정
        * 없을 경우 GetImageAsyncTask 를 사용하여 이미지 파일 다운로드 수행 */

        Bitmap bitmap = imageFileManager.getSavedBitmapFromInternal(dto.getImageLink());
        if (bitmap != null) {   // 이미 저장된 파일이 있으면 가져오고, 없으면 null 반환
            viewHolder.ivImage.setImageBitmap(bitmap);
            Log.i(TAG, "Loaded from file!");
        } else {
            GetImageAsyncTask imgTask = new GetImageAsyncTask(viewHolder);
            try {
                imgTask.execute(dto.getImageLink());
                Log.d(TAG, "Image downloading!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return view;
    }
```
  
## MyBookAdapter의 GetImageAsyncTask 내부의 onPostExecute() 메소드
```java
/* 책 이미지를 다운로드 후 내부저장소에 파일로 저장하고 이미지뷰에 표시하는 AsyncTask */
    class GetImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        ViewHolder viewHolder;
        String imageAddress;

        public GetImageAsyncTask(ViewHolder holder) {
            viewHolder = holder;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            imageAddress = params[0];
            Log.i(TAG, imageAddress);
            Bitmap result = downloadImage(imageAddress);
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            /*작성할 부분*/
            /*네트워크에서 다운 받은 이미지 파일을 ImageFileManager 를 사용하여 내부저장소에 저장
            * 다운받은 bitmap 을 이미지뷰에 지정*/
            if (bitmap != null) {
                Log.i(TAG,"downloading complete");
                viewHolder.ivImage.setImageBitmap(bitmap);
                imageFileManager.saveBitmapToInternal(bitmap, imageAddress); // 추가된 문장. 네트워크로 받아와서 파일로 저장.
            } else {
                viewHolder.ivImage.setImageResource(R.mipmap.ic_launcher);
            }

        }
        
        ... 생략 ...
        
    }
  ```  
    
  ## MainActivity의 리스트뷰 onItemLongClick() 메소드 (onCreate안에서 구현함)
  ```java
        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                /* 작성할 부분 */
                /*롱클릭한 항목의 이미지 주소를 가져와 내부 메모리에 지정한 이미지 파일을 외부저장소로 이동
                * ImageFileManager 의 이동 기능 사용
                * 이동을 성공할 경우 파일 명, 실패했을 경우 null 을 반환하므로 해당 값에 따라 Toast 출력*/

                ImageView image = view.findViewById(R.id.ivImage); // 리스트 뷰의 클릭한 위치에 있는 뷰 확인
                NaverBookDto dto = resultList.get(position);

                String result = imgManager.moveStorage(dto.getImageLink()); // 이동시키기.
                if (result == null)  // 이동 실패 시
                    Toast.makeText(MainActivity.this, "이동 실패!", Toast.LENGTH_SHORT).show();
                else if (result == "Already Moved!")
                    Toast.makeText(MainActivity.this, "이미 이동함!(파일명: " + result + ")", Toast.LENGTH_SHORT).show();
                else // 이동 성공 시 result == newFileName
                    Toast.makeText(MainActivity.this, "이동 성공(파일명: " + result + ")", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
```
  
