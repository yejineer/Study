package BFS_DFS;
/*
    [문제] 적록색약
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색
    [날짜] 2021-12-20
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Q10026 {
    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count1 = 0;
    static int count2 = 0;
    static Queue<Pos> q = new LinkedList<>();

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            char[] str = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = str[j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(new Pos(i, j));
                    count1++;
                }
            }
        }

        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'R') {
                    map[i][j] = 'G';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(new Pos(i, j));
                    count2++;
                }
            }
        }

        System.out.println(count1 + " " + count2);
    }

    static void bfs(Pos pos) {
        char color = map[pos.x][pos.y];
        visited[pos.x][pos.y] = true;
        q.add(pos);

        while (!q.isEmpty()) {
            Pos t = q.remove();
            int x1 = t.x;
            int y1 = t.y;

            for (int i = 0; i < 4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];

                if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < n) {
                    if (map[x2][y2] == color && !visited[x2][y2]) {
                        q.add(new Pos(x2, y2));
                        visited[x2][y2] = true;
                    }
                }
            }
        }
    }
}

