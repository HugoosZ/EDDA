import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Coordenada {
    int x;
    int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solicitud {
    Coordenada origen;
    Coordenada destino;
    int TLlegada;

    public Solicitud(Coordenada origen, Coordenada destino, int TLlegada) {
        this.origen = origen;
        this.destino = destino;
        this.TLlegada = TLlegada;
    }
}

public class AR{
    public static ArrayList<String> TransformarSolicitudes(ArrayList<Coordenada> ubicaciones, ArrayList<Solicitud> solicitudes){
        ArrayList<String> Distancias = new ArrayList<>();
        for(Solicitud solicitud : solicitudes){
            Coordenada Uber = UberAleatorio(ubicaciones);
            if (Uber != null) {
                int distancia = calcularDistancia(Uber, solicitud.destino);
                Distancias.add(solicitud.TLlegada + " " + distancia);
            }
        }
        return Distancias;
    }

    public static ArrayList<Coordenada> Ubicaciones(String archivo){
        ArrayList<Coordenada> ubicaciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String Archivo;
            while ((Archivo = br.readLine()) != null){
                String[] partes = Archivo.split(" ");
                int x = Integer.parseInt(partes[0]);
                int y = Integer.parseInt(partes[1]);
                ubicaciones.add(new Coordenada(x, y));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return ubicaciones;
    }

    public static ArrayList<Solicitud> Solicitudes(String archivo){
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))){
            String Archivo;
            while ((Archivo = br.readLine()) != null){
                String[] partes = Archivo.split(" ");
                int xi = Integer.parseInt(partes[0]);
                int yi = Integer.parseInt(partes[1]);
                int xf = Integer.parseInt(partes[3]);
                int yf = Integer.parseInt(partes[4]);
                int T = Integer.parseInt(partes[6]);
                Coordenada origen = new Coordenada(xi, yi);
                Coordenada destino = new Coordenada(xf, yf);
                solicitudes.add(new Solicitud(origen, destino, T));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return solicitudes;
    }

    public static Coordenada UberAleatorio(ArrayList<Coordenada> ubicaciones){
        if (ubicaciones.isEmpty()){
            return null;
        }
        Random random = new Random();
        int aux = random.nextInt(ubicaciones.size());
        Coordenada Uber = ubicaciones.get(aux);
        ubicaciones.remove(aux);
        return Uber;
    }

    public static void Guardar(ArrayList<String> Distancias, String horarios){
        String archivo = "C:\\RESULTADOSAR_" + horarios.toUpperCase() + ".txt";
        try (FileWriter writer = new FileWriter(archivo)){
            for (String resultado : Distancias){
                writer.write(resultado + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int calcularDistancia(Coordenada origen, Coordenada destino){
        int dx = Math.abs(origen.x - destino.x);
        int dy = Math.abs(origen.y - destino.y);
        return (int) Math.round(Math.sqrt(dx * dx + dy * dy));
    }

    public static void main(String[] args) {
        ArrayList<Coordenada> ubicacionesManana = Ubicaciones("C:\\manana.dat");
        ArrayList<Coordenada> ubicacionesTarde = Ubicaciones("C:\\tarde.dat");
        ArrayList<Coordenada> ubicacionesNoche = Ubicaciones("C:\\noche.dat");

        ArrayList<Solicitud> solicitudes1 = Solicitudes("C:\\requests_1.dat");
        ArrayList<Solicitud> solicitudes2 = Solicitudes("C:\\requests_2.dat");
        ArrayList<Solicitud> solicitudes3 = Solicitudes("C:\\requests_3.dat");


        Collections.shuffle(ubicacionesManana);
        Collections.shuffle(ubicacionesTarde);
        Collections.shuffle(ubicacionesNoche);

        ArrayList<String> DistanciasManana = TransformarSolicitudes(ubicacionesManana, solicitudes1);
        Guardar(DistanciasManana, "manana");

        ArrayList<String> DistanciasTarde = TransformarSolicitudes(ubicacionesTarde, solicitudes2);
        Guardar(DistanciasTarde, "tarde");

        ArrayList<String> DistanciasNoche = TransformarSolicitudes(ubicacionesNoche, solicitudes3);
        Guardar(DistanciasNoche, "noche");
    }
}
