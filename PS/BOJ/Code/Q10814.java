/*
    [문제]
    나이순 정렬

    [분류]
    정렬
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q10814 {
    static class Person {
        int age;
        String name;

        Person (int age, String name) {
            this.age = age;
            this.name = name;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.valueOf(br.readLine());
        List<Person> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Person(Integer.valueOf(st.nextToken()), st.nextToken()));
        }
        Collections.sort(list, Comparator.comparingInt(p -> p.age));

        StringBuilder sb = new StringBuilder();
        list.forEach(p -> sb.append(p.age + " ").append(p.name).append('\n'));
        System.out.print(sb);
    }
}
