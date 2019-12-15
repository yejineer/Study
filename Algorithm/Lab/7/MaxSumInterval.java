package maxSumInterval;

import java.util.Random;

public class MaxSumInterval {

	public static int max_sum_DC(int[] A, int l, int r) {
		if (l == r) return A[l];
		int m = (l+r)/2;
		int max = 0;
		
		int lv = max_sum_DC(A, l, m);
		int rv = max_sum_DC(A, m+1, r);
		
		max = lv > rv ? lv : rv;
		
		int cv = 0;
		int local_max = A[m+1];
		int local_sum = 0;
		for (int i = m+1; i <= r; i++) {
			local_sum += A[i];
			if (local_max < local_sum) local_max = local_sum;
		}
		cv += local_max;
		local_max = A[m];
		local_sum = 0;
		for (int i = m-1; i >= l; i--) {
			local_sum += A[i];
			if (local_max < local_sum) local_max = local_sum;
		}
		cv += local_max;
		max = max > cv ? max : cv;
		return max;
	}
	public static int max_sum_DP(int[] A) {
		int[] maxEndsAt = new int[A.length];
		maxEndsAt[0] = A[0];
		int max = maxEndsAt[1];
		for (int i = 1; i < A.length; i++) {
			maxEndsAt[i] = Math.max(maxEndsAt[i-1]+A[i], A[i]);
			maxEndsAt[i] = maxEndsAt[i] > A[i]? maxEndsAt[i] : A[i];
			max = max > maxEndsAt[i]? max : maxEndsAt[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int[] arr = null;
		
		arr = new int[50000];
		for (int i = 0; i < arr.length; i++)
			arr[i] = r.nextBoolean() ? r.nextInt(100) : -1*r.nextInt(100);
		
		System.out.println("Num of Items: " + arr.length);
		long start = System.currentTimeMillis();
		int value = max_sum_DP(arr);
		long end = System.currentTimeMillis();
		System.out.println("Running Time(DP): " + (end-start));
		System.out.println(max_sum_DP(arr));
	}

}
