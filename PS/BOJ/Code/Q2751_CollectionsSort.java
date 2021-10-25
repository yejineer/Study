/*
    [문제]
    수 정렬하기 2 (https://www.acmicpc.net/problem/2751)

    [분류]
   정렬
 */

import java.io.*;
import java.util.*;

public class Q2751_CollectionsSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.valueOf(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            list.add(Integer.valueOf(br.readLine()));
        }

        Collections.sort(list);
        for (int i=0; i<list.size(); i++) {
            sb.append(list.get(i)).append('\n');
        }
        System.out.print(sb);
    }
}
