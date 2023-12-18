import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class Platform {
    Video head;
    Platform(){
        head = null;
    }

    static class Video {
        String videoID;
        String videoTitle;
        String channelID;
        String channelTitle;
        String publishedAt;
        long viewCount;
        long likeCount;
        long commentCount;
        long Popularity;
        Video next;

        Video(String videoID, String videoTitle, String channelID, String channelTitle, String publishedAt,  long viewCount,  long likeCount,  long commentCount) { //creacion del nodo
            this.videoID = videoID;
            this.videoTitle = videoTitle;
            this.channelID = channelID;
            this.channelTitle = channelTitle;
            this.publishedAt = publishedAt;
            this.viewCount = viewCount;
            this.likeCount = likeCount;
            this.commentCount = commentCount;

            next = null;
            if(likeCount!=0){
                Popularity=viewCount/likeCount;
            }
            else{
                Popularity=0;
            }
    }
        void play(){
            System.out.println(videoID +" "+videoTitle);
        }
    }

    Video begin(){
        return head;
    }
    long isNumericLong(String s){
        long d = 0;
        try {
            d = Long.parseLong(s);  // esta linea transforma el string S a un long
        }
        catch (NumberFormatException ignored){}
        return d; //returna el numero string pero cambiado a long
    }
    Video arrayToVideo(ArrayList<String> array){
        return new Video(array.get(0),
                array.get(1),
                array.get(2),
                array.get(3),
                array.get(4),
                isNumericLong(array.get(5)),
                isNumericLong(array.get(6)),
                isNumericLong(array.get(7))
        );
    }
    void insertFromFile(String file){ //inserta todos los datos del archivo en la LL.
        String string ;
        try (BufferedReader br = new BufferedReader(new FileReader(file))){//Se
            //Skip first
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
                insertAtEnd(newVideo);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    Video iterativeLast(Video v){ //recorre la lista de forma iterativa y retorna el ultimo nodo.
        while(v.next != null){
            v = v.next;
        }
        return v;
    }

    void insertAtEnd(Video v) {
        if (head == null) { // Caso especial: la lista está vacía
            head = v;
        } else {
            Video tail = iterativeLast(head); // Buscamos el último nodo de la lista
            tail.next = v; // Añadimos el nuevo nodo al final
        }
    }
    void iterativePrint(Video v) {
        while (v != null) {
            v.play();
            v = v.next;
        }
    }


    Video search(Video v, String videoID){

        while(v != null){
            if (Objects.equals(v.videoID, videoID)){
                v.play();
                return v;
            }
            v = v.next;
        }
        return null;
    }
    public void reverse(Video v) {
        if (head == null ) {
            return;
        }

        Video prev = null;
        Video aux = head;
        Video next= null;

        while (aux != null) {
            next = aux.next;
            aux.next = prev;
            prev = aux;
            aux = next;
        }

        head = prev;
    }

    public static void main(String[] args) {
        //pruebas para la API
        Platform platform = new Platform();
        String file = "C:\\YoutubeDTSV2.csv";
        platform.insertFromFile(file);
        //platform.search(platform.begin(), "y83x7MgzWOA");
        System.out.println(platform.begin().videoTitle);
        System.out.println(platform.begin().Popularity);

    }
}

