package q1_VladimirAlgorithm;

import java.util.Random;

public class Vladimir {

	public static void insertionSort(int[] arr, int p, int q) {
		int temp, j;

		for (int i = p+1; i <= q; i++) {
			for (j = i - 1; j >= p; j--)
				if (arr[j] < arr[i])
					break;
			j++;
			temp = arr[i];
			
			for (int k = i; k > j; k--) // shift
				arr[k] = arr[k-1];
			arr[j] = temp;
		}

	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void dualPivotQuicksort(int[] arr, int p, int q) {
		if (q - p + 1 < n0)
			insertionSort(arr, p, q);
		else {
			if (p >= q)
				return;
			
			if (arr[q] < arr[p])
				swap(arr, p, q);

			int lt = p + 1;
			int gt = q - 1;
			int k = p + 1; // partitioning하기 위해

			while (k < gt) {
				if (arr[k] < arr[p]) {
					swap(arr, lt, k);
					lt++;
				} else if (arr[k] > arr[q] ) {
					while (arr[gt] > arr[q] && k < gt)
						gt--;
					swap(arr, k, gt); gt--;
					
					if (arr[k] < arr[p]) {
						swap(arr, lt, k);
						lt++;
					}
				}
				k++;
			}

			lt--;
			gt++;
			swap(arr, p, lt);
			swap(arr, q, gt);

			dualPivotQuicksort(arr, p, lt - 1);
			dualPivotQuicksort(arr, lt + 1, gt - 1);
			dualPivotQuicksort(arr, gt + 1, q);
		}
	}

	public static int n0 = 513;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] L = {10, 100, 200, 400, 800, 1600, 3200, 6400};
		for (int i = 0; i < 8; i++) {
			int len = L[i] * 1000;
			int[] arr = new int[len];
			Random r = new Random();
			for (int j = 0; j < len; j++) {
				arr[j] = r.nextInt(640000000);
			}
			long startTime = System.currentTimeMillis();
			dualPivotQuicksort(arr, 0, arr.length - 1);
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
			System.out.println(elapsedTime / 1000.0);

		}
		
	}

}
