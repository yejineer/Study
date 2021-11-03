package DataStructure;
/*
    [문제] 괄호
    [분류] 자료 구조, 문자열, 스택
    [날짜] 2021-11-03
 */
import java.io.*;

public class Q9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.valueOf(br.readLine());
        for (int i=0; i<T; i++) {
            sb.append(checkVPS(br.readLine())).append('\n');
        }
        System.out.print(sb);
    }

    public static String checkVPS(String s) {
        char[] stack = new char[s.length()];
        int idx = -1;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack[++idx] = c;
            } else {
                if (idx < 0 || stack[idx] != '(') {
                    return "NO";
                } else {
                    stack[idx--] = '\0';
                }
            }
        }
        if (stack[0] == '(')
            return "NO";
        else
            return "YES";
    }
}
