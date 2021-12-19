package Graph;
/*
    [문제] 플로이드
    [분류] 그래프 이론, 플로이드-와샬
    [날짜] 2021-12-19
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q11404 {

    public static int N;
    public static int[][] dist;
    public static final int INF = 1000000000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];

        for(int i=1; i <= N; i++) {
            for(int j=1; j <= N; j++) {
                if(i == j) continue;
                dist[i][j] = INF;
                dist[j][i] = INF;
            }
        }

        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end], time);
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i <= N; i++) {
            for(int j=1; j <= N; j++) {
                if(dist[i][j] >= INF) sb.append("0 ");
                else sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i=1; i <= N; i++) {
                for (int j=1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }
    }
    
}