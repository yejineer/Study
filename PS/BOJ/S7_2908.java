import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S7_2908 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		String s1 = st.nextToken();
		String s2 = st.nextToken();
		
		int n1 = Integer.parseInt(s1);
		int n2 = Integer.parseInt(s2);
		String max = "";
		for (int i = 0; i < s1.length(); i++) {
			if (n1 % 10 > n2 % 10) {
				max = s1;
				break;
			} else if (n1 % 10 < n2 % 10) {
				max = s2;
				break;
			} else {
				n1 /= 10;
				n2 /= 10;
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = max.length()-1; i >= 0; i--) {
			bw.write(max.charAt(i));
		}
		bw.flush();
		bw.close();
	}

}
