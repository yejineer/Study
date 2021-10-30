package BinarySearch;
/*
    [문제]
    수 찾기

    [분류]
    이분 탐색
 */
import java.io.*;
import java.util.*;

public class Q1920 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i=0; i<N; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }
        Arrays.sort(arr);

        int M = Integer.valueOf(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            if (binarySearch(Integer.valueOf(st.nextToken())) == -1) {
                sb.append(0).append('\n');
            } else {
                sb.append(1).append('\n');
            }
        }
        System.out.print(sb);
    }

    public static int binarySearch(int key) {
        int start = 0;
        int end = arr.length-1;
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
