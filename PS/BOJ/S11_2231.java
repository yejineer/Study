import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S11_2231 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		System.out.println(decomposition(Integer.valueOf(str), str.length()));
	}
	
	public static int decomposition(int n, int len) {
		int sum;
		int i = n - len * 9;
		while (i < n) {
			sum = i;
			int j = i;
			while (j != 0) {
				sum += j % 10;
				j /= 10;
			}
			if (sum == n)
				return i;
			i++;
		}
		return 0;
	}

}
