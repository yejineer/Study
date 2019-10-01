package q1_card;

import java.util.Scanner;

public class card {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		
		cardConversion(s);
	
	}
	
	public static int[][] cards =
	{
		{1, 2, 3, 4, 5, 6, 7, 8},
		{1, 2, 3, 4, 9, 10, 11, 12},
		{1, 2, 5, 6, 9, 10, 13, 14},
		{1, 3, 5, 7, 9, 11, 13, 15}
	};
	
	public static String[] cardString = new String[16];
	
	public static char[][] cards2 = new char[16][4];	//행: 숫자 1~15	열: card1~4
	
	public static void cardConversion(String s) {
		for (int i=0; i<cards2.length; i++) {
			for (int j=0; j<cards2[i].length; j++)
				cards2[i][j]='N';
		} 
		
		for (int i=0; i<4; i++) {
			for (int j=0;j<cards[i].length; j++) {
				cards2[cards[i][j]][i] = 'Y';
			}
		}
		
		for (int i=0; i<cardString.length; i++) {
			cardString[i] = new String(cards2[i]);
			if (s.equals(cardString[i])) {
				System.out.println(i);
			}
		}
		
	
	}

}
