package practice20;

import java.util.Random;
import java.util.Scanner;

public class Practice20 {

	static Score[][] scoreBoard = { { Score.EQUAL, Score.LOSE, Score.WIN }, { Score.WIN, Score.EQUAL, Score.LOSE },
			{ Score.LOSE, Score.WIN, Score.EQUAL } };

	public static Score whoswin(Game user, Game com) {
		return scoreBoard[user.getGameNum()][com.getGameNum()];
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		/* ��ǻ�� �� ���� */
		Random r = new Random();
		int num = r.nextInt(3);

		Game com;
		if (num == 0) {
			com = Game.ROCK;
		} else if (num == 1) {
			com = Game.SCISSORS;
		} else {
			com = Game.PAPER;
		}

		/* ��� ���� */
		System.out.print("���ϴ� �� �����ϼ��� (1-�ѱ���/2-����/3-�Ϻ���): ");
		int ln = s.nextInt();
		s.close();

		/* �� ���� ��ǻ�� �� ���� */
		Language l = null;
		if (ln == 1) {
			l = new Korean(com);
		} else {
			l = new English(com);
		} // �ش� ��� Ŭ������ �ش� Ŭ���� ��ü �����ϴ� �κи� �߰����ָ� ��
		System.out.println(l.decode(com));

		Game user = l.encode();
		try {
			System.out.println(l.print(whoswin(user, com)));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�ٽ� �Է��Ͻʽÿ�.");
		}
	}
}