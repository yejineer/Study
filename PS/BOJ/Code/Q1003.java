package DynamicProgramming;
/*
    [문제] 피보나치 함수
    [분류] 다이나믹 프로그래밍
    [날짜] 2021-11-17
 */
import java.io.*;

public class Q1003 {
    static Integer[][] dp = new Integer[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(br.readLine());

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.valueOf(br.readLine());
            fibonacci(N);
            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append('\n');
        }
        System.out.print(sb);
    }

    static Integer[] fibonacci(int N) {
        if (dp[N][0] == null || dp[N][1] == null) {
            dp[N][0] = fibonacci(N-1)[0] + fibonacci(N-2)[0];
            dp[N][1] = fibonacci(N-1)[1] + fibonacci(N-2)[1];
        }
        return dp[N];
    }
}
