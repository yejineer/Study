package DataStructure;
/*
    [문제] 큐
    [분류] 자료 구조, 큐
    [날짜] 2021-11-04
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q10845 {
    static int[] queue;
    static int front = 0, rear = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.valueOf(br.readLine());
        queue = new int[n];

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
                case "front":
                    sb.append(front()).append('\n');
                    break;
                case "back":
                    sb.append(back()).append('\n');
                    break;
            }
        }
        System.out.print(sb);
    }

    public static void push(int x) {
        queue[++rear] = x;
    }

    public static int pop() {
        if (rear - front + 1 == 0)
            return -1;
        else {
            return queue[front++];
        }
    }

    public static int size() {
        return rear - front + 1;
    }

    public static int empty() {
        return rear - front + 1 == 0 ? 1 : 0;
    }

    public static int front() {
        if (rear - front + 1 == 0)
            return -1;
        else
            return queue[front];
    }

    public static int back() {
        if (rear - front + 1 == 0)
            return -1;
        else
            return queue[rear];
    }
}
