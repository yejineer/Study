package PrefixSum;
/*
    [문제] 구간 합 구하기 4
    [분류] 누적 합
    [날짜] 2021-11-30
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        int[] prefixSum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        prefixSum[1] = Integer.valueOf(st.nextToken());
        for (int i=2; i<=N; i++) {
            prefixSum[i] = prefixSum[i-1] + Integer.valueOf(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.valueOf(st.nextToken());
            int end = Integer.valueOf(st.nextToken());
            int sum = start == 1 ? prefixSum[end] : prefixSum[end] - prefixSum[start-1];
                sb.append(sum).append('\n');
        }

        System.out.print(sb);
    }
}
