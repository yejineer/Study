package BitMasking;
/*
    [문제] 막대기
    [분류] 수학, 비트마스킹
    [날짜] 2021-11-15
 */
import java.io.*;

public class Q1094 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        int x = Integer.valueOf(br.readLine());

        for (int i=0; i<7; i++) {
            if ((x & (1<<i)) > 0)
                count++;
        }
        System.out.println(count);
    }
}
