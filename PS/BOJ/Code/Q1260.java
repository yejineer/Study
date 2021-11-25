package BFS_DFS;
/*
    [문제] DFS와 BFS
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
    [날짜] 2021-11-25
 */
import java.io.*;
import java.util.*;

public class Q1260 {
    static StringBuilder sb = new StringBuilder();
    static int N, M, V;
    static ArrayList<Integer>[] adj;    // 그래프 정보를 담는 ArrayList 배열
    static boolean[] visit;             // 방문여부 확인을 위한 배열

    // x 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x) {
        visit[x] = true;
        sb.append(x).append(' ');

        for (int y: adj[x]){
            if (visit[y]) continue;
            dfs(y);
        }
    }

    // start 에서 시작해서 갈 수 있는 정점들을 모두 탐색하기
    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while (!que.isEmpty()) {
            int x = que.poll();
            sb.append(x).append(' ');

            for (int y: adj[x]){
                if (visit[y]) continue;
                que.add(y);
                visit[y] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        V = Integer.valueOf(st.nextToken());

        adj = new ArrayList[N+1];
        for (int i = 1;i <= N; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken());
            int y = Integer.valueOf(st.nextToken());
            adj[x].add(y);
            adj[y].add(x);
        }

        // 방문할 수 있는 정점이 여러 개인 경우, 정점 번호가 작은 것을 먼저 방문하기 위해 정렬
        for (int i = 1; i<=N; i++)
            Collections.sort(adj[i]);
        
        // 방문여부 확인을 위한 배열(visit) 초기화 후 dfs진행
        visit = new boolean[N + 1];
        dfs(V);
        sb.append('\n');
        
        // bfs를 위해 배열(visit) 초기화 후 bfs진행
        for (int i = 1; i <= N; i++)
            visit[i] = false;
        bfs(V);

        // 결과 출력
        System.out.println(sb);
    }

}
