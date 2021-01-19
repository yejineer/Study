

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S5_2577 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = Integer.valueOf(br.readLine()) * Integer.valueOf(br.readLine()) * Integer.valueOf(br.readLine());
		
		int[] arr = new int[10];
		do {
			arr[result % 10]++;
			result /= 10;
		} while (result != 0);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int i = 0; 
//		while (i != arr.length) {
//			bw.write(arr[i++] + "\n");
//		}
		for (int n: arr) {
			bw.write(n + "\n");
		}
		bw.flush();
		bw.close();
	}

}
