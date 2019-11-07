# Vladmir Yroslavskiy's algorithm
- Dual pivot quicksort + Insertion sort algorithm
- For small subarrays, use the insertion sort algorithm
  - Why? 어느 정도 수 이하는 insertion sort를 쓰는 것이 더 빠름
    
## 수행 시간 측정
- System.currentTimeMillis() 이용
```java
long startTime = System.currentTimeMillis();

// 수행 시간 측정하려는 작업 구현

long endTime = System.currentTimeMillis();

long elapsedTime = startTime - endTime;
System.out.println("수행 시간: " + elapsedTime + " ms");
```

```java
package q1_VladimirAlgorithm;

import java.util.Random;

public class Vladimir {

	public static void insertionSort(int[] arr) {
		int temp, j, minIdx;

		for (int i = 1; i < arr.length; i++) {
			// i보다 작은 j(i-1, ..., 0)중에서 arr[j]>arr[i]인 값들 중 가장 값이 작은 요소 찾기
			minIdx = -1;
			for (j = i - 1; j >= 0; j--)
				if (arr[j] > arr[i]) {
					minIdx = j;
					if (arr[j] < arr[minIdx])
						minIdx = j;
				}

			if (minIdx != -1) {
				temp = arr[i];
				for (int k = i; k > minIdx; k--) // shift
					arr[k] = arr[k - 1];
				arr[minIdx] = temp;
			}
		}

	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void dualPivotQuicksort(int[] arr, int p, int q) {
		if (q - p + 1 < n0)
			insertionSort(arr);
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
					lt++; k++;
				} else if (arr[k] > arr[q] ) {
					while (arr[gt] > arr[q] && k < gt)
						gt--;
					swap(arr, k, gt); gt--;
				}
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

	public static int n0 = 17;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int len = 2000;
		int[] arr = new int[len];
		Random r = new Random();
		for (int j = 0; j < len; j++) {
			arr[j] = r.nextInt(10000);
//			System.out.println(arr[j]);
		}
//		System.out.println('끝');
		
		long startTime = System.currentTimeMillis();
		dualPivotQuicksort(arr, 0, arr.length - 1);
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("수행 시간: " + elapsedTime / 1000.0 + "s");

//		int[] arr = {5, 2, 11, 9, 7, 3, 6, 8};
//		dualPivotSort(arr);
	}

}

```
# References
- [geeksforgeeks/dual-pivot-quicksort](https://www.geeksforgeeks.org/dual-pivot-quicksort/)  
- [gwpark.tistory.com/QuickSort-DualPivotPartitioning](https://gwpark.tistory.com/1743)
- [paper-Vladimir Yaroslavskiy](https://codeblab.com/wp-content/uploads/2009/09/DualPivotQuicksort.pdf)
- [Average Case Analysis of Java 7’s Dual Pivot Quicksort](https://www.slideshare.net/sebawild/average-case-analysis-of-java-7s-dual-pivot-quicksort)
