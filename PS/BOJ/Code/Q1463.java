package DynamicProgramming;
/*
    [문제] 1로 만들기
    [분류] DynamicProgramming
    [날짜] 2021-11-09
 */
import java.io.*;

public class Q1463 {
    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        memo = new int[n+1];
        System.out.println(rec_func(n));
    }

    public static int rec_func(int x) {
        if (x == 0 || x == 1)
            return 0;
        if (memo[x] == 0) {
            if (x % 6 == 0) {
                memo[x] = Math.min(rec_func(x - 1), Math.min(rec_func(x / 3), rec_func(x / 2))) + 1;
            } else if (x % 3 == 0) {
                memo[x] = Math.min(rec_func(x / 3), rec_func(x - 1)) + 1;
            } else if (x % 2 == 0) {
                memo[x] = Math.min(rec_func(x / 2), rec_func(x - 1)) + 1;
            } else {
                memo[x] = rec_func(x - 1) + 1;
            }
        }
        return memo[x];
    }
}
