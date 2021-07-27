import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S7_11720 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		int i = 0;
		int sum = 0;
		while (i < n) {
			sum += s.charAt(i++) - '0';
		}
	
		System.out.println(sum);
	}

}

// byte[] »ç¿ë
//public class S7_11720 {
//
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine();
//
//		int sum = 0;
//		for (byte val : br.readLine().getBytes()) {
//			sum += val - '0';
//		}
//		System.out.println(sum);
//	}
//
//}