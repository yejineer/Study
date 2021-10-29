/*
    [문제]
    좌표 정렬하기

    [분류]
    정렬
 */
import java.io.*;
import java.util.*;

public class Q11650 {
    static class Coordinate {
        int x;
        int y;

        Coordinate (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Coordinate> list = new ArrayList<>();
        int x, y;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.valueOf(st.nextToken());
            y = Integer.valueOf(st.nextToken());
            list.add(new Coordinate(x, y));
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1.x == o2.x)
                return o1.y - o2.y;
            else
                return o1.x - o2.x;
        });

        list.forEach(o-> sb.append(o.x + " ").append(o.y).append('\n'));
        System.out.print(sb);
    }
}
