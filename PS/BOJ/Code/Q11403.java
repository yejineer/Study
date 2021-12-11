package Graph;
/*
    [문제] 경로 찾기
    [분류] 그래프 이론, 플로이드 와샬
    [날짜] 2021-12-11
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        int[][] adj = new int[N][N];

        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                adj[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        for (int k=0; k<N; k++) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (adj[i][k] == 1 && adj[k][j] == 1) {
                        adj[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                sb.append(adj[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
