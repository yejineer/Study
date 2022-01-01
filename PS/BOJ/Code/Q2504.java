package DataStructure;
/*
    [문제] 괄호의 값
    [분류] 구현, 자료 구조, 스택, 재귀
    [날짜] 2022-01-01
 */

import java.io.*;
import java.util.Stack;

public class Q2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(calc(str));

    }

    static int calc(String str) {
        int result = 0;
        int tmp = 1;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.add(c);
                tmp *= 2;
            } else if (c == '[') {
                stack.add(c);
                tmp *= 3;
            } else if (c == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    return 0;
                }
                if (str.charAt(i - 1) == '(')
                    result += tmp;
                stack.pop();
                tmp /= 2;
            } else if (c == ']') {
                if (stack.empty() || stack.peek() != '[') {
                    return 0;
                }
                if (str.charAt(i - 1) == '[')
                    result += tmp;
                stack.pop();
                tmp /= 3;
            }
        }

        if (!stack.isEmpty())
            result = 0;

        return result;
    }
}
