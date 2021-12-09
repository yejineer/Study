package BFS_DFS;
/*
    [문제] 미로 탐색
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색
    [날짜] 2021-12-09
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M;
    static int[][] matrix;
    static int[][] visitCount;
    static int[] diffRow = {-1, 1, 0, 0};
    static int[] diffCol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        matrix = new int[N][M];
        visitCount = new int[N][M];

        for (int i=0; i<N; i++) {
            String s = br.readLine();
            for (int j=0; j<M; j++) {
                matrix[i][j] = s.charAt(j) - '0';   // 구분자가 없어서 StringTokenizer 못 씀
            }
        }

        bfs(0, 0);

        System.out.println(visitCount[N-1][M-1]);
    }

    private static void bfs(int startRow, int startCol) {
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(startRow, startCol));
        visitCount[startRow][startCol] = 1;

        while (!q.isEmpty()) {
            Position pos = q.poll();
            for (int i=0; i<4; i++) {
                int newRow = pos.row + diffRow[i];
                int newCol = pos.col + diffCol[i];
                if (isValid(newRow, newCol)) {
                    q.offer(new Position(newRow, newCol));
                    visitCount[newRow][newCol] = visitCount[pos.row][pos.col] + 1;
                }
            }
        }
    }

    private static boolean isValid(int newRow, int newCol) {
        if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M) {
            if (visitCount[newRow][newCol] == 0 && matrix[newRow][newCol] == 1)
                return true;
        }
        return false;
    }
}
