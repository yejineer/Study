package DAY10.P2342;
/*
    [문제] G - Dance Dance Revolution
    [분류] 동적계획법2
 */
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] Direction = new int[100000];
    static int[][][] DP;
    static int[][] Power;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Direction = new int[100000];
        N = 0;
        st = new StringTokenizer(br.readLine(), " ");
        while (true) {
            int input = Integer.valueOf(st.nextToken());
            if (input == 0) {
                break;
            }
            Direction[++N] = input;
        }

        initPower();

        DP = new int[N + 1][5][5];
        for(int i = 1 ; i <= N ; i++) {
            for(int j = 0 ; j <= 4 ; j++) {
                for(int k = 0 ; k <= 4 ; k++) {
                    DP[i][j][k] = INF;
                }
            }
        }
        // 초기값
        DP[1][0][Direction[1]] = 2;
        DP[1][Direction[1]][0] = 2;

        int next;
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 0; j <= 4; j++) {
                for (int k = 0; k <= 4; k++) {
                    if (DP[i][j][k] != INF) { // 여기까지 밟는 방법이 있을 경우에만 계산한다.
                        next = Direction[i + 1];
                        if (k != next) { // 왼발로 밟기. 오른발로 확인. (같은 위치에 두 발이 모두 있으면 안 되기 때문에)
                            DP[i + 1][next][k] = Math.min(DP[i + 1][next][k], DP[i][j][k] + Power[j][next]);
                        }
                        if (j != next) { // 오른발로 밟기. 왼발로 확인. (같은 위치에 두 발이 모두 있으면 안 되기 때문에)
                            DP[i + 1][j][next] = Math.min(DP[i + 1][j][next], DP[i][j][k] + Power[k][next]);
                        }
                    }
                }
            }
        }

        int Answer = INF;
        for (int j = 0; j <= 4; j++) {
            for(int k = 0 ; k <= 4 ; k++) {
                Answer = Math.min(Answer, DP[N][j][k]);
            }
        }
        bw.write(Answer + "\n");
        bw.flush();
        bw.close();
    }

    static void initPower() {
        Power = new int[5][5];
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (i == j) {
                    if (i == 0) {
                        Power[i][j] = 0;
                    } else {
                        Power[i][j] = 1;
                    }
                } else if (i == 0 || j == 0) {
                    Power[i][j] = 2;
                } else if (Math.abs(i - j) == 2) {
                    Power[i][j] = 4;
                } else {
                    Power[i][j] = 3;
                }
            }
        }
    }
}
