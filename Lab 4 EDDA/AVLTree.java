import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class AVLTree {
    //No modificar
    private Node root;

    //No modificar
    public static class Node{
        String key;
        LinkedList videos;
        int height;
        Node left;
        Node right;

        Node(String key, LinkedList videos){
            this.key = key;
            this.videos = videos;
        }
    }
    //Constructor para TEST 1
    //No modificar
    AVLTree(){}

    //Constructor para TEST 2
    //No modificar
    AVLTree(String file){
        String string;

        Hashtable<String, LinkedList> channels = new Hashtable<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            br.readLine();
            while((string = br.readLine()) != null){
                boolean inQuotes = false;
                int start = 0;
                ArrayList<String> newLines = new ArrayList<>();
                for (int i = 0; i < string.length(); i++) {
                    if (string.charAt(i) == '\"') {
                        inQuotes = !inQuotes;
                    } else if (string.charAt(i) == ',' && !inQuotes) {
                        newLines.add(string.substring(start, i));
                        start = i + 1;
                    }
                }
                newLines.add(string.substring(start));
                Video newVideo = arrayToVideo(newLines);
                String key = newVideo.getChannelTitle();
                if(channels.get(key) == null){
                    LinkedList linkedList = new LinkedList();
                    linkedList.insertAtEnd(newVideo);
                    channels.put(key, linkedList);
                }
                else {
                    channels.get(key).insertAtEnd(newVideo);
                }
            }
            Enumeration<String> keys = channels.keys();
            //se agregan datos agrupados del hash al árbol
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                insert(key,channels.get(key));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //No modificar
    public Node getRoot(){return root;}

    //No modificar
    long isNumericLong(String s){
        long d = 0;
        try {
            d = Long.parseLong(s);
        }
        catch (NumberFormatException ignored){}
        return d;
    }

    //No modificar
    int isNumericInt(String s){
        int i = 0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException ignored){}
        return i;
    }
    //No modificar
    Video arrayToVideo(ArrayList<String> array){
        if(array.get(5).equals("")){
            array.set(5,"0");
        }
        if(array.get(6).equals("")){
            array.set(6,"0");
        }

        long viewCount = isNumericLong(array.get(5));
        int likeCount = isNumericInt(array.get(6));
        int commentCount = isNumericInt(array.get(7));

        float popularity = 0F;
        if( viewCount != 0) { popularity = Float.parseFloat(array.get(5)) / Float.parseFloat(array.get(6)); }
        Video v = new Video(array.get(0),
                array.get(1),
                array.get(2),
                array.get(3),
                array.get(4),
                viewCount,
                likeCount,
                commentCount,
                popularity);
        return v;
            }
    //Depurar
    void insert(String key, LinkedList videos){
        root = insertNode(root, key, videos);
    }
    //Depurar
    Node insertNode(Node node, String key, LinkedList videos){
        if(node == null){
            return new Node(key, videos);
        }
        if(key.compareTo(node.key) < 0)
            node.left = insertNode(node.left, key, videos);
        else if (key.compareTo(node.key) > 0) {
            node.right = insertNode(node.right,key, videos);
        }
        else {
            throw new RuntimeException("Duplicate key!");
        }
        return balance(node);
    }
    //Depurar
    Node balance(Node node) {
        updateHeight(node);
        if (getBalance(node) < -1) {
            if (height(node.left.right) > height(node.left.left)) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (getBalance(node) > 1) {
            if (height(node.right.left) > height(node.right.right)) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }
    //Depurar
    Node rotateRight(Node n1) {
        Node n2 = n1.left;
        Node n3 = n2.right;
        n2.right = n1;
        n1.left = n3;
        updateHeight(n1);
        updateHeight(n2);
        return n2;
    }
    //Depurar
    Node rotateLeft(Node n1) {
        Node n2 = n1.right;
        Node n3 = n2.left;
        n2.left = n1;
        n1.right = n3;
        updateHeight(n1);
        updateHeight(n2);
        return n2;
    }

    Node find(String key){
        return find(root, key);
    }
    Node find(Node node, String key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        }
        Node encontradoIzq = find(node.left, key);
        if (encontradoIzq != null) {
            return encontradoIzq;
        }
        return find(node.right, key);
    }

    public void delete(String key) {
        root = delete(root, key);
    }
    private Node delete(Node root, String key) {
        if (root == null) return null;

        int aux = key.compareTo(root.key);
        if (aux < 0) {
            root.left = delete(root.left, key);
        } else if (aux > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;

            Node temp = root;
            root = GetMin(temp.right);
            root.right = deleteMin(temp.right);
            root.left = temp.left;
            root.height = height(root.left) + height(root.right) + 1;
        }
        return balance(root);
    }
    private Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.height = height(root.left) + height(root.right) + 1;
        root = balance(root);
        return root;
    }
    Node GetMin(Node node){
        if(node == null){
            return null;
        }
        if(node.left != null){
            return GetMin(node.left);
        }
        return node;
    }

    //No modificar
    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    //No modificar
    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    //No modificar
    private int getBalance(Node node){
        return (node == null) ? 0 :  height(node.right) - height(node.left);
    }

    //No modificar
    void preOrder(Node node) {
        if (node != null) {
            System.out.println("Channel Title: " + node.key + "| Height: " + node.height + "| root: " + node.videos.begin().getVideoTitle());
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    //No modificar
    void inOrderInv(Node node) {
        if (node != null) {
            inOrderInv(node.right);
            System.out.println("Channel Title: " + node.key + "| Height: " + node.height + "| root: " + node.videos.begin().getVideoTitle());
            inOrderInv(node.left);
        }
    }

    public static void main(String[] args) {

        String file = "C:\\YoutubeDTSV2.csv";
        AVLTree tree = new AVLTree(file);
        tree.preOrder(tree.root);
        System.out.println("Resultado de la busqueda: ");
        Node result = tree.find("twenty one pilots");
        result.videos.recursivePrint(result.videos.begin());
        tree.delete("notFoundVideo");
    }
}
