package Backtracking;
/*
    [문제] N과 M(8)
    [분류] 백트래킹
    [날짜] 2022-01-13
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q15657 {
    static int N, M;
    static int[] arr, selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        arr = new int[N];
        selected = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }
        Arrays.sort(arr);

        solution(0);

        System.out.print(sb);
    }

    static void solution(int k) {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                sb.append(arr[selected[i]]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int smallest = k == 0 ? 0 : selected[k - 1];
        for (int item = smallest; item <= N-1; item++) {
            selected[k] = item;
            solution(k + 1);
            selected[k] = 0;
        }
    }

}
