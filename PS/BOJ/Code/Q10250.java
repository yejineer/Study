package Etc.Math;
/*
    ACM 호텔
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q10250 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.valueOf(br.readLine());
        int H, W, N;
        for (int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.valueOf(st.nextToken());
            W = Integer.valueOf(st.nextToken());
            N = Integer.valueOf(st.nextToken());
            int answer;
            if (N % H == 0) {
                answer = H * 100 + N/H;
            } else {
                answer = N % H * 100 + (N/H + 1);
            }
            bw.write(answer + "\n");
        }
        bw.close();
    }
}
