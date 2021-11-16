package DataStructure;
/*
    [문제] 듣보잡
    [분류] 자료 구조, 문자열, 정렬, 해시를 사용한 집합과 맵
    [날짜] 2021-11-16
 */
import java.io.*;
import java.util.*;

public class Q1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        for (int i=0; i<n; i++) {
            String name = br.readLine();
            map.put(name, map.getOrDefault(name, 0)+1);
        }

        for (int i=0; i<m; i++) {
            String name = br.readLine();
            map.put(name, map.getOrDefault(name, 0)+1);
        }

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                list.add(entry.getKey());
            }
        }

        Collections.sort(list);
        sb.append(list.size()).append('\n');
        for (int i=0; i<list.size(); i++) {
            sb.append(list.get(i)).append('\n');
        }
        System.out.print(sb);
    }
}
