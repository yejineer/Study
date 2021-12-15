package DivideConquer;
/*
    [문제] 쿼드트리
    [분류] 분할 정복, 재귀
    [날짜] 2021-12-15
 */
import java.io.*;

public class Q1992 {
    static int[][] arr;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        arr = new int[N][N];

        for (int i=0; i<N; i++) {
            String str = br.readLine();
            for (int j=0; j<N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        partition(0, 0, N);
        System.out.println(sb);
    }

    private static void partition(int r, int c, int size) {
        if (checkSameNum(r, c, size)) {
            sb.append(arr[r][c]);
            return;
        }

        sb.append("(");

        size /= 2;
        partition(r, c, size);
        partition(r, c+size, size);
        partition(r+size, c, size);
        partition(r+size, c+size, size);

        sb.append(")");
    }

    private static boolean checkSameNum(int r, int c, int size) {
        int num = arr[r][c];
        for (int i=r; i<r+size; i++) {
            for (int j=c; j<c+size; j++) {
                if (arr[i][j] != num)
                    return false;
            }
        }
        return true;
    }
}
