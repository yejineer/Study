import java.util.Scanner;

public class Practice17A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("날짜를 입력하세요 : ");
		String userInput = scan.nextLine();
		
		String[] numbers = userInput.split("-");
		
		Date date = new Date(Integer.valueOf(numbers[0]), Integer.valueOf(numbers[1]), Integer.valueOf(numbers[2]));
		System.out.println("서양식 표기 : " + date.printWestern());
	}

}
