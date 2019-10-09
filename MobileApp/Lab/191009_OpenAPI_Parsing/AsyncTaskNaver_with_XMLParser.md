# 소스코드
```java
package mobile.exam.network.sample.openapi_with_parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;


public class NaverBookXmlParser {

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

    public NaverBookXmlParser() {
        try {
            parser = XmlPullParserFactory.newInstance().newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<NaverBookDto> parse(String xml) {
        ArrayList<NaverBookDto> resultList = new ArrayList<>();
        NaverBookDto dto = null;
        TagType tagType = TagType.NONE;

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

        return resultList;
    }
}
```
