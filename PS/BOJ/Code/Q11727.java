package DynamicProgramming;
/*
    [문제] 2 × n 타일링 2
    [분류] 다이나믹 프로그래밍
    [날짜] 2021-11-29
*/

import java.io.*;

public class Q11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] dp = new int[1001];
        dp[0] = 0;
        dp[1] = 1;

        if (n > 1) {
            dp[2] = 3;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
            }
        }
        System.out.println(dp[n]);
    }

}
