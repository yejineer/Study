/*
    팰린드롬수
 */
import java.io.*;

public class Q1259
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        String result;
        while (true) {
            s = br.readLine();
            if (s.equals("0")) break;
            result = "yes";
            for (int i=0; i<s.length()/2; i++) {
                if(s.charAt(i) != s.charAt(s.length()-1-i)) {
                    result = "no";
                    break;
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
