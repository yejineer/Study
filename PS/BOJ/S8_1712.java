import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S8_1712 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.valueOf(st.nextToken());
		int b = Integer.valueOf(st.nextToken());
		int c = Integer.valueOf(st.nextToken());
		
		if (c <= b) {
			System.out.println(-1);
		} else {
			System.out.println((a / (c - b)) + 1);
		}
	}
}
