import java.util.Scanner;

public class Practice17A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("��¥�� �Է��ϼ��� : ");
		String userInput = scan.nextLine();
		
		String[] numbers = userInput.split("-");
		
		Date date = new Date(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1]), Integer.valueOf(numbers[2]));
		System.out.println("����� ǥ�� : " + date.printWestern());
	}

}
