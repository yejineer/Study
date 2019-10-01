package q2_palindrome;

import java.util.Scanner;

public class findingPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		String s = scan.next();
		
		char center = s.charAt(s.length()-1);
		
		
	}
	
	public static boolean isPalindrome(String s) {
		int len = s.length();
		for (int i=0; i<len/2; i++) {
			if (s.charAt(i) != s.charAt(len-1-i))
				return false;
		}
		return false;
	}

}
