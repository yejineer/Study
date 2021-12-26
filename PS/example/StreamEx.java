package Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEx {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        useStream();
    }
    public static void useStream() {
        sb.setLength(0);
        sb.append("useStream()\n");

        List<Integer> cal = Arrays.asList(49, 123, 22, 55, 21);

        //collect
        sb.append("[collect]\n");
        List<Integer> newList = cal.stream().filter(x-> x > 50).collect(Collectors.toList());
        Collections.sort(newList);
        newList.forEach(x->sb.append(x).append('\n')); // x가 숫자형일 땐, +'\n'하면 아스키코드 10 합쳐져서 133으로 출력되기 때문에 append('\n')으로 하기.

        //reduce
        sb.append("[reduce]\n");
        int reduce = cal.stream().reduce((x, y) -> x+y).get();
        sb.append(reduce).append('\n');

        // min
        sb.append("[min]\n");
        int min = Collections.min(cal);
        sb.append(min).append('\n');

        //count
        sb.append("[count]\n");
        String[] arrs = {"book", "desk", "keyboard", "mouse", "cup"};
        List<String> words = new ArrayList<>(Arrays.asList(arrs));
        sb.append("일치: ");
        int sameCount = (int) words.stream().filter(w->w.contentEquals("cup")).count();
        sb.append(sameCount).append('\n');
        sb.append("포함: ");
        int containCount = (int) words.stream().filter(w-> (w.contains("o") && w.contains("u"))).count(); //"o"와 "u"를 동시에 갖는
        sb.append(containCount).append('\n');

        // 출력
        System.out.print(sb);
    }
}
