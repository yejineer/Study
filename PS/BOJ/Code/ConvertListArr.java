package Example;

import java.util.*;
import java.util.stream.Collectors;

public class ConvertListArr {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        convertListAndArr();
    }

    public static void convertListAndArr() {
        sb.append("convertListAndArr()\n");

        String[] arr = {"ee1", "cc2", "bb1"};
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        list.add("aa3");

        Collections.sort(list, Collections.reverseOrder());
        String[] new_arr = list.stream().toArray(String[]::new);
        Arrays.stream(new_arr).forEach(s-> sb.append(s).append('\n'));
        for (String s : new_arr) {
            sb.append(s).append('\n');
        }
        System.out.print(sb);
    }
}
