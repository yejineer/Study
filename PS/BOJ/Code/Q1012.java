package BFS_DFS;

import java.util.*;

public class Q1012 {
    static int[][] map;
    static boolean[][] ck;
    static Queue<int[]> que;
    static int N, M, K;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    static void bfs(int x, int y) {
        que.add(new int[]{x, y});

        while (!que.isEmpty()) {
            x = que.peek()[0];
            y = que.peek()[1];
            que.remove();
            ck[x][y] = true;

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                if (xx < 0 || xx >= N || yy < 0 || yy >= M)
                    continue;
                if (map[xx][yy] == 0 || ck[xx][yy])
                    continue;
                que.add(new int[]{xx, yy});
                ck[xx][yy] = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][M];
            ck = new boolean[N][M];

            for (int k = 0; k < K; k++) {
                int X = sc.nextInt();
                int Y = sc.nextInt();
                map[X][Y] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0 || ck[i][j])
                        continue;
                    que = new LinkedList<int[]>();
                    bfs(i, j);
                    cnt++;
                }
            }
            System.out.println(cnt);
        }

    }
}