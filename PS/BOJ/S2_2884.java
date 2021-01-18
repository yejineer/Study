import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S2_2884 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		
		StringTokenizer st = new StringTokenizer(s);
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		
		if (minute < 45) {
			hour -= 1;
			if (hour == -1)
				hour = 23;
			minute = minute + 60 - 45;
		} else {
			minute -= 45;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(hour + " " + minute);
		bw.flush();
		bw.close();
	}

}
