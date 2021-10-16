package Etc.Math;

/*
    벌집
 */
import java.util.Scanner;

public class Q2292 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int layer = 1;
        int room = 0;
        int totalRoom = 1;
        while (true) {
            if (n <= totalRoom) break;
            room += 6;
            totalRoom += room;
            layer++;
        }
        System.out.println(layer);
    }
}
