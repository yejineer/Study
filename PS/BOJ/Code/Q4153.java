package Etc.Math;

import java.io.*;
import java.util.StringTokenizer;

/*
    직각삼각형
 */
public class Q4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.valueOf(st.nextToken());
            int y = Integer.valueOf(st.nextToken());
            int z = Integer.valueOf(st.nextToken());
            if (x == 0 && y == 0 && z == 0)
                break;

            if (Math.pow(z, 2) == Math.pow(x, 2) + Math.pow(y, 2)) bw.write("right\n");
            else if (Math.pow(y, 2) == Math.pow(x, 2) + Math.pow(z, 2)) bw.write("right\n");
            else if (Math.pow(x, 2) == Math.pow(y, 2) + Math.pow(z, 2)) bw.write("right\n");
            else bw.write("wrong\n");
        }
        bw.close();
    }
}
