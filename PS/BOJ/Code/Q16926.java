package Implementation;
/*
    [문제] 배열 돌리기 1
    [분류] 구현
    [날짜] 2021-12-10
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q16926 {
    static int N, M, R;
    static int[][] arr;
    static int[] diffX = {0, 1, 0, -1}; // 우, 하, 좌, 우 
    static int[] diffY = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        M = Integer.valueOf(st.nextToken());
        R = Integer.valueOf(st.nextToken());
        arr = new int[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                arr[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        int cycle = Math.min(N, M) / 2;
        while (R-- > 0) {
            rotate(cycle);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void rotate(int cycle) {
        for (int i=0; i<cycle; i++) {
            int x = i;
            int y = i;
            int diffIdx = 0;
            int temp = arr[x][y];

            while (diffIdx < 4) {
                int newX = x + diffX[diffIdx];
                int newY = y + diffY[diffIdx];
                if (newX >= i && newX < N-i && newY >= i && newY < M-i) {
                    arr[x][y] = arr[newX][newY];
                    x = newX;
                    y = newY;
                } else {
                    diffIdx++;
                }
            }
            arr[i+1][i] = temp;
        }
    }
}
