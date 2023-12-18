import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Cliente {

    public static void seeLaterMenu( BufferedReader br, MaxHeap pqMax,MinHeap pqMin) throws IOException { //Completar----------------------------------------------------------------
        if(pqMax.getSize() == 0){
            System.out.println("La lista está vacía");
            return;
        }

        boolean maxOrder = true;
        boolean exit = false;

        Video actual;
        actual = pqMax.getTop();

        while(!exit){
            System.out.println("Reproduciendo:");
            actual.reproduce();
            System.out.println("Ingrese una opción:");
            System.out.println("1: para siguiente video");
            System.out.println("2: para cambiar el orden de prioridad");
            System.out.println("3: para regresar al menú principal");

            switch (br.readLine()) {
                case "1" -> {
                    if (pqMax.getSize() == 0) {
                        System.out.println("La lista está vacía");
                        return;
                    }
                    System.out.println("Siguiente vídeo...");
                    actual = actual.getNext();
                    actual.reproduce();
                }
                case "2" -> {
                    System.out.println("Cambiando orden...");
                    maxOrder= !maxOrder;
                    if (maxOrder) {
                        actual = pqMax.getTop();
                    } else {
                        actual = pqMin.getTop();
                    }
                }
                case "3" -> {
                    System.out.println("Volviendo a menú inicial");
                    exit = true;
                }
                default -> System.out.println("Ingrese opción válida");
            }
        }
    }



    public static void main(String[] args) {
        System.out.println("Cargando datos...");
        Plataforma platform = new Plataforma();
        String file = "C:\\YoutubeDTSV2.csv";
        platform.insertFromFile(file);
        Video actualVideo = platform.begin();
        MaxHeap pqMax = new MaxHeap(1000);
        MinHeap pqMin = new MinHeap(1000);


        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))){
            boolean exit = false;
            while (!exit){
                System.out.println("reproduciendo:");
                actualVideo.reproduce();
                System.out.println("Ingrese una opción:");
                System.out.println("1: para siguiente video");
                System.out.println("2: para buscar una canción por el ID");
                System.out.println("3: para invertir el orden de la lista");
                System.out.println("4: para imprimir la lista restante");
                System.out.println("5: enviar a lista de 'ver más tarde'");
                System.out.println("6: ir a la lista de reproducción 'ver más tarde'");
                System.out.println("7: para salir");
                System.out.println();
                switch (br.readLine()) {
                    case "1" -> {
                        if (actualVideo.getNext() != null) {
                            System.out.println("Siguiente video...");
                            actualVideo = actualVideo.getNext();
                        } else {
                            System.out.println("No quedan más videos");
                        }
                    }
                    case "2" -> {
                        System.out.println("Ingrese id del vídeo");
                        Video targetVideo = platform.iterativeSearch(platform.begin(), br.readLine());
                        if (targetVideo != null) {
                            actualVideo = targetVideo;
                            System.out.println("Vídeo encontrado");
                            actualVideo.reproduce();
                        } else {
                            System.out.println("Vídeo no encontrado");
                        }
                    }
                    case "3" -> {
                        System.out.println("Invirtiendo lista");
                        platform.iterativeReverse(platform.begin());
                        actualVideo = platform.begin();
                    }
                    case "4" -> platform.iterativePrint(actualVideo);
                    case "5" -> {
                        System.out.println("Enviar a lista de 'ver más tarde'");
                        pqMax.insert(actualVideo);
                        pqMin.insert(actualVideo);
                    }
                    case "6" -> {
                        System.out.println("Ir a la lista de reproducción 'ver más tarde'");
                        seeLaterMenu(br, pqMax,pqMin);
                    }
                    case "8" -> exit = true;
                    default -> System.out.println("Ingrese opción válida");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
