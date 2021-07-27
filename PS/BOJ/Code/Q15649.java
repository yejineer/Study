package BruteForce.Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    [문제]
    N과 M (1)

    [설명]
    자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

    [풀이]
    순열
 */
public class Q15649 {
    static int N, M;
    static int[] selected;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        selected = new int[M];
        visited = new boolean[N+1];
        rec_func(0);
        System.out.println(sb);
    }

    public static void rec_func(int toPick) {
        if (toPick == M) {
            for (int i=0; i<M; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }
        for (int i=1; i<= N; i++) {
            if (!visited[i]) {
                selected[toPick] = i;
                visited[i] = true;
                rec_func(toPick + 1);
                visited[i] = false;
            }
        }
    }

}
