package q2_consecutiveNumbers;

import java.util.Scanner;

public class Consecutive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		System.out.print("n을 입력하세요: ");
		int target = scan.nextInt();
		
		solution(items, bucket, 5, target);  //5개일때
		
	}
	
	static final int items[] = {1, -1};
	static final int bucket[] = new int[5];
	
	public static void solution(int[] items, int[] bucket, int k, int target) {
		if (k == 0) {
			int sum = 0;
			for (int i = 0; i < bucket.length; i++) {
				sum += bucket[i] * (i+1);
			}
			if (sum == target) {
				for (int i = 0; i < bucket.length; i++) {
					if (bucket[i] == 1)
						System.out.print("+");
					else
						System.out.print("-");
					System.out.print(i+1);
				}
				System.out.println();
			}
			return;
		}
		
		
		int lastIndex = bucket.length - k - 1; //가장 최근에 뽑힌 수가 저장된 위치 index
		
		for (int i=0; i<items.length; i++) //candidate items
		{
			bucket[lastIndex+1] = items[i];
			solution(items, bucket, k-1, target);
		}
	}

}
