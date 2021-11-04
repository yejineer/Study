package DataStructure;
/*
    [문제] 스택
    [분류] 자료 구조, 스택
    [날짜] 2021-11-04
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q10828 {
    static int[] stack;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.valueOf(br.readLine());
        stack = new int[n];

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "push":
                    push(Integer.valueOf(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append('\n');
                    break;
                case "size":
                    sb.append(size()).append('\n');
                    break;
                case "empty":
                    sb.append(empty()).append('\n');
                    break;
                case "top":
                    sb.append(top()).append('\n');
                    break;
            }
        }
        System.out.print(sb);
    }

    static void push(int x) {
        stack[size++] = x;
    }

    static int pop() {
        if (size == 0) {
            return -1;
        } else {
            int num = stack[size-1];
            stack[size-1] = 0;
            size--;
            return num;
        }
    }

    static int size() {
        return size;
    }

    static int empty() {
        if (size == 0)
            return 1;
        else
            return 0;
    }

    static int top() {
        if (size == 0)
            return -1;
        else
            return stack[size-1];
    }
}
