package Greedy;
/*
    [문제] ATM
    [분류] 그리디 알고리즘, 정렬
    [날짜] 2021-11-22
 */
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;

public class Q11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        List<Integer> list = new ArrayList<>(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            list.add(Integer.valueOf(st.nextToken()));
        }
        Collections.sort(list);

        int prevSum = 0;
        int totalSum = 0;
        for (int i=0; i<N; i++) {
            totalSum += prevSum + list.get(i);
            prevSum += list.get(i);
        }

        System.out.println(totalSum);
    }

}
