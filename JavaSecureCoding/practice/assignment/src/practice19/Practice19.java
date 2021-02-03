// ��ǻ���а� 20170964 �̿���
package practice19;

import java.util.Scanner;

public class Practice19 {

	static Score[][] scoreBoard = {
			{Score.EQUAL,  Score.LOSE, Score.WIN},
			{Score.WIN,  Score.EQUAL, Score.LOSE},
			{Score.LOSE,  Score.WIN, Score.EQUAL}
	};
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		Game com = Game.SCISSORS;
		System.out.println("��ǻ���� �Է� : " + com.decode());
		
		System.out.print("����� �Է� : ");
		String yourInput = s.next();
		Game user = Game.encode(yourInput);
		
		try {
			Score rslt = whoswin(user, com);
			System.out.println(Score.print(rslt));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�ٽ� �Է��Ͻʽÿ�.");
		}
		s.close();
	}
	
	public static Score whoswin(Game user, Game com) {
		return scoreBoard[user.getGameNum()][com.getGameNum()];
	}

}