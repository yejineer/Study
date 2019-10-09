# AsyncTaskNaver_with_XMLParser
네이버 오픈 API의 검색 결과를 Parsing하는 앱을 완성하시오.  
  
#### 네이버 책 검색 API
* 네이버 책 검색 결과를 출력해주는 REST API
* 비로그인 오픈 API  
  → GET으로 호출할 때 HTTP Header에 애플리케이션 등록 시 발급받은 Client ID와 Client Secret 값을 같이 전송하면 활용 가능  
  
#### 요청 시 응답결과
![1](https://user-images.githubusercontent.com/50271884/66453673-2b156480-eaa0-11e9-9b8d-0224ea1112e5.PNG)  
  
### ⚠️ 주의사항
#### 네이버 개발자 사이트에서 애플리케이션 등록 후 진행!
1. Client ID 및 Client Secret을 발급 받아 strings.xml에 설정
1. 애플리케이션 이름은 임의로 지정 (예: 오픈API테스트)
1. 사용 API는 [검색] 선택
1. 비로그인 오픈API 서비스 환경은 [안드로이드] 선택 후 프로젝트의 패키지명 기록  
  
# ① NaverBookXmlParser 클래스  
### 큰 틀
1. 변수 생성
```java
//    xml에서 읽어들일 태그를 구분한 enum (열거형) → 정수값 등으로 구분하지 않고 가독성 높은 방식을 사용
    private enum TagType { NONE, TITLE, AUTHOR, LINK, IMAGELINK};     // 해당없음, title, author, link, imagelink
    //    parsing 대상인 tag를 상수로 선언
    private final static String FAULT_RESULT = "faultResult"; // 숫자 잘못 넣어서 xml 받아올 때
    private final static String ITEM_TAG = "item";
    private final static String TITLE_TAG = "title";
    private final static String AUTHOR_TAG = "author";
    private final static String LINK_TAG = "link";
    private final static String IMAGE_TAG = "image";
    private XmlPullParser parser;
```  
  
1. XmlPullParserFactory 객체 **factory 생성** (try 안에서)
1. factory 객체 생성해 XmlPullParser 객체 **parser 생성** (try 안에서)
1. String타입의 xml을 매개변수로 받아와 ArrayList<NaverBookDto> 타입으로 반환해주는 메소드 **parse 생성**
  
### 메소드 parse 내부
#### 1. 변수 생성
```java
ArrayList<NaverBookDto> resultList = new ArrayList<>();
NaverBookDto dto = null;
TagType tagType = TagType.NONE;     //태그를 구분하기 위한 enum 변수 초기화
```
#### 2. try-catch 내부
```java
try {
            parser.setInput(new StringReader(xml));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if (tag.equals(ITEM_TAG)) {
                            dto = new NaverBookDto();
                        } else if (tag.equals(TITLE_TAG)) {
                        // <channel>의 자식인 <title>도 있고 <item>의 자식인 <title>도 있어서 구분해야 함
                            if (dto != null) { 
                                tagType = TagType.TITLE;
                            }
                        } else if (tag.equals(AUTHOR_TAG)) {
                            tagType = TagType.AUTHOR;
                        } else if (tag.equals(LINK_TAG)) {
                            if (dto != null) {
                                tagType = TagType.LINK;
                            }
                        } else if (tag.equals(IMAGE_TAG)) {
                            tagType = TagType.IMAGELINK;
                        } else if (tag.equals(FAULT_RESULT)) { // 정상적인 xml문서가 아님을 표시
                            return null;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals(ITEM_TAG)) {
                            resultList.add(dto);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagType) {       // 태그의 유형에 따라 dto 에 값 저장
                            case TITLE:
                                dto.setTitle(parser.getText());
                                break;
                            case AUTHOR:
                                dto.setAuthor(parser.getText());
                                break;
                            case LINK:
                                dto.setLink(parser.getText());
                                break;
                            case IMAGELINK:
                                dto.setImageLink(parser.getText());
                                break;
                        }
                        tagType = TagType.NONE; // 관심있는 태그 처리 후 다시 초기화
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
```
#### 3. 생성된 resultList 반환  
