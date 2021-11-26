package DynamicProgramming;
/*
    [문제] 2 × n 타일링
    [분류] 다이나믹 프로그래밍
    [날짜] 2021-11-26
 */
import java.io.*;

public class Q11726 {
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        System.out.println(rec_func(N));
    }

    static int rec_func(int n) {
        if (dp[n] > 0) return dp[n];
        if(n == 0 || n == 1) return 1;
        else {
            dp[n] = rec_func(n-1) + rec_func(n-2);
            return dp[n] %= 10007;
        }
    }
}
