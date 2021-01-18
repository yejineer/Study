// 컴퓨터학과 20170964 이예진
package practice14;

import java.util.Comparator;

public class SortByMajorNum implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		String m1 = o1.getMajor();
		String m2 = o2.getMajor();
		
		int majorResult = m1.compareTo(m2);
		
		if (majorResult == 0) {
			int s1 = o1.getSid();
			int s2 = o2.getSid();
			
			if (s1<s2) return -1;
			else if (s1>s2) return 1;
			else return 0;			
		} else 
			return majorResult;
	}
	
	
}
