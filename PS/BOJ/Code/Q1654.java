package BinarySearch;
/*
    [문제] 랜선 자르기
    [분류] 이분 탐색, 매개 변수 탐색
    [날짜] 2021-11-08
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.valueOf(st.nextToken());
        int n = Integer.valueOf(st.nextToken());
        int[] arr = new int[k];
        int maxLength = Integer.MIN_VALUE;  // 이미 갖고 있는 랜선들 중 가장 긴 랜선의 길이 (right값으로 사용할 예정)

        for (int i=0; i<k; i++) {
            arr[i] = Integer.valueOf(br.readLine());
            maxLength = Math.max(maxLength, arr[i]);
        }

        long left = 1, right = maxLength;
        long answer = 0;     // N개를 만들 수 있는 랜선의 최대 길이
        while (left <= right) {
            long count = 0;
            long mid = left + (right-left)/2;
            for (int i=0; i<k; i++) {
                count += arr[i] / mid;
            }

            if (count < n) {
                right = mid - 1;
            } else {
                answer = Math.max(answer, mid);
                left = mid + 1;     // (count >= n)을 만족해도 가능한 랜선의 최대 길이를 찾아야하니까
            }
        }
        
        System.out.println(answer);
    }
}
