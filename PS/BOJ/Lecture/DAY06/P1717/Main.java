package DAY06.P1717;
/*
    [문제] A - 집합의 표현
    [분류] 그래프 1
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.valueOf(st.nextToken());
        m = Integer.valueOf(st.nextToken());

        // 초기화
        group = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            group[i] = i;
        }

        int command, a, b;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            command = Integer.valueOf(st.nextToken());
            a = Integer.valueOf(st.nextToken());
            b = Integer.valueOf(st.nextToken());
            // command == 0 -> 합집합
           if (command == 0) {
               union(a, b);
           } else {
               if (find(a) == find(b))
                   bw.write("YES" + "\n");
               else
                   bw.write("NO" + "\n");
           }
        }

        bw.flush();
        bw.close();
    }

    private static void union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);
        group[aGroup] = bGroup;
    }

    private static int find(int i) {
        if (group[i] == i)
            return i;
        else
            return group[i] = find(group[i]);
    }
}
