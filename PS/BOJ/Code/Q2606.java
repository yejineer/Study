package BFS_DFS;
/*
    [문제] 바이러스
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
    [날짜] 2021-11-18
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2606 {
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        adj = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i=1; i<=n; i++)
            adj[i] = new ArrayList<>();

        StringTokenizer st;
        int adjCount = Integer.valueOf(br.readLine());
        for (int i=0; i<adjCount; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1);
        System.out.println(count);
    }

    static void dfs(int idx) {
        visited[idx] = true;

        for (int adjIdx : adj[idx]) {
            if (!visited[adjIdx]) {
                count++;
                dfs(adjIdx);
            }
        }
    }
}
