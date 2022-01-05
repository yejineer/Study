package DynamicProgramming;
/*
    [문제] 정수 삼각형
    [분류] 다이나믹 프로그래밍
    [날짜] 2022-01-05
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1932 {
    static int n;
    static int[][] arr;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.valueOf(br.readLine());
        arr = new int[n][n];
        dp = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < i + 1; j++) {
                arr[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = arr[n - 1][i];
        }

        System.out.println(recursive(0, 0));
    }

    static int recursive(int depth, int idx) {
        if (depth == n - 1)
            return dp[depth][idx];

        if (dp[depth][idx] == null) {
            dp[depth][idx] = Math.max(recursive(depth + 1, idx), recursive(depth + 1, idx + 1)) + arr[depth][idx];
        }

        return dp[depth][idx];
    }
}
