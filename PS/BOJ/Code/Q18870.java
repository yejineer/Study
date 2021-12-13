package Sorting;
/*
    [문제] 좌표 압축
    [분류] 정렬
    [날짜] 2021-12-13
 */
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Q18870 {

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        int[] X = new int[N];
        int cnt = 0;

        for(int i = 0 ; i < arr.length ; i ++)
            X[i] = Integer.parseInt(arr[i]);

        int[] temp = X.clone();
        Arrays.sort(X);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<X.length; i++) {
            if(!map.containsKey(X[i]))
                map.put(X[i], cnt++);
        }

        for (int i=0; i<N; i++) {
            sb.append(map.get(temp[i])).append(" ");
        }
        System.out.println(sb);
    }
}
