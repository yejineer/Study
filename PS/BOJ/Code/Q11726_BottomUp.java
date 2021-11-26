package DynamicProgramming;
/*
    [문제] 2 × n 타일링
    [분류] 다이나믹 프로그래밍
    [날짜] 2021-11-26
 */
import java.io.*;

public class Q11726_BottomUp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        if (n == 1) {
            System.out.println(dp[1]);
        } else {
            dp[2] = 2;  // ArrayIndexOutOfBounds 에러 해결하기 위해 dp[2]값은 여기서 초기화.
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }
            System.out.println(dp[n]);
        }
    }

}
