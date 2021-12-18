package Backtracking;
/*
    [문제] 연산자 끼워넣기
    [분류] 브루트포스 알고리즘, 백트래킹
    [날짜] 2021-12-18
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q14888 {
    static int N;
    static int[] number;
    static int[] operator = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        number = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        rec_func(number[0], 1);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n');
        sb.append(min).append('\n');
        System.out.print(sb);
    }

    public static void rec_func(int num, int idx) {
        if (idx == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < operator.length; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                switch (i) {
                    case 0:
                        rec_func(num + number[idx], idx + 1);
                        break;
                    case 1:
                        rec_func(num - number[idx], idx + 1);
                        break;
                    case 2:
                        rec_func(num * number[idx], idx + 1);
                        break;
                    case 3:
                        rec_func(num / number[idx], idx + 1);
                        break;
                }
                operator[i]++;
            }
        }
    }

}