package DataStructure;
/*
    [문제] 최대 힙
    [분류] 자료 구조, 우선순위 큐
    [날짜] 2021-12-05
 */
import java.io.*;
import java.util.PriorityQueue;
import java.util.Collections;

public class Q11279 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.valueOf(br.readLine());
        for (int i=0; i<N; i++) {
            int x = Integer.valueOf(br.readLine());
            if (x == 0) {
                if (!q.isEmpty())
                    sb.append(q.poll()).append('\n');
                else
                    sb.append(0).append('\n');
            } else {
                q.add(x);
            }
        }
        System.out.print(sb);
    }
}
