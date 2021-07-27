import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S6_1065 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(getCount(n));
	}
	
	public static int getCount(int n) {
		int cnt = 0;
		
		if (n < 100) {
			return n;
		} else {
			cnt = 99;
			if (n == 1000)
				n = 999;
			
			int i = 100;
			while (i<=n) {
				int one = i%10;
				int ten = i/10%10;
				int hundred = (i/10/10)%10;
				if (hundred-ten == ten-one)
					cnt++;
				i++;
			}
		}
		return cnt;
	}

}
