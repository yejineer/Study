package DataStructure;
/*
    [문제] 스택 수열
    [분류] 자료 구조, 스택
    [날짜] 2021-11-06
 */

import java.io.*;
import java.util.Stack;

public class Q1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());

        int idx = 0;
        for (int i=0; i<n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > idx) {
                for(int j = idx + 1; j <= num; j++) {
                    stack.push(j);
                    sb.append('+').append('\n');
                }
                idx = num;
            }
            else if(stack.peek() != num) {
                System.out.println("NO");
                return;
            }
            stack.pop();
            sb.append('-').append('\n');
        }
        System.out.print(sb);
    }
}
