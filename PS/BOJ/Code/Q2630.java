package DivideConquer;
/*
    [문제] 색종이 만들기
    [분류] 분할 정복, 재귀
    [날짜] 2021-12-01
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q2630 {
    static int[][] arr;
    static int whiteCount = 0, blueCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        arr = new int[N][N];

        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        partition(0, 0, N);

        StringBuilder sb = new StringBuilder();
        sb.append(whiteCount).append('\n');
        sb.append(blueCount).append('\n');
        System.out.print(sb);
    }

    public static void partition(int row, int col, int size) {
        if (check(row, col, size)) {
            if (arr[row][col] == 0)
                whiteCount++;
            else
                blueCount++;
            return;
        }

        size /= 2;
        
        partition(row, col, size);
        partition(row, col+size, size);
        partition(row+size, col, size);
        partition(row+size, col+size, size);
    }

    static boolean check(int row, int col, int size) {
        int color = arr[row][col];

        for (int i=row; i<row+size; i++) {
            for (int j=col; j<col+size; j++) {
                if (arr[i][j] != color)
                    return false;
            }
        }
        return true;
    }
}
