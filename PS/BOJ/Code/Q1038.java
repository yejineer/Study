package Backtracking;
/*
    [문제] 감소하는 수
    [분류] 브루트포스 알고리즘, 백트래킹
    [날짜] 2022-01-11
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q1038 {
    static List<Long> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        list = new ArrayList<>();

        if (N <= 10) {
            System.out.println(N);
        } else if (N > 1022) {
            System.out.println("-1");
        } else {
            for (int i = 0; i < 10; i++) {
                solution(i, 1);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }
    }

    static void solution(long num, int idx) {
        if (idx > 10) return;

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            solution((num * 10) + i, idx + 1);
        }
    }
}
