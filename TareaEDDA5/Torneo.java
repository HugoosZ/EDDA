import java.util.Scanner;

public class Torneo {
    class Node {
        int val;
        String name;
        Node left, right;

        Node(int val, String name) {
            this.val = val;
            this.name = name;
        }
    }
    Node root;
    void insert(int key, String name) {
        root = auxInsert(root, key, name);
    }
    Node auxInsert(Node root, int key, String name) {
        if (root == null) {
            root = new Node(key, name);
            return root;
        } else if (key < root.val) {
            root.left = auxInsert(root.left, key, name);
        } else if (key > root.val) {
            root.right = auxInsert(root.right, key, name);
        }

        return root;
    }
    Node buscarNombre(Node root, String nombre) {
        if (root == null) {
            return null;
        }
        if (root.name.equals(nombre)) {
            return root;
        }
        Node encontradoIzq = buscarNombre(root.left, nombre);
        if (encontradoIzq != null) {
            return encontradoIzq;
        }
        return buscarNombre(root.right, nombre);
    }


    public Node AncestroMinimoComun(Node root, Node p, Node q) {
        if (root == null)
            return null;
        if (root == p || root == q)
            return root;
        Node left = AncestroMinimoComun(root.left, p, q);
        Node right = AncestroMinimoComun(root.right, p, q);
        if ((left == p && right == q) || (left == q && right == p)) {
            return root;
        } else if (left != null && right != null) {
            return root;
        } else if (left == null && right == null) {
            return null;
        } else {
            if(left == null && right != null)
                return right;
            else{
                return left;
            }
        }
    }

    public static void main(String[] args) {
        Torneo torneo = new Torneo();
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        for (int i = 0; i < s; i++) {
            String name = sc.next();
            int n = sc.nextInt();
            torneo.insert(n, name);
        }
        String name1 = sc.next();
        String name2 = sc.next();
        Node a = torneo.buscarNombre(torneo.root, name1);
        Node b = torneo.buscarNombre(torneo.root, name2);
        Node Comun = torneo.AncestroMinimoComun(torneo.root, a, b);
        System.out.println(Comun.name);
    }
}
