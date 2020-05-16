package org.springframework.samples.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.samples.model.Address;

public class MemberRegistRequest { //순수한 Form입력받을거니까 id필요없음
	/* step1 정보 */
	@NotBlank
	private String name;				// 이름*
	@NotBlank 
	@Email(regexp="^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$")
	private String email;				// ID(이메일 주소 형식)*
	@NotBlank @Size(min=6)
	private String password;			// 암호*
	@NotBlank
	private String confirmPassword;		// 암호 확인*
	
	@NotBlank @Pattern(regexp="[0][1]\\d{1}\\-\\d{3,4}\\-\\d{4}")
	private String phone;				// 전화번호*
	@Valid
	private Address address;			// 주소: street, city, zipcode
	
	/* step2 정보 */
	private String type;		// 공연 종류
	private String song;		// 곡명
	private String time;		// 공연 시간 (30분 이내)
	private String area;		// 선호 지역
	private boolean firstTime;	// 첫 공연 여부

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public boolean isSamePasswordConfirmPassword() {
		if (password == null || confirmPassword == null)
			return false;
		return password.equals(confirmPassword);
	}

	public boolean hasPassword() {
		return password != null && password.trim().length() > 0;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	@Override
	public String toString() {
		return "MemberRegistRequest [name=" + name + ", address=" + address + ", email=" + email + ", password="
		+ password + ", confirmPassword=" + confirmPassword + ", phone=" + phone + ", type=" + type + ", area=" + area + "]";
	}
}
