/*
    [문제] 균형잡힌 세상
    [분류] 자료 구조, 문자열, 스택
    [날짜] 2021-11-02
 */
import java.io.*;
import java.util.Stack;

public class Q4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;

        while (true) {
            s = br.readLine();
            if (s.equals(".")) break;
            sb.append(isBalanced(s)).append('\n');
        }
        System.out.print(sb);
    }

    public static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        char c;

        for (int i=0; i<s.length(); i++) {
            c = s.charAt(i);
            if (c == '(' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    return "no";
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                if (stack.empty() || stack.peek() != '[') {
                    return "no";
                } else {
                    stack.pop();
                }
            }
        }

        if (stack.empty())
            return "yes";
        else
            return "no";
    }
}
