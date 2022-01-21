package DAY05.P1837;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static char[] P;
    static boolean[] isNotPrime;
    static List<Integer> primes = new ArrayList<>();;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("SDSLecture/src/DAY05/P1837/input2.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken().toCharArray();
        K = Integer.valueOf(st.nextToken());
        isNotPrime = new boolean[K + 1];

        for (int i = 2; i < K + 1; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
                for (int j = i * 2; j < K + 1; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int prime : primes) {
            if (prime >= K)
                break;
            if (checkIsBad(prime)) {
                sb.append("BAD").append(' ').append(prime);
                System.out.println(sb);
                return;
            }
        }
        System.out.println("GOOD");

    }

    static boolean checkIsBad(int x) {
        int r = 0;
        for (int i = 0; i < P.length; i++) {
            r = (r * 10 + (P[i] - '0')) % x;
        }
        if (r == 0)
            return true;
        else
            return false;
    }
}
