package BFS_DFS;
/*
    [문제] A → B
    [분류] 그래프 이론, 그리디 알고리즘, 그래프 탐색, 너비 우선 탐색
    [날짜] 2021-12-21
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16953 {
    static long A, B;
    static long cnt = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.valueOf(st.nextToken());
        B = Integer.valueOf(st.nextToken());

        dfs(A, 1);

        if (cnt == Long.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(cnt);
    }

    static void dfs(long num, long level) {
        if (num == B) {
            cnt = Math.min(cnt, level);
            return;
        }
        if (num > B) return;

        dfs(num * 10 + 1, level+1);
        dfs(num * 2, level+1);
    }
}
