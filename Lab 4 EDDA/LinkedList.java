import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

/*
-------------------------
NO MODIFICAR ESTE ARCHIVO
-------------------------
*/

public class LinkedList {
    Video head;
    LinkedList(){
        head = null;
    }

    Video begin(){
        return head;
    }

    long isNumericLong(String s){
        long d = 0;
        try {
            d = Long.parseLong(s);
        }
        catch (NumberFormatException ignored){}
        return d;
    }
    int isNumericInt(String s){
        int i = 0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException ignored){}
        return i;
    }

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
        return (new Video(array.get(0),
                array.get(1),
                array.get(2),
                array.get(3),
                array.get(4),
                viewCount,
                likeCount,
                commentCount,
                popularity));
    }


    void insertFromFile(String file){
        String string;
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
                        start = i +1;
                    }
                }
                newLines.add(string.substring(start));
                Video newVideo = arrayToVideo(newLines);
                insertAtEnd(newVideo);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    Video recursiveSearch(Video v, String videoID){
        if( v == null){
            return  null;
        }
        if(Objects.equals(v.getVideoID(), videoID)){
            return v;
        }
        return recursiveSearch(v.getNext(), videoID );
    }
    Video iterativeSearch(Video v, String videoID){
        while(v!=null){
            if(Objects.equals(v.getVideoID(), videoID)){
                return v;
            }
            v = v.getNext();
        }
        return null;
    }


    void iterativeReverse(Video v){
        Video prev = null;
        Video actual = v;
        Video next;
        while (actual != null){
            next = actual.getNext();
            actual.setNext(prev);
            prev = actual;
            actual = next;
        }
        head = prev;
    }

    public void recursiveReverse(Video v){
        recursiveReverse(v, null);
    }

    private void recursiveReverse(Video v, Video prev) {
        if(v == null){
            return;
        }
        if(v.getNext() == null){
            v.setNext(prev);
            head = v;
            return;
        }
        recursiveReverse(v.getNext(),v);
        v.setNext(prev);
    }

    void recursivePrint(Video v){
        if(v == null){
            return;
        }
        v.reproduce();
        recursivePrint(v.getNext());
    }

    void iterativePrint(Video v){
        while(v.getNext() != null){
            v.reproduce();
            v = v.getNext();
        }
        v.reproduce();
    }


    Video recursiveLast(Video v){
        if ( v == null){
            return null;
        }
        if ( v.getNext() == null ){
            return v;
        }
        return recursiveLast(v.getNext());
    }

    Video iterativeLast(Video v){
        if(v == null){
            return null;
        }
        while(v.getNext() != null){
            v = v.getNext();
        }
        return v;
    }


    void insertAtEnd(Video v){
        Video tail = recursiveLast(head);

        if ( tail == null){
            head = v;
            return;
        }
        tail.setNext(v);
    }


    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        String file = "YoutubeDTSV2.csv";
        linkedList.insertFromFile(file);
        linkedList.iterativeReverse(linkedList.begin());
        linkedList.iterativePrint(linkedList.begin());
        System.out.println("search: " + linkedList.iterativeSearch(linkedList.begin(),"y83x7MgzWOA").getVideoTitle());
    }

}
