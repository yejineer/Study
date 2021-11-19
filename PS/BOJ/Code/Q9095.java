package DynamicProgramming;
/*
    [문제] 1, 2, 3 더하기
    [분류] 다이나믹 프로그래밍
    [날짜] 2021-11-19
 */
import java.io.*;

public class Q9095 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i=4; i<=10; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }

        int T = Integer.valueOf(br.readLine());
        while (T-- > 0) {
            int n = Integer.valueOf(br.readLine());
            sb.append(dp[n]).append('\n');
        }
        System.out.print(sb);
    }
}
