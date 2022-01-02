package DynamicProgramming;
/*
    [문제] 가장 긴 증가하는 부분 수열
    [분류] 다이나믹 프로그래밍
    [날짜] 2022-01-02
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] dp = new int[N];
        int[] arr = new int[N];

        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        Arrays.sort(dp);

        System.out.println(dp[N - 1]);
    }
}
