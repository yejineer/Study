[>> 문제 보러가기](https://www.hackerrank.com/challenges/ctci-bubble-sort/problem)  

## 문제 설명
Given an array of integers, sort the array in ascending order using the Bubble Sort algorithm above. Once sorted, print the following three lines:  
  
> Array is sorted in numSwaps swaps., where  is the number of swaps that took place.  
> First Element: firstElement, where  is the first element in the sorted array.  
> Last Element: lastElement, where  is the last element in the sorted array.  
  
Hint: To complete this challenge, you must add a variable that keeps a running tally of all swaps that occur during execution.

   
## Example
### a [6, 4, 1]
swap    a         
0       [6,4,1]  
1       [4,6,1]  
2       [4,1,6]  
3       [1,4,6]  
The steps of the bubble sort are shown above. It took 3 swaps to sort the array. Output is:  
  
Array is sorted in 3 swaps.    
First Element: 1  
Last Element: 6  
  
## 소스 코드
```java
package sort;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Q1 {

	// Complete the countSwaps function below.
	static void countSwaps(int[] a) {
		int count = 0;
		List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList());
		
		int i = 0;
		while (i < a.length-1){
			boolean tag = true;
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j+1]) {
					Collections.swap(list, j, j+1);
					count++;
					tag = false;
				}
				System.out.print("{ ");
				for (Integer n : list) {
					System.out.print(n + " ");
				}
				System.out.print(" }\n");
			}
			if (tag)
				break;
			i++;
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			bw.write("Array is sorted in " + count + " swaps.\n");
			bw.write("First Element: " + String.valueOf(list.get(0)));
			bw.write("\nLast Element: " + list.get(a.length - 1));
			bw.flush();
			bw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] a = new int[n];

		String[] aItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int aItem = Integer.parseInt(aItems[i]);
			a[i] = aItem;
		}

		countSwaps(a);

		scanner.close();
	}
}

```
