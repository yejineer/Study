package org.springframework.samples.model;

public class MemberInfo {
	/* step1 정보 */
	private String id;
	private String name;		// 이름*
	private String email;		// ID(이메일 주소 형식)*
	private String password;	// 암호*
	private String phone;		// 전화번호*
	private Address address;	// 주소: street, city, zipcode
	
	/* step2 정보 */
	private String type;		// 공연 종류
	private String song;		// 곡명
	private int time;			// 공연 시간 (30분 이내)
	private String place;		// 공연 장소
	private boolean firstTime;	// 첫 공연 여부

	public MemberInfo() {
	}

	public MemberInfo(String id, String name, String email, String password, String phone, Address address, 
					String type, String song, int time, String place, boolean firstTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.type = type;
		this.song = song;
		this.time = time;
		this.place = place;
		this.firstTime = firstTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean matchPassword(String inputPassword) {
		return password.equals(inputPassword);
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

}
