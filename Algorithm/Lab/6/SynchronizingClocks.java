package q3_synchronizingClocks;

import java.util.Scanner;

public class SynchronizingClocks {

	public static int[] items = {0, 1, 2, 3};
	public static int minPressedCount = 100;
	public static int[] clocks = new int[16];
	public static int connected[][] = new int[10][];  // 각 스위치에 연결된 시계들을 나타내기 위한 배열
	
	public static void solution(int[] items, int[] bucket, int k) {
		if (k == 0) {	// 다 뽑았을 때
			int pressedCnt = changeClocks(bucket);	// 스위치 누른 횟수만큼 그에 연결된 시계들의 시각을 바꿔줌.

			if (pressedCnt != -1) // 현재 시계들이 전부 12시인 경우. 아닌 경우는 -1.
				minPressedCount = pressedCnt < minPressedCount ? pressedCnt : minPressedCount;
			
			return;
		}
		
		int lastIdx = bucket.length - k - 1;
		
		for (int i = 0; i < items.length; i++) {
			bucket[lastIdx + 1] = items[i];
			solution(items, bucket, k-1);
		}
	}
	
	public static int changeClocks(int[] bucket) {
		int pressedCount = 0;
		int[] tmp = new int[clocks.length];
		
		// 사본을 만들어줘야 함. 안 그러면 clocks가 직접 바뀌기 때문에 사용자가 입력한 clocks와 달라짐.
		for (int i = 0; i < clocks.length; i++) {	
			tmp[i] = clocks[i];
		}
		
		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] == 0)	// 스위치 누르지 않은 건 아무런 작업도 안 해줘도 되니까 넘어감
				continue;
			// switch에 연결된 시계들 전부 버튼 누른 횟수(bucket[i]) * 3(시간)만큼 더해줘야 함 
			for (int j = 0; j < connected[i].length; j++)
				tmp[connected[i][j]] += bucket[i] * 3;
			pressedCount += bucket[i];
		}
		
		for (int i = 0; i < tmp.length; i++) // 현재 시계들이 전부 12시인지 확인
			if (tmp[i] % 12 != 0)	// 15, 18은 나머지 3, 6 이므로 어쨌든 같은 시각을 가리킴
				return -1;
		
		return pressedCount;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int[] bucket = new int[10];
		
		// 사용자 입력
		for (int i = 0; i < 10; i++) {
			int switchIdx = scan.nextInt();		// 스위치 번호(0~10)
			int numOfClocks = scan.nextInt();	// 각 스위치에 연결된 시계의 수 
			connected[switchIdx] = new int[numOfClocks]; // 스위치가 어떤 시계들과 연결됐는지를 보여주기 위한 배열
			
			for (int j = 0; j < numOfClocks; j++)
				connected[switchIdx][j] = scan.nextInt();	
		}
		
		for (int i = 0; i < 16; i++)		// 현재 시계들의 상태를 사용자에게 입력 받음
			clocks[i] = scan.nextInt();
		
		solution(items, bucket, 10);		 // 스위치를 최소 몇 번 눌러야 모두 12시가 되는 지를 알기 위한 함수
		System.out.println(minPressedCount); // solution을 수행하고 얻게 된 결과를 출력
	}

}
