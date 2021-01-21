import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class S5_8958 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int i = 0;
		do {
			char arr[] = br.readLine().toCharArray();
			int pre = arr[0] == 'O'? 1 : 0;
			int sum = pre;
			int idx = 1;
			
			while (idx < arr.length) {
				if (arr[idx] == 'O') {
					pre++;
					sum += pre;
				} else {
					pre = 0;
				}
				idx++;
			}
			bw.write(sum + "\n");
		} while (++i < n);
		bw.flush();
		bw.close();
	}

}
