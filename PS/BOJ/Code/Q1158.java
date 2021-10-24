/*
    [문제]
    요세푸스 순열 https://www.acmicpc.net/problem/1158

    [분류]
    자료 구조, 큐
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1158_Queue {

    static StringBuilder sb = new StringBuilder();

    public static void solution(int n, int k) {
        sb.append("<");
        func(n, k);
        sb.append(">");
        System.out.println(sb);
    }

    public static void func(int n, int k) {
        Queue<Integer> que = new LinkedList<>();
        for (int i=1; i<=n; i++) {
            que.offer(i);
        }
        while (que.size() != 1) {
            for (int i=0; i<k-1; i++) {
                que.offer(que.poll());
            }
            sb.append(que.poll()).append(", ");
        }
        sb.append(que.poll());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.valueOf(arr[0]);
        int K = Integer.valueOf(arr[1]);

        solution(N, K);
    }
}
