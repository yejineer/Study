package BFS_DFS;
/*
    [문제] 토마토
    [분류]
    [날짜] 2021-12-07   11:27~
 */
import java.io.*;
import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7576 {
    static int[][] tomato;
    static int[] xDiff = {-1, 1, 0, 0};
    static int[] yDiff = {0, 0, -1, 1};
    static int day = 0;
    static Queue<Position> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.valueOf(st.nextToken());
        int N = Integer.valueOf(st.nextToken());
        tomato = new int[M][N];

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int tomatoInfo = Integer.valueOf(st.nextToken());
                tomato[i][j] = tomatoInfo;
                if (tomatoInfo == 1) {
                    queue.offer(new Position(i, j));
                }
            }
        }

        bfs(M, N);

        int day = Integer.MIN_VALUE;
        for (int i=0; i<M; i++) {
            for (int j=0; j<N; j++) {
                if (tomato[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                day = Math.max(day, tomato[i][j]);
            }
        }
        if (day == 1) {
            System.out.println(0);
        } else {
            System.out.println(day-1);
        }
    }


    static void bfs(int M, int N) {
        while (!queue.isEmpty()) {
            Position pre = queue.poll();

            for (int i=0; i<xDiff.length; i++) {
                int nowX = pre.x + xDiff[i];
                int nowY = pre.y + yDiff[i];
                if (0 <= nowX && nowX < N && 0 <= nowY && nowY < M) {
                    if (tomato[nowX][nowY] == 0) {
                        tomato[nowX][nowY] = tomato[pre.x][pre.y] + 1;
                        queue.add(new Position(nowX, nowY));
                    }
                }
            }
        }
    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
