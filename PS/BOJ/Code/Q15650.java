package BruteForce.Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    [문제]
    N과 M (2)
    
    [설명]
    자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
    - 고른 수열은 오름차순이어야 한다.
    
    [풀이]
    조합
 */
public class Q15650 {
    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        selected = new int[M];
        rec_func(0);
        System.out.print(sb);
    }

    private static void rec_func(int toPick) {
        if (toPick == M) {
            for (int n : selected) sb.append(n).append(' ');
            sb.append('\n');
            return;
        }
        int smallest = toPick == 0 ? 1 : selected[toPick-1] + 1;
        for (int item=smallest; item <=N; item++) {
            selected[toPick] = item;
            rec_func(toPick + 1);
        }
    }
}
