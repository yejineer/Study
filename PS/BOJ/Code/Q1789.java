package Greedy;
/*
    [문제] 수들의 합
    [분류] 수학, 그리디 알고리즘
    [날짜] 2021-12-17
 */
import java.io.*;

public class Q1789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        int i = 0;
        long sum = 0;

        while (true) {
            sum += i;
            if (sum > S)
                break;
            i++;
        }
        System.out.println(--i);
    }

}
