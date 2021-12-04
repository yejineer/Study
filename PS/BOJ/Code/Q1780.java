package DivideConquer;
/*
    [문제] 종이의 개수
    [분류] 분할 정복, 재귀
    [날짜] 2021-12-04
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1780 {
    static int[][] arr;
    static int[] count = {0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        arr = new int[N][N];

        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                arr[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        partition(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<count.length; i++) {
            sb.append(count[i]).append('\n');
        }
        System.out.print(sb);
    }

    static void partition(int row, int col, int size) {
        if (check(row, col, size)) {
            count[arr[row][col]+1]++;
            return;
        }

        size /= 3;

        partition(row, col, size);
        partition(row, col + size, size);
        partition(row, col + 2 * size, size);

        partition(row + size, col, size);
        partition(row + size, col + size, size);
        partition(row + size, col + 2 * size, size);

        partition(row + 2 * size, col, size);
        partition(row + 2 * size, col + size, size);
        partition(row + 2 * size, col + 2 * size, size);
    }

    static boolean check(int row, int col, int size) {
        int color = arr[row][col];
        for (int i=row; i<row+size; i++) {
            for (int j=col; j<col+size; j++)
                if (arr[i][j] != color)
                    return false;
        }
        return true;
    }
}
