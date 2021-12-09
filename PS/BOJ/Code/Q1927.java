package DataStructure;
/*
    [문제] 최소 힙
    [분류] 자료 구조, 우선순위 큐
    [날짜] 2021-12-09
 */
import java.io.*;
import java.util.PriorityQueue;

public class Q1927 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(br.readLine());

        for (int i=0; i<N; i++) {
            int x = Integer.valueOf(br.readLine());
            if (x == 0) {
                if (queue.isEmpty()) {
                    sb.append(0).append('\n');
                } else {
                    sb.append(queue.poll()).append('\n');
                }
            } else {
                queue.offer(x);
            }
        }
        System.out.print(sb);
    }
}
