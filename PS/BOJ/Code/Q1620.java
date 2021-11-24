package DataStructure;
/*
    [문제] 나는야 포켓몬 마스터 이다솜
    [분류] 자료 구조, 해시를 사용한 집합과 맵
    [날짜] 2021-11-24
 */
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, Integer> nameKeyMap = new HashMap<>();
        Map<Integer, String> orderKeyMap = new HashMap<>();
        int N = Integer.valueOf(st.nextToken());    // 포켓몬의 수
        int M = Integer.valueOf(st.nextToken());    // 맞춰야 하는 문제의 개수

        for (int i=1; i<=N; i++) {
            String name = br.readLine();
            nameKeyMap.put(name, i);
            orderKeyMap.put(i, name);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            String problem = br.readLine();
            if (problem.charAt(0) > 'A'-1) {
                sb.append(nameKeyMap.get(problem)).append('\n');
            } else {
                sb.append(orderKeyMap.get(Integer.valueOf(problem))).append('\n');
            }
        }

        System.out.print(sb);
    }
}
