package Sorting;
/*
    [문제]
    수 정렬하기 3

    [분류]
    정렬
*/
import java.io.*;

public class Q10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.valueOf(br.readLine());
        int[] count = new int[10001];

        for (int i=0; i<N; i++) {
            count[Integer.valueOf(br.readLine())]++;
        }

        for (int i=0; i<10001; i++) {
            while (count[i] > 0) {
                sb.append(i).append('\n');
                count[i]--;
            }
        }
        System.out.print(sb);
    }
}
