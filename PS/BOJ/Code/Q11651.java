/*
    [문제]
    좌표 정렬하기 2

    [분류]
    정렬
 */
import java.io.*;
import java.util.*;

public class Q11651 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.valueOf(st.nextToken());
            arr[i][1] = Integer.valueOf(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1])
                return o1[0] - o2[0];
            else
                return o1[1] - o2[1];
        });

        Arrays.stream(arr).forEach(o-> sb.append(o[0] + " ").append(o[1]).append('\n'));
        System.out.print(sb);
    }
}
