// 컴퓨터학과 20170964 이예진
package practice14;

public class Student {
	private int sid;
	private String major, name;
	
	public Student(String major, int sid, String name) {
		this.major = major;
		this.sid = sid;
		this.name = name;
	}
	
	public String toString() {
		return major + "/" + sid + "/" + name;
	}

	public int getSid() {
		return sid;
	}

	public String getMajor() {
		return major;
	}

	public String getName() {
		return name;
	}
	
	
	
}
