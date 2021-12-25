package Sorting;
/*
    [문제 N번째 큰 수
    [분류] 정렬
    [날짜] 2021-12-25
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2693 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.valueOf(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            ArrayList<Integer> list = new ArrayList<>();

            for (int i=0; i<10; i++) {
                list.add(Integer.valueOf(st.nextToken()));
            }

            Collections.sort(list, Collections.reverseOrder());
            
            sb.append(list.get(2)).append('\n');
        }

        System.out.print(sb);
    }
}
