package DAY09.P7579;
/*
    [문제] A - 앱
    [분류] 동적계획법 2
    [알고리즘] Knapsack
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        int[] Memory = new int[N + 1];
        int[] Cost = new int[N + 1];
        int totalCost = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Memory[i] = Integer.valueOf(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Cost[i] = Integer.valueOf(st.nextToken());
            totalCost += Cost[i];
        }

        // DP[i][j] = i 번 앱까지 중, j 만큼의 비용을 사용하여 만들수 있는 가장 많은 메모리
        int[][] DP = new int[N + 1][totalCost + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= totalCost; j++) {
                DP[i][j] = DP[i - 1][j];
                if (j >= Cost[i]) {
                    DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - Cost[i]] + Memory[i]);
                }
            }
        }

        int answer = totalCost;
        for (int i = 1; i <= totalCost; i++) {
            if (DP[N][i] >= M) {
                answer = i;
                break;
            }
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
