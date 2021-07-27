import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_2588 {

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n1 = Integer.parseInt(bf.readLine());
		int n2 = Integer.parseInt(bf.readLine());
		
		int[] arr = new int[String.valueOf(n2).length()];
		int idx = 0;
		int result = 0;
		do {
			int factor = n2 % 10;
			arr[idx] = n1 * factor;
			System.out.println(arr[idx]);
			result += arr[idx] * (int)Math.pow(10, idx++);
			n2 /= 10;
		} while (n2 != 0); 
		System.out.println(result);
	}

}
