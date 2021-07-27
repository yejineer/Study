package BruteForce.Solution;

import java.io.*;
import java.util.StringTokenizer;

/*
    [문제]
    N과 M (3)

    [설명]
    자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    - 1부터 N까지 자연수 중에서 M개를 고른 수열
    - 같은 수를 여러 번 골라도 된다.

    [풀이]
    중복 순열
 */
public class Q15651 {

    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[M];
        rec_func(0);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.close();
//        System.out.println(sb.toString());
    }

    public static void rec_func(int k) {
        if (k == M) {
            for (int i=0; i<M; i++)
                sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }
        for (int item=1; item<=N; item++) {
            selected[k] = item;
            rec_func(k+1);
            selected[k] = 0;
        }
    }

}
