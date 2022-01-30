package DAY07.P11657;
/*
    [문제] B - 타임머신
    [분류] 그래프 2
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static class Info {
        int from;
        int to;
        int time;

        public Info(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    static int N, M;
    static Info[] AdjList;
    static long[] Time;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Time = new long[N + 1];
        AdjList = new Info[M + 1];
        for (int i = 1; i <= N; i++) {
            Time[i] = INF;
        }

        int A, B, C;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            AdjList[i] = new Info(A, B, C);
        }

        findShortestPath(1);

        boolean isNegativeCycle = findNegativeCycle();
        if (isNegativeCycle == true) {
            bw.write("-1" + "\n");
            bw.flush();
            bw.close();
            return;
        }

        for (int i = 2; i <= N; i++) {
            if (Time[i] == INF) {
                bw.write("-1" + "\n");
            } else {
                bw.write(Time[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void findShortestPath(int start) {
        Time[start] = 0;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 1; j <= M; j++) {
                Info nowEdge = AdjList[j];
                if (Time[nowEdge.from] != INF) {
                    if (Time[nowEdge.to] > Time[nowEdge.from] + nowEdge.time) {
                        Time[nowEdge.to] = Time[nowEdge.from] + nowEdge.time;
                    }
                }
            }
        }
    }

    private static boolean findNegativeCycle() {
        for (int j = 1; j <= M; j++) {
            Info nowEdge = AdjList[j];
            if (Time[nowEdge.from] != INF) {
                if (Time[nowEdge.to] > Time[nowEdge.from] + nowEdge.time) {
                    return true;
                }
            }
        }
        return false;
    }
}
