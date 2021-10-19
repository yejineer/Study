/*
    체스판 다시 칠하기
    1. 체스판 경우의 수 : (M-7) * (M-7)
    2. 경우의 수만큼 다시 칠해야 하는 정사각형의 최소 개수 구하기
      2-1. 흑/백일 경우 나눠서 생각
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1018 {

    static boolean[][] isWhite;
    static int minCount = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        isWhite = new boolean[N][M];

        for (int i=0; i<N; i++) {
            String s = br.readLine();
            for (int j=0; j<M; j++) {
                if (s.charAt(j) == 'W')
                    isWhite[i][j] = true;
            }
        }

        for (int i=0; i<N-7; i++) {
            for (int j=0; j<M-7; j++) {
                find(i, j);
            }
        }

        System.out.println(minCount);
    }

    public static void find(int rStart, int cStart) {
        int rEnd = rStart + 8;
        int cEnd = cStart + 8;
        boolean tf = isWhite[rStart][cStart];
        int count = 0;

        for (int i=rStart; i<rEnd; i++) {
            for (int j=cStart; j<cEnd; j++) {
                if (isWhite[i][j] != tf)
                    count++;
                tf = !tf;
            }
            tf = !tf;
        }

        count = Math.min(count, 64-count);
        minCount = Math.min(minCount, count);
    }

}
