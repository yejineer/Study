package LCA;
/*
    [문제] 가장 가까운 공통 조상
    [분류] 그래프 이론, 그래프 탐색, 트리, 깊이 우선 탐색, 최소 공통 조상
    [날짜] 2021-11-12
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q3584_v1 {
    static List<Integer>[] list;   // 트리를 만들기 위한 리스트
    static int[] parentArr;    // 각 노드의 부모 노드 정보를 담는 배열
    static int[] depthArr;     // 각 노드의 부모 노드 정보를 담는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.valueOf(br.readLine()); // 테스트 케이스의 개수
        boolean[] isNotRoot;   // 루트 노드 찾기 위한 배열
        while (T-- > 0) {
            int N = Integer.valueOf(br.readLine()); // 트리를 구성하는 노드의 수. (2<= N <=10,000)
            list = new ArrayList[N+1];
            for (int i=1; i<=N; i++) {
                list[i] = new ArrayList<>();
            }
            parentArr = new int[N+1];
            depthArr = new int[N+1];
            isNotRoot = new boolean[N+1];

            int child, parent;
            StringTokenizer st;
            for (int i=0; i<N-1; i++) {
                st = new StringTokenizer(br.readLine());
                parent = Integer.valueOf(st.nextToken());
                child = Integer.valueOf(st.nextToken());
                list[parent].add(child);
                isNotRoot[child] = true;
            }
            
            // 루트 노드 찾기
            int rootIdx = 0;
            for (int i=1; i<=N; i++) {
                if (!isNotRoot[i]) {
                    rootIdx = i;
                    break;
                }
            }

            // 트리 생성
            makeTree(rootIdx, 0, 0);

            // 두 노드의 가장 가까운 공통 조상 출력
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.valueOf(st.nextToken());
            int nodeB = Integer.valueOf(st.nextToken());
            sb.append(LCA(nodeA, nodeB)).append('\n');
        }

        System.out.print(sb);
    }

    static void makeTree(int nowIdx, int depth, int parent) {
        depthArr[nowIdx] = depth;
        parentArr[nowIdx] = parent;
        for (int child : list[nowIdx]) {
            makeTree(child, depth+1, nowIdx);
        }
    }

    static int LCA(int a, int b) {
        // 현재 비교하고 있는 노드들의 depth
        int aDepth = depthArr[a];
        int bDepth = depthArr[b];

        // 비교하는 노드들의 depth가 다를 경우 같게 만들기
        while (aDepth > bDepth) {
            a = parentArr[a];
            aDepth--;
        }
        while (bDepth > aDepth) {
            b = parentArr[b];
            bDepth--;
        }

        // 공통 조상 찾기: a == b일 때 a(또는 b)의 값
        while (a != b) {
            a = parentArr[a];
            b = parentArr[b];
        }

        return a;
    }
}
