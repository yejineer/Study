/*
    [문제]
    덩치

    [분류]
    구현, BruteForce
 */
import java.io.*;
import java.util.*;

public class Q7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.valueOf(br.readLine());
        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.valueOf(st.nextToken());
            arr[i][1] = Integer.valueOf(st.nextToken());
        }

        for (int i=0; i<N; i++) {
            int rank = 1;
            for (int j=0; j<N; j++) {
                if (i == j) continue;
                if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) rank++;
            }
            sb.append(rank).append(' ');
        }
        System.out.println(sb);
    }

}
