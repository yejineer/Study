package Etc.Math;

/*
    부녀회장이 될테야
 */
import java.io.*;
public class Q2775 {

    public static void main(String[] args) throws IOException {
        int[][] apt = new int[15][15];
        int len = 14;
        for (int i=1; i<=len; i++) {
            apt[0][i] = i;
            apt[i][1] = 1;
        }

        for (int i=1; i<=len; i++) {
            for (int j=2; j<=len; j++) {
                apt[i][j] = apt[i][j-1] + apt[i-1][j];
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.valueOf(br.readLine());
        for (int tc=0; tc<T; tc++) {
            int k = Integer.valueOf(br.readLine());
            int n = Integer.valueOf(br.readLine());
            bw.write(apt[k][n] + "\n");
        }
        bw.flush();
        bw.close();
    }

}
