/*
 *   단지번호 붙이기
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Q2667_bfs {
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
                    bfs(i, j);
                    apartList.add(apartCount);
                }
            }
        }
	print();
    }

    private static void bfs(int x, int y) {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[]{x, y});
        visit[x][y] = true;
        apartCount++;

        while (!que.isEmpty()) {
            int curX = que.peek()[0];
            int curY = que.peek()[1];
            que.poll();
            for (int i=0; i<4; i++) {
                int dx = curX + diff[i][0];
                int dy = curY + diff[i][1];
                if (dx < 0 || dy < 0 || dx >= n || dy >= n) continue;
                if (!visit[dx][dy] && map[dx][dy] == 1) {
                    que.offer(new int[]{dx, dy});
                    visit[dx][dy] = true;
                    apartCount++;
                }
            }
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(apartList.size()).append('\n');
        Collections.sort(apartList);    // 역순일 경우, Collections.sort(apartList, Collections.reverseOrder());
        for (int n : apartList) {
            sb.append(n).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
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
