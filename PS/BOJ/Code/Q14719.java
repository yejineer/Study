package Implementation;
/*
    [문제] 빗물
    [분류] 구현, 시뮬레이션
    [날짜] 2022-01-10
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.valueOf(st.nextToken());
        int W = Integer.valueOf(st.nextToken());
        int[] block = new int[W];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            block[i] = Integer.valueOf(st.nextToken());
        }

        int rainWater = 0;
        int now, left, right;
        for (int i = 1; i < W - 1; i++) {
            now = block[i];
            left = block[i];
            right = block[i];

            for (int j = 0; j < i; j++) {
                if (block[j] > now) {
                    left = Math.max(block[j], left);
                }
            }
            for (int j = i + 1; j < W; j++) {
                if (block[j] > now) {
                    right = Math.max(block[j], right);
                }
            }

            rainWater += Math.min(left, right) - now;
        }

        System.out.println(rainWater);
    }
}
