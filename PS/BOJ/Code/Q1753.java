package Dijkstra;
/*
    [문제] 최단경로
    [분류] 그래프 이론, 다익스트라
    [날짜] 2021-11-11
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1753 {
    static int v, e, k;
    static int[] dist;  // 거리배열
    static ArrayList<Node>[] adjList;   // 방향 간선 인접리스트

    static class Node implements Comparable<Node> {
        int vertex;
        int value;

        public Node(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.valueOf(st.nextToken());
        e = Integer.valueOf(st.nextToken());
        k = Integer.valueOf(br.readLine());

        // dist(거리배열) 초기화
        dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // adjList 초기화
        adjList = new ArrayList[v+1];
        for (int i=1; i<=v; i++) {
            adjList[i] = new ArrayList<>();
        }

        // adjList 데이터 채우기
        int u, v, w;
        for (int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.valueOf(st.nextToken());    // 출발지 정점
            v = Integer.valueOf(st.nextToken());    // 목적지 정점
            w = Integer.valueOf(st.nextToken());    // 가중치
            adjList[u].add(new Node(v, w));
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.value > dist[now.vertex]) continue;

            int len = adjList[now.vertex].size();
            for (int i=0; i<len; i++) {
                Node adjNode = (Node) adjList[now.vertex].get(i);
                if (dist[adjNode.vertex] > now.value + adjNode.value) {
                    dist[adjNode.vertex] = now.value + adjNode.value;
                    pq.add(new Node(adjNode.vertex, dist[adjNode.vertex]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        dijkstra(k);

        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=v; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            }
            else {
                sb.append(dist[i]+"\n");
            }
        }
        System.out.print(sb);
    }

}
