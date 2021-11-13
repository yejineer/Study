package Math;
/*
    [문제] 소수 구하기
    [분류] 수학, 정수론, 소수 판정, 에라토스테네스의 체
    [날짜] 2021-11-13
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.valueOf(st.nextToken());
        int n = Integer.valueOf(st.nextToken());
        boolean[] visit = new boolean[n+1];

        visit[0] = true;
        visit[1] = true;
        for (int i=2; i<=Math.sqrt(n); i++) {
            if (visit[i]) continue;
            for (int j=i*i; j<=n; j+=i) {
                visit[j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=m; i<=n; i++) {
            if (!visit[i])
                sb.append(i).append('\n');
        }
        System.out.print(sb);
    }
}
