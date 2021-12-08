package BFS_DFS;
/*
    [문제] 토마토
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색
    [날짜] 2021-12-08
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q7569 {
    private static class Position {
        int row;
        int col;
        int height;

        public Position(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }

    static int M, N, H;
    static int[][][] tomato;
    static int[] rowDiff = {-1, 1, 0, 0, 0, 0};
    static int[] colDiff = {0, 0, -1, 1, 0, 0};
    static int[] heightDiff = {0, 0, 0, 0, -1, 1};
    static Queue<Position> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.valueOf(st.nextToken());
        N = Integer.valueOf(st.nextToken());
        H = Integer.valueOf(st.nextToken());
        tomato = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.valueOf(st.nextToken());
                    if (tomato[i][j][k] == 1)
                        queue.offer(new Position(i, j, k));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        while (!queue.isEmpty()) {
            Position pre = queue.poll();

            for (int i = 0; i < 6; i++) {
                int newHeight = pre.height + heightDiff[i];
                int newRow = pre.row + rowDiff[i];
                int newCol = pre.col + colDiff[i];
                if (isValid(newHeight, newRow, newCol)) {
                    queue.add(new Position(newHeight, newRow, newCol));
                    tomato[newHeight][newRow][newCol] = tomato[pre.height][pre.row][pre.col] + 1;
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomato[i][j][k] == 0) {
                        return -1;
                    }
                    result = Math.max(result, tomato[i][j][k]);
                }
            }
        }
        return result-1;
    }

    static boolean isValid(int height, int row, int col) {
        if (height >= 0 && height < H && row >= 0 && row < N && col >= 0 && col < M) {
            if (tomato[height][row][col] == 0)
                return true;
        }
        return false;
    }

}
