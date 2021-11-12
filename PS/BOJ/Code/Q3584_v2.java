package LCA;
/*
    [문제] 가장 가까운 공통 조상
    [분류] 그래프 이론, 그래프 탐색, 트리, 깊이 우선 탐색, 최소 공통 조상
    [날짜] 2021-11-12
 */
import java.io.*;
import java.util.StringTokenizer;

public class Q3584_v2 {
    static int[] parentArr;    // 각 노드의 부모 노드 정보를 담는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.valueOf(br.readLine()); // 테스트 케이스의 개수
        while (T-- > 0) {
            int N = Integer.valueOf(br.readLine()); // 트리를 구성하는 노드의 수. (2<= N <=10,000)
            parentArr = new int[N+1];
            for (int i=1; i<=N; i++) {
                parentArr[i] = i;
            }

            int child, parent;
            StringTokenizer st;
            for (int i=0; i<N-1; i++) {
                st = new StringTokenizer(br.readLine());
                parent = Integer.valueOf(st.nextToken());
                child = Integer.valueOf(st.nextToken());
                parentArr[child] = parent;
            }

            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.valueOf(st.nextToken());
            int nodeB = Integer.valueOf(st.nextToken());

            boolean[] isParent = new boolean[N+1];
            while (parentArr[nodeA] != nodeA) {
                isParent[nodeA] = true;
                nodeA = parentArr[nodeA];
            }

            while (parentArr[nodeB] != nodeB) {
                nodeB = parentArr[nodeB];
                if (isParent[nodeB]) {
                    sb.append(nodeB).append('\n');
                    break;
                }
            }

            if (parentArr[nodeB] == nodeB)
                sb.append(nodeB).append('\n');
        }
        System.out.print(sb);
    }

}
