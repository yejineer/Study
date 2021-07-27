

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class S5_3052 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> set = new HashSet<Integer>();
		
		for (int i=0; i<10; i++) {
			set.add(Integer.valueOf(br.readLine()) % 42);
		}
		System.out.println(set.size());
	}

}
