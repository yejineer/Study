/*
    [문제]
    소수 찾기

    [분류]
    수학, 정수론, 소수 판정, 에라토스테네스의 체
 */
import java.io.*;
import java.util.*;

public class Q1978 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int count = 0;

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        while(st.hasMoreTokens()) {
            boolean isPrime = true;
            int n = Integer.parseInt(st.nextToken());
            if (n == 1) continue;

            for(int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) count++;
        }

        System.out.println(count);
    }

}
