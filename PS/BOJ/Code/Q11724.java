package BFS_DFS;
/*
    [문제] 연결 요소의 개수
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
    [날짜] 2021-11-27
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11724 {
    static ArrayList<Integer>[] adj;
    static boolean[] visit;
    static int N, M;
    static int count = 0;

    public static void main(String[] args) throws IOException {
       init();

       for (int i=1; i<=N; i++) {
           if (!visit[i]) {
               dfs(i);
               count++;
           }
       }

       System.out.println(count);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        adj = new ArrayList[N+1];
        visit = new boolean[N+1];

        for (int i=1; i<=N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.valueOf(st.nextToken());
            int v = Integer.valueOf(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
    }

    static void dfs(int node) {
        visit[node] = true;
        for (int adjNode : adj[node]) {
            if (!visit[adjNode]) {
                dfs(adjNode);
            }
        }
    }
}
