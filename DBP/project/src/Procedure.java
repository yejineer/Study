package model;
/**
 * @author bambi
 * �ϳ��� �������� ������ ������ �ϳ��ϳ��� ��Ÿ���� ���� �ʿ��� ������ Ŭ����
 * Recipe_Procedure ���̺�� ������
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
	
	/* ������ ����Ͻ� ����: text�� null���� Ȯ��(������ �� ������ ���� ������ ������ Ȯ��) */
	public boolean isTextNull(Procedure procedure) {
		if (procedure.text == null)
			return true;
		return false;
	}
	
	/* ������ ����Ͻ� ����: img_url�� null���� Ȯ�� */
	public boolean isImg_urlNull(Procedure procedure) {
		if (procedure.img_url == null)
			return true;
		return false;
	}
}
