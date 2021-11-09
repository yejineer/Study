package BinarySearch;
/*
    [문제] 나무 자르기
    [분류] 이분 탐색
    [날짜] 2021-11-09
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        int[] trees = new int[n];
        st = new StringTokenizer(br.readLine());
        int maxLength = 0;
        for (int i=0; i<n; i++) {
            trees[i] = Integer.valueOf(st.nextToken());
            maxLength = Math.max(maxLength, trees[i]);
        }

        int left = 0, right = maxLength;
        int answer = 0;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            long sum = 0;
            for (int i=0; i<n; i++) {
                if (trees[i] > mid) {
                    sum += trees[i] - mid;
                }
            }
            if (sum < m) {
                right = mid - 1;
            } else {
                answer = Math.max(answer, mid);
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
