package Hash;
/*
    Hashing
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15829 {
    static final int n = 31;
    static final int M = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.valueOf(br.readLine());
        String s = br.readLine();

        long pow = 1;
        long answer = 0;

        for (int i=0; i<L; i++) {
            answer += (s.charAt(i)-'a'+1) * pow;
            pow = (pow * n) % M;    // answer로 하면 long 범위 안에 안 들어와서 틀림
        }
        System.out.println(answer % M); // answer로 하면 long 범위 안에 안 들어와서 틀림
    }

}
