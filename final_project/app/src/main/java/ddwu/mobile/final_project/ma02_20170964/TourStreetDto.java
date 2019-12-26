package ddwu.mobile.final_project.ma02_20170964;

import java.io.Serializable;

public class TourStreetDto implements Serializable {

    private int _id;
    private String name;            // 거리명
    private String streetInfo;      // 거리 정보
    private String address;         // 도로명 주소
    private String length;          // 총 길이(m)
    private String storeNum;        // 점포 수
    private String institution;     // 관리 기관 명
    private String latitude;        // 위도
    private String hardness;        // 경도

    public TourStreetDto() {
    }

    public int get_id() { return _id; }
    public void set_id(int _id) { this._id = _id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStreetInfo() { return streetInfo; }
    public void setStreetInfo(String streetInfo) { this.streetInfo = streetInfo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getLength() { return length; }
    public void setLength(String length) { this.length = length; }

    public String getStoreNum() { return storeNum; }
    public void setStoreNum(String storeNum) { this.storeNum = storeNum; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public String getLatitude() { return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getHardness() { return hardness; }
    public void setHardness(String hardness) { this.hardness = hardness; }
}

