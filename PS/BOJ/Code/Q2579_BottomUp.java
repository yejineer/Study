package DynamicProgramming;
/*
    [문제] 계단 오르기
    [분류] Dynamic Programming
    [날짜] 2021-11-10
 */
import java.io.*;

public class Q2579_BottomUp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        int[] stairs = new int[n+1];
        int[] dp = new int[n+1];
        for (int i=1; i<=n; i++) {
            stairs[i] = Integer.valueOf(br.readLine());
        }

        dp[1] = stairs[1];
        if (n >= 2) {
            dp[2] = stairs[1] + stairs[2];
        }

        for (int i=3; i<=n; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i-1]) + stairs[i];
        }
        System.out.println(dp[n]);
    }

}
