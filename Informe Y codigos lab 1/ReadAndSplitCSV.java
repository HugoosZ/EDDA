import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static ArrayList<ArrayList<String>> ReadCSVToArray2D(String file){
        String string;
        ArrayList<ArrayList<String>> dataset = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            boolean inQuotes = false;
            int start = 0;

            while((string = br.readLine()) != null){

                for (int i=0; i < string.length(); i++){
                    if (string.charAt(i) == '\"'){
                        string = string.substring(1,string.length()-1);
                    } else {
                        break;
                    }
                }
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
                inQuotes= false;
                start=0;
                newLines.add(string.substring(start));
                dataset.add(newLines);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dataset;
    }
    public static void main(String[] args) {
        String file = "C:\\Users\\Hugo\\OneDrive\\Documentos\\Programas\\A.CSV";
        ArrayList<ArrayList<String>> dataset = ReadCSVToArray2D(file);
        if(dataset.size() != 0){
            dataset.forEach(System.out::println);
            System.out.println("Number of columns " + dataset.toArray().length);
            System.out.println("Number of columns " + dataset.get(0).size());
        }
    }
}