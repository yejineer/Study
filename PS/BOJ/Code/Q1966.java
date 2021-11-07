package DataStructure;
/*
    [문제] 프린터 큐
    [분류] 구현, 자료 구조, 시뮬레이션, 큐
    [날짜] 2021-11-07
 */
import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.valueOf(br.readLine());

        LinkedList<int[]> que;
        for (int i=0; i<T; i++) {
            que = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.valueOf(st.nextToken());
            int M = Integer.valueOf(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                que.offer(new int[]{j, Integer.valueOf(st.nextToken())});
            }

            int count = 0;
            while (!que.isEmpty()) {
                int[] page = que.poll();
                boolean maxValue = true;

                for (int j=0; j<que.size(); j++) {
                    if (que.get(j)[1] > page[1]) {
                        que.offer(page);
                        for (int k=0; k<j; k++) {
                            que.offer(que.poll());
                        }
                        maxValue = false;
                        break;
                    }
                }
                if (maxValue == false) continue;

                count++;
                if (page[0] == M) {
                    break;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb);


    }
}
