package DataStructure;
/*
    [문제] 요세푸스 문제 0
    [분류] 구현, 자료 구조, 큐
    [날짜] 2021-11-05
    11:34 -
 */
import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Q11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int k = Integer.valueOf(st.nextToken());

        Queue<Integer> que = new LinkedList<>();
        for (int i=1; i<=n; i++) {
            que.offer(i);
        }

        StringBuilder sb = new StringBuilder("<");
        while (que.size() > 1) {
            for (int i=0; i<k-1; i++) {
                que.offer(que.poll());
            }
            sb.append(que.poll()).append(", ");
        }
        sb.append(que.poll()).append(">");
        System.out.println(sb);
    }
}
