package Math;
/*
    [문제] 팩토리얼 0의 개수
    [분류] 수학
    [날짜] 2021-11-23
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Q1676 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        // (2 * 5)의 개수만큼. 사실상 5의 개수로 판단됨.
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        System.out.println(count);
    }

}