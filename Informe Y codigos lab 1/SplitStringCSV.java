import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        lines.add("1232,,video title");
        lines.add("1342,video \"title\"");
        lines.add("1342,\"video, title\"");
        lines.add("1342,\"video, \"\"title\"\"\"");

        ArrayList<ArrayList<String>> dataset = new ArrayList<>();

        lines.forEach(string -> {
            boolean inQuotes = false;
            int start = 0;
            ArrayList<String> newLines = new ArrayList<>();
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '\"') {    //string.charAt(i) es el que lee las palabras , osea si tenemos un hola string.charAt(0) es una h
                    inQuotes = !inQuotes;

                }
                else if(string.charAt(i) == ',' && inQuotes == false){
                        newLines.add(string.substring(start,i));
                            start=i+1;
                    }
            }
            start=0;
            inQuotes = false;
            newLines.add(string.substring(start));
            dataset.add(newLines);
        });
        dataset.forEach( element -> System.out.println(element + " size " + element.size()));
    }
}