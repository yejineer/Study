package DAY01.P1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L, C;
    static char[] data;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY01/P1759/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.valueOf(st.nextToken());
        C = Integer.valueOf(st.nextToken());
        data = new char[C];
        visit = new boolean[C];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            data[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(data);

        dfs(0, 0, 0, 0);

        System.out.print(sb);
    }

    static void dfs(int start, int k, int consonant, int vowel) {
        if (k == L && consonant >= 2 && vowel >= 1) {   // 2. 목적지인가?
            for (int i = 0; i < C; i++) {
                if (visit[i]) {
                    sb.append(data[i]);
                }
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i < C; i++) {   // 3. 연결된 곳 순회
            visit[i] = true;
            boolean isVowel = checkVowel(data[i]);
            dfs(i + 1, k + 1, consonant + (!isVowel ? 1 : 0), vowel + (isVowel ? 1 : 0));
            visit[i] = false;
        }
    }

    static boolean checkVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        else
            return false;
    }
}
