package DivideConquer;
/*
    [문제] Z
    [분류] 분할 정복, 재귀
    [날짜] 2021-12-14
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1074 {
    static int N, r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.valueOf(st.nextToken());
        r = Integer.valueOf(st.nextToken());
        c = Integer.valueOf(st.nextToken());

        int size = (int)Math.pow(2, N);
        System.out.println(recursive(0, 0, size));
    }

    static int recursive(int newR, int newC, int size) {
        if (size == 1) {
            return 0;
        }

        if (r < newR+size/2 && c < newC+size/2)
            return recursive(newR, newC, size/2); // 위-왼쪽
        else if (r < newR+size/2 && c < newC+size)
            return recursive(newR, newC+size/2, size/2) + (int)Math.pow(size/2, 2); // 위-오른쪽
        else if (r < newR+size && c < newC+size/2)
            return recursive(newR+size/2, newC, size/2) + (int)Math.pow(size/2, 2) * 2; // 아래-왼쪽
        else
            return recursive(newR+size/2, newC+size/2, size/2) + (int)Math.pow(size/2, 2) * 3; //아래-오른쪽
    }
}
