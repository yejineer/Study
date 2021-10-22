/*
*   단지번호 붙이기
* */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Q2667 {
    static int apartCount, n;
    static boolean[][] visit;
    static int[][] map;
    static int[][] diff = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static List<Integer> apartList = new ArrayList<>();

    private static void solution(int[][] matrix) {
        map = matrix;
        visit = new boolean[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    apartCount = 0;
                    dfs(i, j);
                    apartList.add(apartCount);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(apartList.size()).append('\n');
        Collections.sort(apartList);
        for (int n : apartList) {
            sb.append(n).append('\n');
        }
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;
        apartCount++;
        for (int i=0; i<4; i++) {
            int dx = x + diff[i][0];
            int dy = y + diff[i][1];
            if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
            if (!visit[dx][dy] && map[dx][dy] == 1) {
                dfs(dx, dy);
            }
        }

    }

    public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        int[][] matrix = new int[n][n];
        for (int i=0; i<n; i++) {
            String s = br.readLine();
            for (int j=0; j<n; j++) {
                matrix[i][j] = s.charAt(j)-'0';
            }
        }
        solution(matrix);
    }
}
