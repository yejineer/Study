package DynamicProgramming;
/*
    [문제] 계단 오르기
    [분류] Dynamic Programming
    [날짜] 2021-11-10
 */
import java.io.*;

public class Q2579_TopDown {
    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        stairs = new int[n+1];
        dp = new int[n+1];
        for (int i=1; i<=n; i++) {
            stairs[i] = Integer.valueOf(br.readLine());
        }

        dp[0] = stairs[0];
        dp[1] = stairs[1];
        if (n >= 2) {
            dp[2] = stairs[1] + stairs[2];
        }
        System.out.println(rec_func(n));
    }

    public static int rec_func(int n) {
        if (n >= 3 && dp[n] == 0) {
            dp[n] = Math.max(rec_func(n-2), rec_func(n-3) + stairs[n-1]) + stairs[n];
        }
        return dp[n];
    }
}
