/*
    [문제] 카드2
    [분류] 자료 구조, 큐
    [날짜] 2021-11-01
 */
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Q2164 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> que = new LinkedList<>();
        int N = Integer.valueOf(br.readLine());
        if (N == 1 || N == 2) {
            System.out.println(N);
            return;
        }
        for (int i=1; i<=N; i++) {
            que.offer(i);
        }
        while (que.size() > 2) {
            que.remove();
            que.offer(que.poll());
        }
        que.remove();
        System.out.println(que.poll());
    }
}
