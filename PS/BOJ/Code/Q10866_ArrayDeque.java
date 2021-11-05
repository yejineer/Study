package DataStructure;
/*
    [문제] 덱
    [분류] 자료 구조, 덱
    [날짜] 2021-11-05
 */
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q10866_ArrayDeque {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Deque<Integer> deque = new ArrayDeque<>();
        int n = Integer.valueOf(br.readLine());

        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "push_front":
                    deque.addFirst(Integer.valueOf(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.valueOf(Integer.valueOf(st.nextToken())));
                    break;
                case "pop_front":
                    if (deque.size() == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deque.pollFirst()).append('\n');
                    break;
                case "pop_back":
                    if (deque.size() == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deque.pollLast()).append('\n');
                    break;
                case "size":
                    sb.append(deque.size()).append('\n');
                    break;
                case "empty":
                    if (deque.size() == 0)
                        sb.append(1).append('\n');
                    else
                        sb.append(0).append('\n');
                    break;
                case "front":
                    if (deque.size() == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deque.peekFirst()).append('\n');
                    break;
                case "back":
                    if (deque.size() == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deque.peekLast()).append('\n');
                    break;
            }
        }
        System.out.print(sb);
    }

}
