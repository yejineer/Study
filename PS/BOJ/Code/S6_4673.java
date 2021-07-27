import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S6_4673 {

	public static void main(String[] args) throws IOException {
		
		boolean[] isSelfNum = new boolean[10001];
		Arrays.fill(isSelfNum, true);	// boolean�� �迭�� false�� �ʱ�ȭ �ǹǷ� true�� �ʱ�ȭ�ϴ� �۾�
		
		System.out.println(Integer.valueOf('A'));
//		int i = 1;
//		while (i < 10000)	{
//			int dn = d(i++);
//			if (dn <= 10000) {
//				isSelfNum[dn] = false;
//			}
//		}
//		
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		i = 1;
//		while (i <= 10000) {
//			if (isSelfNum[i] == true) {
//				bw.write(i + "\n");
//			}
//			i++;
//		}
//		bw.flush();
//		bw.close();
	}
	
	public static int d(int n) {
		int dn = n;
		while (n != 0) {
			dn += n % 10;
			n /= 10;
		}
		return dn;
	}

}
