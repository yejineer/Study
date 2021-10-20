/*
  단어 정렬
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Q1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.valueOf(br.readLine());
        String[] arr = new String[n];
        for (int i=0; i<n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                } else {
                    return s1.length() - s2.length();
                }
            }
        });

        sb.append(arr[0]).append('\n');
        for (int i=1; i<n; i++) {
            if (!(arr[i].equals(arr[i-1])))
                sb.append(arr[i]).append('\n');
        }
        System.out.println(sb);

    }
}
