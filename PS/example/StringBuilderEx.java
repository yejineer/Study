package Example;
public class StringBuilderEx {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("Welcome to Java tutorial");
        System.out.println("Char at index 3: " + sb.charAt(3));
        System.out.println("Index of string 'to' : " + sb.indexOf("to"));
        System.out.println("Last index of string 'to' : " + sb.lastIndexOf("to"));
        System.out.println();

        sb.replace(0, sb.length(), "Welcome to Java tutorial");
        sb.insert(2, "Hello");
        System.out.println("insert한 후: " + sb);
        System.out.println();

        sb.replace(0, sb.length(), "Welcome to Java tutorial");
        System.out.println("Substring from index 5: " + sb.substring(5));
        System.out.println("Substring ranging between index 5 and 10: " + sb.substring(5, 10));
        System.out.println("After using substring(): " + sb);
        System.out.println();

        sb.delete(2, 8);    // 2부터 (8-1)번째 까지 삭제
        System.out.println("String after delete operation: " + sb); // Weto Java tutorial
        sb.deleteCharAt(15);    // 현재 sb에서 정확하게 15번째 자리의 문자(i) 삭제
        System.out.println("String after deleteCharAt operation: " + sb);   // Weto Java tutoral
        System.out.println();

        sb.replace(0,sb.length(), "Welcome to Java tutorial");
        sb.replace(2, 4, "Hello");
        System.out.println(sb);
        System.out.println("replace 한 후: " + sb);
        System.out.println();

        sb.replace(0, sb.length(), "Welcome to Java tutorial");
        sb.reverse();
        System.out.println("reverse한 후: " + sb);
        System.out.println();

        sb.replace(0, sb.length(), "Welcome to Java tutorial");
        System.out.println("Size of string: " + sb.length());
        sb.setLength(50);
        System.out.println("New size of the string: " + sb.length());
        System.out.println("Now sb: " + sb);

    }
}
