/*
    [문제] 통계학
    [분류] 구현, 정렬
    [날짜] 2021-11-01
    [참고] https://st-lab.tistory.com/108
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(br.readLine());
        int[] arr = new int[8001];
        int sum = 0;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int median = 0;
        int mode = 0;

        for (int i=0; i<N; i++) {
            int num = Integer.valueOf(br.readLine());
            arr[num+4000]++;
            sum += num;
            if (num < min) min = num;
            if (num > max) max = num;
        }

        int count = 0;
        int maxMode = 0;
        int modeIdx = 0;
        for (int i=min+4000; i<=max+4000; i++) {
            if (arr[i] > 0) {
                if (count < (N+1)/2) {
                    count += arr[i];
                    median = i-4000;
                }
                if (maxMode < arr[i]) {
                    maxMode = arr[i];
                    mode = i-4000;
                    modeIdx = 1;
                } else if (maxMode == arr[i] && modeIdx == 1) {
                    mode = i-4000;
                    modeIdx = 0;
                }
            }
        }

        sb.append(Math.round((double)sum / N)).append('\n');
        sb.append(median).append('\n');
        sb.append(mode).append('\n');
        sb.append(max-min).append('\n');
        System.out.print(sb);
    }
}
