package Greedy;
/*
    [문제] 회의실 배정
    [분류] 그리디 알고리즘, 정렬
    [날짜] 2021-12-02
 */
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        int[][] time = new int[N][2];

        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i][0] = Integer.valueOf(st.nextToken());
            time[i][1] = Integer.valueOf(st.nextToken());
        }

        Arrays.sort(time, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int count = 0;
        int prevEndTime = 0;
        for (int i=0; i<N; i++) {
            if (prevEndTime <= time[i][0]) {
                prevEndTime= time[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}
