import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
    public static ArrayList<ArrayList<String>> ReadCSVToArray2D(String file){
        String string;
        ArrayList<ArrayList<String>> dataset = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((string = br.readLine()) != null){
                // Se elimina las comillas al inicio y al final de la fila
                for (int i=0; i < string.length(); i++){
                    if (string.charAt(i) == '\"'){
                        string = string.substring(1,string.length()-1);
                    } else {
                        break;
                    }
                }
                ArrayList<String> newLines = new ArrayList<>();
                newLines.add(string.substring(start));
                dataset.add(newLines);            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dataset;
    }

    public static void main(String[] args) {
        String file = "C:\\YoutubeDTSV2.csv";
        ArrayList<ArrayList<String>> dataset = ReadCSVToArray2D(file);
        if(dataset.size() != 0) {
            System.out.println("Number of rows " + dataset.size());
            System.out.println("Number of columns " + dataset.get(0).size());
        }
    }
}