package DataStructure;
/*
    [문제] 절댓값 힙
    [분류] 자료 구조, 우선순위 큐
    [날짜] 2021-12-12
 */
import java.io.*;
import java.util.PriorityQueue;

public class Q11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->{
            if(Math.abs(a) == Math.abs(b)) {
                return a > b ? 1 : -1;
            }
            return Math.abs(a) - Math.abs(b);
        });

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            int x = Integer.valueOf(br.readLine());
            if (x == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append('\n');
                } else {
                    sb.append(pq.poll()).append('\n');
                }
            } else {
                pq.offer(x);
            }
        }
        System.out.println(sb);
    }
}
