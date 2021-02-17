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
