package DAY07.P1753;
/*
    [문제] B - 최단경로
    [분류] 그래프 2
    [알고리즘] 다익스트라
 */
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Info implements Comparable<Info> {
        int node;
        int distance;

        public Info(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(distance, o.distance);
        }
    }

    static int V, E, K;
    static ArrayList<Info>[] adjList;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.valueOf(st.nextToken());
        E = Integer.valueOf(st.nextToken());
        K = Integer.valueOf(br.readLine());
        adjList = new ArrayList[V + 1];
        dist = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
            dist[i] = INF;
        }

        int u, v, w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.valueOf(st.nextToken());
            v = Integer.valueOf(st.nextToken());
            w = Integer.valueOf(st.nextToken());
            adjList[u].add(new Info(v, w));
        }

        // 다익스트라 알고리즘 실행
        findShortestPath(K);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                bw.write("INF\n");
            } else {
                bw.write(dist[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void findShortestPath(int start) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        // 시작점에 대한 정보를 넣어준다.
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Info now = pq.poll();

            if (dist[now.node] < now.distance)  // = 이면 안 됨. start일 경우 (0 == 0이라 continue 되니까 문제 생김)
                continue;

            // now의 연결된 점들의 거리 정보를 갱신
            for (Info next : adjList[now.node]) {
                // 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음

                if (dist[next.node] > dist[now.node] + next.distance) {
                    dist[next.node] = dist[now.node] + next.distance;
                    pq.add(new Info(next.node, dist[next.node]));
                }
            }
        }
    }
}
