package q1_FindingPathinFantasia;

import java.util.Scanner;

public class FindingPathInFantasia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner scan = new Scanner(System.in);
		double n = scan.nextDouble();
		double m = scan.nextDouble();
		
		System.out.println(FindPath(n, m));
		
	}
	
	public static String FindPath(double n, double m) {
		double n1 = 0, m1 = 1, n2 = 1, m2 = 0;
		double n3 = n1 + n2, m3 = m1 + m2;
		String s = "";
		
		while (n != n3 && m != m3) {
			if (n/m < n3/m3) {
				s += "L";
				n2 = n3;
				m2 = m3;
				n3 = n1 + n2;
				m3 = m1 + m2;
			}
			else {
				s += "R";
				n1 = n3;
				m1 = m3;
				n3 = n1 + n2;
				m3 = m1 + m2;
			}
		}
		return s;
	}

}
