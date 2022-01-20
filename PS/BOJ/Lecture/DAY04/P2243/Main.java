package DAY04.P2243;
/*
    [문제] 사탕상자
    [분류] 자료구조
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int S;
    static int MAX = 1000000;
    static int a, b, c;
    static int[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SDSLecture/src/DAY04/P2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = 1;
        while (S < MAX) {
            S *= 2;
        }
        tree = new int[2 * S];
        int n = Integer.valueOf(br.readLine());

        StringTokenizer st;
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.valueOf(st.nextToken());
            // a == 1 -> 사탕을 꺼내는 경우
            if (a == 1) {
                b = Integer.valueOf(st.nextToken());    // b번째 사탕 1 <= b <= 2,000,000
                int flavorIdx = queryTD(1, S, 1, b);
                sb.append(flavorIdx).append('\n');
                updateBU(flavorIdx, -1);
            }
            // a == 2 -> 사탕을 넣는 경우
            else if (a == 2) {
                b = Integer.valueOf(st.nextToken());    // 맛번호 1 <= b <= 1,000,000
                c = Integer.valueOf(st.nextToken());
                updateBU(b, c);
            }
        }

        System.out.print(sb);
    }

    static void updateBU(int target, int diff) {
        // leaf에서 target을 찾음
        int node = S + target - 1;
        // value 반영
        tree[node] += diff;
        // S = 1048576
        // target = 4 -> node = 1048579
        // node /= 2 -> 524289
        // Root에 도달할 때 까지 부모에 값 반영
        node /= 2;
        while (node > 0) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }

    static int queryTD(int left, int right, int node, int target) {
        // 1. leaf에 도착했을 때 -> 사탕의 맛 번호 반환
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;
        // 2. 왼쪽 >= target -> 왼쪽으로 이동
        if (tree[node * 2] >= target) {
            return queryTD(left, mid, node * 2, target);
        }
        // 3. 왼쪽 < target -> 오른쪽으로 이동 Q(target-왼쪽의 값)
        else {
            return queryTD(mid + 1, right, node * 2 + 1, target - tree[node * 2]);
        }
    }

}
