package BFS_DFS;
/*
    [문제] 숨바꼭질 2
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색
    [날짜] 2021-12-22
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q12851 {
    static int N, K;
    static int min = Integer.MAX_VALUE;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.valueOf(st.nextToken());
        K = Integer.valueOf(st.nextToken());

        if (N >= K) {
            sb.append(N-K).append('\n');
            sb.append(1).append('\n');
        } else {
            bfs();
            sb.append(min).append('\n');
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] visit = new int[100001];
        q.offer(N);
        visit[N] = 1;

        while (!q.isEmpty()) {
            int x = q.poll();
            if (min < visit[x]) return;

            int[] diff = new int[]{-1, 1, x};
            for (int i=0; i<3; i++) {
                int nx = x + diff[i];
                if (nx < 0 || nx > 100000) continue;

                if (nx == K) {
                    min = visit[x];
                    count++;
                }

                if (visit[nx] == 0 || visit[nx] == visit[x]+1) {
                    q.offer(nx);
                    visit[nx] = visit[x] + 1;
                }
            }
        }

    }

}
