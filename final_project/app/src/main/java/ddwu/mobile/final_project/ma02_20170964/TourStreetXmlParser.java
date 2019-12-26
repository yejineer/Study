package ddwu.mobile.final_project.ma02_20170964;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.util.ArrayList;

public class TourStreetXmlParser {
    // { stretNm(거리명), stretIntrcn(거리소개), rdnmadr(도로명 주소), storNumber(점포 수), insttNm(제공 기관명)}
    public enum TagType { NONE, NAME, INFO, ADDR, LENGTH, STORENUM, INSTITUTION, LATITUDE, HARDNESS};

    public TourStreetXmlParser() {
    }

    public ArrayList<TourStreetDto> parse(BufferedReader reader) {

        ArrayList<TourStreetDto> resultList = new ArrayList();
        TourStreetDto dto = null;

        TagType tagType = TagType.NONE;

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(reader);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("record")) {  //item
                            dto = new TourStreetDto();
                        } else if (parser.getName().equals("거리명")) { //stretNm
                            if (dto != null) tagType = TagType.NAME;
                        } else if (parser.getName().equals("거리소개")) { //stretIntrcn
                            if (dto != null) tagType = TagType.INFO;
                        } else if (parser.getName().equals("소재지도로명")) { // rdnmadr
                            if (dto != null) tagType = TagType.ADDR;
                        }  else if (parser.getName().equals("총길이")) {
                            if (dto != null) tagType = TagType.LENGTH;
                        }  else if (parser.getName().equals("점포수")) { //storNumber
                            if (dto != null) tagType = TagType.STORENUM;
                        } else if (parser.getName().equals("제공기관명")) { //insttNm
                            if (dto != null) tagType = TagType.INSTITUTION;
                        } else if (parser.getName().equals("위도")) { //latitude
                            if (dto != null) tagType = TagType.LATITUDE;
                        } else if (parser.getName().equals("경도")) { //hardness
                            if (dto != null) tagType = TagType.HARDNESS;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("record")) {
                            resultList.add(dto);
                            dto = null;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        switch(tagType) {
                            case NAME:
                                dto.setName(parser.getText());
                                break;
                            case INFO:
                                dto.setStreetInfo(parser.getText());
                                break;
                            case ADDR:
                                dto.setAddress(parser.getText());
                                break;
                            case LENGTH:
                                dto.setLength(parser.getText());
                                break;
                            case STORENUM:
                                dto.setStoreNum(parser.getText());
                                break;
                            case INSTITUTION:
                                dto.setInstitution(parser.getText());
                                break;
                            case LATITUDE:
                                dto.setLatitude(parser.getText());
                                break;
                            case HARDNESS:
                                dto.setHardness(parser.getText());
                                break;
                        }
                        tagType = TagType.NONE;
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