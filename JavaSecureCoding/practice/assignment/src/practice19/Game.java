// ��ǻ���а� 20170964 �̿���
package practice19;

public enum Game {
	ROCK(0), PAPER(1), SCISSORS(2), ERROR(-1);
	
	// Constructor
	Game(int num) {
		GameNum = num;
	}
	
	int GameNum;
	public int getGameNum() {
		return GameNum;
	}
	
	public static Game encode(String input) {
		switch (input) {
			case "����":
				return ROCK;
			case "��":
				return PAPER;
			case "����":
				return SCISSORS;
			default:
				return ERROR;
		}
	}
	
	public String decode() {
		switch (name()) {
			case "ROCK":
				return "����";
			case "PAPER":
				return "��";
			case "SCISSORS":
				return "����";
			default:
				return "����";
		}
	}
}