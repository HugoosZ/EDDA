import java.util.Scanner;
public class JardinVirtual {
    class Node {
        int val;
        Node left, right;
        Node(int val) {
            this.val = val;
        }
    }
    Node root;
    void insert(int key) {
        root = auxInsert(root, key);
    }
    Node auxInsert(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        } else if (key < root.val) {
            root.left = auxInsert(root.left, key);
        } else if (key > root.val) {
            root.right = auxInsert(root.right, key);
        }
        return root;
    }
    void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] InOrder = new int[n];
        int[] PostOrder = new int[n];

        for (int i = 0; i < n; i++) {
            InOrder[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            PostOrder[i] = sc.nextInt();
        }
        JardinVirtual tree = new JardinVirtual();
        for(int i = n-1 ; i >= 0; i--) {
            tree.insert(PostOrder[i]);
        }
        tree.preOrder(tree.root);


    }
}
