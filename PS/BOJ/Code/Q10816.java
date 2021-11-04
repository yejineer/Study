package DataStructure;
/*
    [문제] 숫자 카드 2
    [분류] 자료 구조, 정렬, 이분 탐색, 해시를 사용한 집합과 맵
    [날짜] 2021-11-04
 */
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        StringBuilder sb = new StringBuilder();

        int n = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int key;
        while (st.hasMoreTokens()) {
            key = Integer.valueOf(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int m = Integer.valueOf(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            key = Integer.valueOf(st.nextToken());
            sb.append(map.getOrDefault(key, 0)).append(" ");
        }
        System.out.print(sb);
    }
}
