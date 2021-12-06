package BFS_DFS;
/*
    [문제] 숨바꼭질
    [분류] 그래프 이론, 그래프 탐색, 너비 우선 탐색
    [날짜] 2021-12-06
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1697 {
    static int[] visitCount = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());

        if (N != K)
            bfs(N, K);

        System.out.println(visitCount[K]);
    }

    static void bfs(int N, int K) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        while (!q.isEmpty()) {
            int pos = q.poll();
            if (pos == K) break;

            if (pos-1 >= 0 && visitCount[pos-1] == 0) {
                q.offer(pos-1);
                visitCount[pos-1] = visitCount[pos] + 1;
            }
            if (pos+1 <= 100000 && visitCount[pos+1] == 0) {
                q.offer(pos+1);
                visitCount[pos+1] = visitCount[pos] + 1;
            }
            if (2*pos <= 100000 && visitCount[2*pos] == 0) {
                q.offer(2*pos);
                visitCount[2*pos] = visitCount[pos] + 1;
            }
        }
    }
}
