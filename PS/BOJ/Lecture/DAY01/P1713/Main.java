package DAY01.P1713;
/*
    [문제] D - 후보 추천하기
    [분류] 알고리즘 기초
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Picture {
        int student;
        int order;

        public Picture(int student, int order) {
            this.student = student;
            this.order = order;
        }

        public int getStudent() {
            return student;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[101];
        List<Picture> list = new ArrayList<>();

        int N = Integer.valueOf(br.readLine());
        int total = Integer.valueOf(br.readLine());

        // 새로운 후보 추가 & 비어있는 사진틀 없는 경우 -> 정렬 필요
        // 추천 받은 횟수, 가장 오래된 사진 순으로 정렬
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int order = 0; order < total; order++) {
            int student = Integer.valueOf(st.nextToken());

            if (count[student] > 0) {
                count[student]++;
            } else {
                if (list.size() == N) {
                    Collections.sort(list, (o1, o2) -> {
                        int comp1 = Integer.compare(count[o1.student], count[o2.student]);    // 추천 수 (올림차순)
                        if (comp1 == 0)
                            return Integer.compare(o1.order, o2.order);     // 게시일자 (내림차순)
                        else
                            return comp1;
                    });

                    Picture deleted = list.remove(0);
                    count[deleted.student] = 0;
                    count[student]++;
                    list.add(new Picture(student, order));
                } else {
                    count[student]++;
                    list.add(new Picture(student, order));
                }
            }
        }

        Collections.sort(list, Comparator.comparingInt(Picture::getStudent));

        StringBuilder sb = new StringBuilder();
        for (Picture pic : list) {
            sb.append(pic.student).append(' ');
        }
        System.out.println(sb);
    }

}

