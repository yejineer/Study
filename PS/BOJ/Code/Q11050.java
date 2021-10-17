/*
    이항 계수 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11050 {
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());

        memo = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            memo[i][0] = 1;
            memo[i][i] = 1;
        }

        for (int i=2; i<=n; i++) {
            for (int j=0; j<=i; j++) {
                if (i == j || j == 0) {
                    memo[i][j] = 1;
                } else {
                    memo[i][j] = memo[i-1][j-1] + memo[i-1][j];
                }
            }
        }
        System.out.println(memo[n][k]);

    }

}
