package DAY02.P2003;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("SDSLecture/src/DAY02/P2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
//        int[] A = new int[N];
        int[] A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.valueOf(st.nextToken());
        }

        int low = 0, high = 0, sum = A[0], count = 0;
        while (true) {
            if (sum == M) {
                // sum == M -> (답) low++
                count++;
                sum -= A[low++];
            } else if (sum > M) {
                // sum > M -> low++
                sum -= A[low++];
            } else {
                // sum < M -> high++
//                high++;
//                if (high == N) break;
//                sum += A[high];
                sum += A[++high];
            }
            if (high == N) break;
        }

        System.out.println(count);

    }
}
