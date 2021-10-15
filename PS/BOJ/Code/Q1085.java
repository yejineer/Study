package Etc.Math;

/*
    직사각형에서 탈출
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1085 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.valueOf(st.nextToken());
        int y = Integer.valueOf(st.nextToken());
        int w = Integer.valueOf(st.nextToken());
        int h = Integer.valueOf(st.nextToken());

        int min = Integer.MAX_VALUE;
        min = Math.min(min, Math.min(x-0, y-0));
        min = Math.min(min, Math.min(w-x, h-y));
        System.out.println(min);

    }
}
