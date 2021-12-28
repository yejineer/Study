package BruteForce;
/*
    [문제] 일곱 난쟁이
    [분류] 브루트포스 알고리즘, 정렬
    [날짜] 2021-12-28
 */

import java.io.*;
import java.util.Arrays;

public class Q2309 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.valueOf(br.readLine());
        }

        boolean isFinished = false;
        int sum = Arrays.stream(arr).sum();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                int temp = sum - arr[i] - arr[j];
                if (temp == 100) {
                    arr[i] = 1000;
                    arr[j] = 1000;
                    isFinished = true;
                    break;
                }
            }
            if (isFinished) break;
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        Arrays.stream(arr).filter(s -> s < 1000).forEach(s -> sb.append(s).append('\n'));
        System.out.print(sb);
    }

}
