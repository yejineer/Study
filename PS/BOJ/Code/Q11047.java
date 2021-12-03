package Greedy;
/*
    [문제] 동전 0
    [분류] 그리디 알고리즘
    [날짜] 2021-12-03
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());

        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.valueOf(br.readLine());
        }

        int count = 0;
        for (int i=N-1; i>=0; i--) {
            if (K >= arr[i]) {
                count += K / arr[i];
                K %= arr[i];
            }
        }
        System.out.println(count);
    }
}
