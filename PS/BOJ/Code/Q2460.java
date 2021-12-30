package Math;
/*
    [문제] 지능형 기차 2
    [분류] 수학, 구현, 사칙연산
    [날짜] 2021-12-30
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2460 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] station = new int[11];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= 10; i++) {
            st = new StringTokenizer(br.readLine());
            int getOff = Integer.valueOf(st.nextToken());
            int getOn = Integer.valueOf(st.nextToken());
            station[i] = station[i-1] - getOff + getOn;
            max = Math.max(max, station[i]);
        }

        System.out.println(max);
    }
}
