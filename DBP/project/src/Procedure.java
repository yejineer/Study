package model;
/**
 * @author bambi
 * 하나의 레시피의 각각의 과정들 하나하나를 나타내기 위해 필요한 도메인 클래스
 * Recipe_Procedure 테이블과 대응됨
 */
public class Procedure {
	private int proc_Id;
	private String text;
	private String img_url;
	
	public Procedure() {}
	
	public Procedure(int proc_Id, String text, String img_url) {
		this.proc_Id = proc_Id;
		this.text = text;
		this.img_url = img_url;
	}

	public int getProc_Id() {
		return proc_Id;
	}

	public void setProc_Id(int proc_Id) {
		this.proc_Id = proc_Id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	/* 간단한 비즈니스 로직: text가 null인지 확인(레시피 각 과정에 대한 설명이 없는지 확인) */
	public boolean isTextNull(Procedure procedure) {
		if (procedure.text == null)
			return true;
		return false;
	}
	
	/* 간단한 비즈니스 로직: img_url이 null인지 확인 */
	public boolean isImg_urlNull(Procedure procedure) {
		if (procedure.img_url == null)
			return true;
		return false;
	}
}
