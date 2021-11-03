package DataStructure;
/*
    [문제] 제로
    [분류] 구현, 자료 구조, 스택
    [날짜] 2021-11-03
 */
import java.io.*;

public class Q10773 {
    public static void main(String[] arsg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.valueOf(br.readLine());
        int[] stack = new int[k];
        int idx = -1;   // 스택의 현재 인덱스
        for (int i=0; i<k; i++) {
            int n = Integer.valueOf(br.readLine());
            if (n == 0) {
                stack[idx--] = 0;
            } else {
                stack[++idx] = n;
            }
        }

        int sum = 0;
        for (int i=0; i<=idx; i++) {
            sum += stack[i];
        }
        System.out.println(sum);
    }
}
