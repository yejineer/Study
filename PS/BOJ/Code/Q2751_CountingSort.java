/*
    [문제]
    숫자 정렬하기 2
    
    [분류]
    정렬
 */
import java.io.*;

public class Q2751_CountingSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(br.readLine());
        boolean[] exist = new boolean[2000001];

        for (int i=0; i<N; i++) {
            exist[Integer.valueOf(br.readLine()) + 1000000] = true;
        }
        for (int i=0; i<exist.length; i++) {
            if (exist[i]) sb.append(i-1000000).append('\n');
        }
        System.out.print(sb);
    }
}
