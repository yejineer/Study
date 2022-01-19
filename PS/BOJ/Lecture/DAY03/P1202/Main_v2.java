package DAY03.P1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_v2 {
    static int N, K;
    static Jewel[] jewels;
    static int[] bags;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SDSLecture/src/DAY03/P1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        K = Integer.valueOf(st.nextToken());
        jewels = new Jewel[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.valueOf(st.nextToken());
            int value = Integer.valueOf(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.valueOf(br.readLine());
        }

        // 가방 오름 차순 정렬
        Arrays.sort(bags);
        // 보석 무게순 정렬
        Arrays.sort(jewels, Comparator.comparingInt(Jewel::getM));
        // 보석 높은 값 기준 힙
        PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getV).reversed());

        long result = 0;
        int jIndex = 0;
        // 1. 남은 가방 중 제일 작은 가방을 선택 <- 정렬
        for (int i = 0; i < K; i++) {
            // 2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택
            while (jIndex < N && jewels[jIndex].M <= bags[i]) {
                // 현재 보석의 무게 <= maxWeight -> pq에 넣고 list에서 제거하면 땡겨와서 연산이 증가됨. 그러므로 삭제하지 말아야 함.
                pq.add(jewels[jIndex++]);
            }
            if (!pq.isEmpty()) {    // 처리 안 해주면 NullPointerException 남
                result += pq.poll().V;
            }
        }

        System.out.println(result);
    }

}
