package DynamicProgramming;
/*
    [문제] 동전 1
    [분류] 다이나믹 프로그래밍
    [날짜] 2021-12-27
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());
        int[] dp = new int[k + 1];
        dp[0] = 1;

        while (n-- > 0) {
            int coin = Integer.valueOf(br.readLine());
            for (int i = 1; i < k + 1; i++) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        System.out.println(dp[k]);

    }
}
