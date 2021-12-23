package Implementation;
/*
    [문제] 쉽게 푸는 문제
    [분류] 수학, 구현
    [날짜] 2021-12-23
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        int A = Integer.valueOf(st.nextToken());
        int B = Integer.valueOf(st.nextToken());

        int count = 1;
        for (int i=0; i<B; i++) {
            for (int j=0; j<count; j++) {
                list.add(count);
            }
            count++;
        }

        int sum = 0;
        for (int i=A; i<=B; i++) {
            sum += list.get(i-1);
        }
        System.out.println(sum);
    }
}
