package DataStructure;
/*
    [문제] 덱
    [분류] 자료 구조, 덱
    [날짜] 2021-11-05
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q10866_Array {
    static int arrLength = 10000;
    static int[] deque = new int[arrLength];
    static int front = 0, rear = 0;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.valueOf(br.readLine());

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push_front":
                    push_front(Integer.valueOf(st.nextToken()));
                    break;
                case "push_back":
                    push_back(Integer.valueOf(st.nextToken()));
                    break;
                case "pop_front":
                    sb.append(pop_front()).append('\n');
                    break;
                case "pop_back":
                    sb.append(pop_back()).append('\n');
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

    static void push_front(int x) {
        deque[front] = x;
        front = (front-1 + arrLength) % arrLength;
        size++;
    }

    static void push_back(int x) {
        rear = (rear + 1) % arrLength;
        deque[rear] = x;
        size++;
    }

    static int pop_front() {
        if (size == 0) return -1;
        int num = deque[(front+1) % arrLength];
        front = (front + 1) % arrLength;
        size--;
        return num;
    }

    static int pop_back() {
        if (size == 0) return -1;
        int num = deque[rear];
        rear = (rear-1 + arrLength) % arrLength;
        size--;
        return num;
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

    static int front() {
        if (size == 0) return -1;
        return deque[(front+1) % arrLength];
    }

    static int back() {
        if (size == 0) return -1;
        return deque[rear];
    }
}
