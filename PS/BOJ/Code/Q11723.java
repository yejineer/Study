package BitMasking;
/*
    [문제] 집합
    [분류] 구현, 비트 마스킹
    [날짜] 2021-11-14
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int bitSet = 0;

        int m = Integer.valueOf(br.readLine());
        StringTokenizer st;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x;
            switch (st.nextToken()) {
                case "add":
                    x = Integer.valueOf(st.nextToken());
                    bitSet |= (1 << (x-1));
                    break;
                case "remove":
                    x = Integer.valueOf(st.nextToken());
                    bitSet = bitSet & ~(1 << (x-1));
                    break;
                case "check":
                    x = Integer.valueOf(st.nextToken());
                    sb.append((bitSet & (1 <<(x-1))) != 0 ? 1 : 0).append('\n');
                    break;
                case "toggle":
                    x = Integer.valueOf(st.nextToken());
                    bitSet ^= (1 << (x-1));
                    break;
                case "all":
                    bitSet |= (~0);
                    break;
                case "empty":
                    bitSet &= 0;
                    break;
            }
        }
        System.out.println(sb);
    }
}
