package DataStructure;
/*
    [문제] AC
    [분류] 구현, 자료 구조, 문자열, 파싱, 덱
    [날짜] 2021-12-16
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Q5430 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        ArrayDeque<Integer> deque;
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            deque = new ArrayDeque<>();
            for(int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }
            AC(deque, p);
        }

        System.out.print(sb);
    }

    public static void AC(ArrayDeque<Integer> deque, String p) {
        boolean isRight = true;
        for (char cmd : p.toCharArray()) {
            if(cmd == 'R') {
                isRight = !isRight;
                continue;
            }

            if(isRight) {
                if(deque.pollFirst() == null) {
                    sb.append("error\n");
                    return;
                }
            } else {
                if(deque.pollLast() == null) {
                    sb.append("error\n");
                    return;
                }
            }
        }

        makeStringResult(deque, isRight);
    }

    public static void makeStringResult(ArrayDeque<Integer> deque, boolean isRight) {
        sb.append('[');
        if (deque.size() > 0) {
            if (isRight) {	// 정방향일경우
                sb.append(deque.pollFirst());
                while(!deque.isEmpty()) {
                    sb.append(',').append(deque.pollFirst());
                }
            } else {	// 역방향일경우
                sb.append(deque.pollLast());
                while(!deque.isEmpty()) {
                    sb.append(',').append(deque.pollLast());
                }
            }
        }
        sb.append(']').append('\n');
    }
}