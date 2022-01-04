package Math;
/*
    [문제] 곱셈
    [분류] 수학, 분할 정복을 이용한 거듭제곱
    [날짜] 2022-01-04
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1629 {
    public static long C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B));
    }

    public static long pow(long A, long exponent) {
        if (exponent == 1) {
            return A % C;
        }

        long temp = pow(A, exponent / 2);
        if (exponent % 2 == 1) {
            return (temp * temp % C) * A % C;
        }

        return temp * temp % C;
    }
}
