package DynamicProgramming;
/*
    [문제] 파도반 수열
    [분류] 수학, 다이나믹 프로그래밍
    [날짜] 2021-11-20
 */
import java.io.*;

public class Q9461 {
    static long[] dp = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        int T = Integer.valueOf(br.readLine());
        while (T-- > 0) {
            int N = Integer.valueOf(br.readLine());
            sb.append(rec_func(N)).append('\n');
        }
        System.out.print(sb);
    }

    static long rec_func(int n) {
        if (dp[n] == 0) {
            dp[n] = rec_func(n-2) + rec_func(n-3);
        }
        return dp[n];
    }
}
