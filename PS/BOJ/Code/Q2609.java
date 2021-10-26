/*
    [문제]
    최대공약수와 최소공배수

    [분류]
    수학, 정수론, 유클리드 호제법
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int a = Integer.valueOf(st.nextToken());
        int b = Integer.valueOf(st.nextToken());
        int d = gcd(a, b);
        sb.append(d).append('\n');
        sb.append(a * b / d).append('\n');
        System.out.print(sb);
    }

    public static int gcd(int a, int b) {
        int r;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

}
