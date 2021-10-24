/*
    [문제]
    영화감독 숌 (https://www.acmicpc.net/problem/1436)

    [분류]
    BruteForce
 */
import java.util.Scanner;

public class Q1436 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n == 1) System.out.println(666);
        else {
            int num = 666;
            int count = 1;
            String s;
            while (true) {
                num++;
                s = String.valueOf(num);
                if (s.contains("666")) count++;
                if (count == n) break;
            }
            System.out.println(num);
        }
    }

}
