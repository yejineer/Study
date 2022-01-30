package DAY09.P11049;
/*
    [문제] F - 행렬 곱셈 순서
    [분류] 동적계획법1
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int MatrixR[] = new int[N + 1];
        int MatrixC[] = new int[N + 1];
        int D[][] = new int[N + 1 + 1][N + 1 + 1];
        final int INF = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            MatrixR[i] = Integer.parseInt(st.nextToken());
            MatrixC[i] = Integer.parseInt(st.nextToken());
        }

        // 점화식을 이용한 계산
        // D[i][j] = i 번 Matrix 에서 j 번 매트릭스까지 곱할때 최소 연산값
        for (int i = N - 1; i >= 1; i--) {
            for (int j = i + 1; j <= N; j++) {
                D[i][j] = INF;
                for (int k = i; k <= j; k++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k + 1][j] + MatrixR[i] * MatrixC[k] * MatrixC[j]);
                }
            }
        }

        bw.write(D[1][N] + "\n");
        bw.flush();
        bw.close();
    }
}
