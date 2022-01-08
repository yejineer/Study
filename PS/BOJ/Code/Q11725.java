package BFS_DFS;
/*
    [문제] 트리의 부모 찾기
    [분류] 그래프 이론, 그래프 탐색, 트리, 너비 우선 탐색, 깊이 우선 탐색
    [날짜] 2022-01-08
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11725 {
    static int N;
    static int[] parents;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine());
        list = new ArrayList[N + 1];
        parents = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }


        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < parents.length; i++) {
            sb.append(parents[i]).append('\n');
        }
        System.out.print(sb);

    }

    static void bfs() {
        boolean[] visit = new boolean[N + 1];
        parents = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            int num = q.poll();
            for (int i : list[num]) {
                if (!visit[i]) {
                    visit[i] = true;
                    parents[i] = num;
                    q.add(i);
                }
            }
        }

    }
}
