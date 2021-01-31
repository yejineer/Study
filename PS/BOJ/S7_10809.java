import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class S7_10809 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int[] nums = new int[26];
		Arrays.fill(nums, -1);

		int i = 0;
		while (i < s.length()) {
			if (nums[s.charAt(i) - 97] == -1) {
				nums[s.charAt(i) - 97] = i;
			}
			i++;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		i = 0;
		while (i < nums.length) {
			bw.write(nums[i++] + " ");
		}
		bw.flush();
		bw.close();
	}

}
