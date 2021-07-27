import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S7_2675 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		int i = 0;
//		while (i < T) {
//			String[] arr = br.readLine().split(" ");
//			int R = Integer.parseInt(arr[0]);
//			String S = arr[1];
//			
//			int j = 0;
//			while (j < S.length()) {
//				int k = 0;
//				while (k < R) {
//					bw.write(S.charAt(j));
//					k++;
//				}
//				j++;
//			}
//			bw.write("\n");
//			i++;
//		}
//		
//		bw.flush();
//		bw.close();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < T; i++) {
			String[] arr = br.readLine().split(" ");
			int R = Integer.parseInt(arr[0]);
			String S = arr[1];
			
			for (int j = 0; j < S.length(); j++) {
				for (int k = 0; k < R; k++) {
					sb.append(S.charAt(j));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

}
