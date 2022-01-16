package Tree;
/*
    [문제] 이진 검색 트리
    [분류] 그래프 이론, 그래프 탐색, 트리, 재귀
    [날짜] 2022-01-16
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5639 {
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
        }

        void insertNode(int key) {
            if (key < this.key) {
                if (this.left == null) {
                    this.left = new Node(key);
                } else {
                    this.left.insertNode(key);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(key);
                } else {
                    this.right.insertNode(key);
                }
            }
        }

    }

    static void postOrder(Node node) {
        if (node.left != null)
            postOrder(node.left);
        if (node.right != null)
            postOrder(node.right);
        sb.append(node.key).append('\n');
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.valueOf(br.readLine()));

        while (true) {
            String s = br.readLine();
            if (s == null || s.isBlank())
                break;
            root.insertNode(Integer.valueOf(s));
        }

        postOrder(root);
        System.out.print(sb);
    }

}
