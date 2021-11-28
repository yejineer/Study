package Math;
/*
    [문제] 잃어버린 괄호
    [분류] 수학, 문자열, 그리디 알고리즘, 파싱, 계산 이론
    [날짜] 2021-11-28
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 뺄셈으로 분리
        StringTokenizer minus = new StringTokenizer(br.readLine(), "-");
        int totalSum = Integer.MAX_VALUE;

        while (minus.hasMoreTokens()) {
            // 덧셈으로 분리
            StringTokenizer plus = new StringTokenizer(minus.nextToken(), "+");
            int sum = 0;
            while (plus.hasMoreTokens()) {
                sum += Integer.valueOf(plus.nextToken());
            }

            if (totalSum == Integer.MAX_VALUE) {
                totalSum = sum;
            } else {
                totalSum -= sum;
            }
        }

        System.out.println(totalSum);
    }
}
