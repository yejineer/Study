package Math;
/*
    [문제] 이진수
    [분류] 수학, 구현
    [날짜] 2021-12-29
 */
import java.io.*;

public class Q3460 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = Integer.valueOf(br.readLine());
            int idx = 0;

            while (n > 0) {
                if (n % 2 == 1) {
                    sb.append(idx).append(' ');
                }
                n /= 2;
                idx++;
            }
        }

        System.out.println(sb);
    }
}
