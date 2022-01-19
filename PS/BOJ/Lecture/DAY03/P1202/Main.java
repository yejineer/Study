package DAY03.P1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SDSLecture/src/DAY03/P1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        K = Integer.valueOf(st.nextToken());

        // 보석 정보 입력
        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.valueOf(st.nextToken());
            int value = Integer.valueOf(st.nextToken());
            jewels.add(new Jewel(weight, value));
        }

        // 가방 정보 입력
        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bags.add(Integer.valueOf(br.readLine()));
        }
        
        // 무게 기준으로 보석 정렬 (오름차순)
        Collections.sort(jewels, Comparator.comparingInt(o -> o.M));
        // 담을 수 있는 최대 무게 기준으로 가방 정렬 (오름차순)
        Collections.sort(bags);

        long result = 0;
        int jewelIdx = 0;
        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.V, o1.V));
        for (int i = 0; i < K; i++) {
            while (jewelIdx < N && jewels.get(jewelIdx).M <= bags.get(i)) {
                Jewel jewel = jewels.get(jewelIdx++);
                // 현재 보석의 무게 <= maxWeight -> pq에 넣고 list에서 제거
                pq.add(new Jewel(jewel.M, jewel.V));
            }
            if (!pq.isEmpty()) {    // 처리 안 해주면 NullPointerException 남
                result += pq.poll().V;
            }
        }

        System.out.println(result);
    }

}

class Jewel {
    int M;
    int V;

    public Jewel(int m, int v) {
        M = m;
        V = v;
    }

    public int getM() {
        return M;
    }

    public int getV() {
        return V;
    }
}