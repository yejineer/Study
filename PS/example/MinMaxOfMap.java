package Example;
/*
    2021-10-23
 */
import java.util.*;

public class MinMaxOfMap {

    static StringBuilder sb = new StringBuilder();
    static Map<String, Integer> map;
    public static void main(String[] args) {
        // HashMap 준비
        map = new HashMap<String, Integer>();
        map.put("x", 1);
        map.put("c", 2);
        map.put("f", 3);
        map.put("a", 2);

        map.forEach((key, value) -> sb.append(key+": ").append(value+"\n"));
        System.out.println("초기 map 구성"+"\n"+sb);

        withCollections();

        withComparator();

        sortHashMap();
    }

    public static void withCollections() {
        sb.setLength(0);
        sb.append("withCollections()").append('\n');
        // Max Key
        String maxKey = Collections.max(map.keySet());
        // Min Key
        String minKey = Collections.min(map.keySet());
        // Max Value
        Integer maxValue = Collections.max(map.values());
        // Min Value
        Integer minValue = Collections.min(map.values());

        // 결과 출력
        sb.append(maxKey + "\n");
        sb.append(minKey + "\n");
        sb.append(maxValue + "\n");
        sb.append(minValue + "\n");
        System.out.println(sb); // 5
    }

    public static void withComparator() {
        sb.setLength(0);
        sb.append("withComparator()").append('\n');
//        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return e1.getKey().compareTo(e2.getKey());
//            }
//        };
        /*
            2. 람다
         */
//        Comparator<Map.Entry<Integer, Integer>> comparator = (e1, e2) -> e1.getKey().compareTo(e2.getKey());

        /*
            3. Comparator.comparing(Map.Entry::getValue)
         */
        Comparator<Map.Entry<String, Integer>> comparator = Comparator.comparing(Map.Entry::getValue);

        Map.Entry<String, Integer> maxEntry = Collections.max(map.entrySet(), comparator);
        Map.Entry<String, Integer> minEntry = Collections.min(map.entrySet(), comparator);

        sb.append(maxEntry.getKey()).append(": ").append(maxEntry.getValue()).append('\n');
        sb.append(minEntry.getKey()).append(": ").append(minEntry.getValue()).append('\n');
        System.out.println(sb);
    }

    /*
        HashMap 정렬 (key와 value를 이용)
     */
    public static void sortHashMap() {
        sb.setLength(0);
        sb.append("sortHashMap()").append('\n');

        List<String> keySetList = new ArrayList<>(map.keySet());
//        Collections.sort(keySetList); // key 값 기준 정렬
//        Collections.sort(keySetList, Comparator.comparing(key -> map.get(key)));    // value 값 기준 정렬
        Collections.sort(keySetList, (k1, k2) -> {
            if (map.get(k1) == map.get(k2)) {
                return k2.compareTo(k1);    // value 똑같을 때, 사전 역순으로 출력
            } else {
                return map.get(k1).compareTo(map.get(k2));
            }
        });
        keySetList.forEach(key -> {
            sb.append(key).append(": ");
            sb.append(map.get(key)).append('\n');
        });
        System.out.print(sb);
    }
}

