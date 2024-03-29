package q2_consecutiveNumbers;

import java.util.Scanner;

public class CompleteVersion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int items[] = {1, -1};
		int k = 1;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("n을 입력하세요: ");
		int target = scan.nextInt();
		
		while (sum(k) < target)
			k++;
		
		while(true) {
			int []bucket = new int[k];
			if (solution(items, bucket, k, target))
				break;
			k++;
		}
		
	}
	
	public static int sum(int n) {
		return n * (n+1) / 2;
	}
	
	public static boolean solution(int[] items, int[] bucket, int k, int target) {
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
				return true;
			}
			return false;
		}
		
		
		int lastIndex = bucket.length - k - 1; //가장 최근에 뽑힌 수가 저장된 위치 index
		boolean ret = false;
		
		for (int i=0; i<items.length; i++) //candidate items
		{
			bucket[lastIndex+1] = items[i];
			ret = solution(items, bucket, k-1, target) || ret; //둘중에 하나만 True면 True
		}
		return ret;
	}

}
