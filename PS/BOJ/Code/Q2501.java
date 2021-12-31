package Math;
/*
    [문제] 약수 구하기
    [분류] 수학, 브루트포스 알고리즘
    [날짜] 2021-12-31
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q2501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int K = Integer.valueOf(st.nextToken());

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                list.add(i);
            }
        }

        if (list.size() >= K)
            System.out.println(list.get(K - 1));
        else
            System.out.println(0);
    }
}
