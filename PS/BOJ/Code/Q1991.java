package Tree;
/*
    [문제] 트리 순회
    [분류] 트리, 재귀
    [날짜] 2022-01-07
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1991 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        Tree tree = new Tree();
        StringTokenizer st;
        
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.insert(node, left, right);
        }

        tree.preOrder(tree.root);
        sb.append('\n');
        tree.inOrder(tree.root);
        sb.append('\n');
        tree.postOrder(tree.root);
        System.out.println(sb);
    }

    static class Node {
        char data;
        Node left;
        Node right;

        public Node(char data) {
            this.data = data;
        }
    }

    static class Tree {
        Node root;

        void insert(char data, char left, char right) {
            if (root == null) {
                if (data != '.')
                    root = new Node(data);
                if (left != '.')
                    root.left = new Node(left);
                if (right != '.')
                    root.right = new Node(right);
            } else {
                search(root, data, left, right);
            }
        }

        void search(Node root, char data, char leftData, char rightData) {
            if (root == null)
                return;
            if (root.data == data) {
                if (leftData != '.')
                    root.left = new Node(leftData);
                if (rightData != '.')
                    root.right = new Node(rightData);
            } else {
                search(root.left, data, leftData, rightData);
                search(root.right, data, leftData, rightData);
            }
        }

        void preOrder(Node root) {
            sb.append(root.data);
            if (root.left != null)
                preOrder(root.left);
            if (root.right != null)
                preOrder(root.right);
        }

        void inOrder(Node root) {
            if (root.left != null)
                inOrder(root.left);
            sb.append(root.data);
            if (root.right != null)
                inOrder(root.right);
        }

        void postOrder(Node root) {
            if (root.left != null)
                postOrder(root.left);
            if (root.right != null)
                postOrder(root.right);
            sb.append(root.data);
        }
    }
}
