package Backtracking;
/*
    [문제] N과 M(9)
    [분류] 백트래킹
    [날짜] 2022-01-14
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Q15663 {
    static int N, M;
    static int[] arr, selected;
    static boolean[] visited;
    static LinkedHashSet<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        arr = new int[N];
        selected = new int[M];
        visited = new boolean[N];
        set = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.valueOf(st.nextToken());
        }
        Arrays.sort(arr);

        solution(0);

        set.forEach(System.out::println);
    }

    static void solution(int k) {
        if (k == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < M; i++) {
                sb.append(arr[selected[i]]).append(' ');
            }
            set.add(sb.toString());
            return;
        }
        
        for (int idx = 0; idx < N; idx++) {
            if (visited[idx]) continue;
            visited[idx] = true;
            selected[k] = idx;
            solution(k + 1);
            visited[idx] = false;
            selected[k] = 0;
        }
    }

}
