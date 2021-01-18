// 컴퓨터학과 20170964 이예진
package practice11;

public class GameCharacterTest {

	public static void main(String[] args) {
		GameCharacter charac = new GameCharacter();
		
		charac.add("Sword",  1, 100);
		charac.add("Gun", 2, 50);
		charac.print();
	}

}
