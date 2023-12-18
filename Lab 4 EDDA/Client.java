import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Client {
    //No modificar
    public static boolean maxOrder = true;
    public static MaxHeap pqMax = new MaxHeap(1000);
    public static MinHeap pqMin = new MinHeap(1000);

    //No modificar
    public static void seeLaterMenu( BufferedReader br) throws IOException {
        if(pqMax.getSize() == 0){
            System.out.println("La lista está vacía");
            return;
        }
        boolean exit = false;
        Video actual;
        if (maxOrder) {
            actual = pqMax.getTop();
            System.out.println("El orden actual es descendente");
        } else {
            actual = pqMin.getTop();
            System.out.println("El orden actual es ascendiente");
        }
        actual.reproduce();
        while(!exit){
            System.out.println("Ingrese una opción:");
            System.out.println("1: para siguiente video");
            System.out.println("2: para cambiar el orden de prioridad");
            System.out.println("3: para regresar al menú principal");

            switch (br.readLine()) {
                case "1" -> {
                    if (maxOrder) {
                        pqMax.delete();
                        pqMin.deleteMax();
                        if(pqMax.getSize() == 0){
                            System.out.println("La lista está vacía");
                            return;
                        }
                        actual =  pqMax.getTop();
                    } else {
                        pqMin.delete();
                        pqMax.deleteMin();
                        if(pqMax.getSize() == 0){
                            System.out.println("La lista está vacía");
                            return;
                        }
                        actual =  pqMin.getTop();
                    }
                    System.out.println("Reproduciendo:");
                    actual.reproduce();
                }
                case "2" -> {
                    System.out.println("Cambiando orden...");
                    maxOrder = !maxOrder;
                    if (maxOrder) {
                        actual = pqMax.getTop();
                        System.out.println("El orden actual es descendente");
                    } else {
                        actual = pqMin.getTop();
                        System.out.println("El orden actual es ascendiente");
                    }
                    System.out.println("Reproduciendo:");
                    actual.reproduce();
                }
                case "3" -> {
                    System.out.println("Volviendo al menú inicial");
                    exit = true;
                }
                default -> System.out.println("Ingrese opción válida");
            }
        }
    }

    //No modificar
    public static void channelVideosMenu(LinkedList linkedList, BufferedReader br) throws IOException {
        Video actualVideo = linkedList.begin();
        if(actualVideo != null){
            actualVideo.reproduce();
        }
            boolean exit = false;
            while (!exit) {
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
                            System.out.println("Reproduciendo:");
                            actualVideo.reproduce();
                        } else {
                            System.out.println("No quedan más videos");
                        }
                    }
                    case "2" -> {
                        System.out.println("Ingrese id del vídeo");
                        Video targetVideo = linkedList.iterativeSearch(linkedList.begin(), br.readLine());
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
                        linkedList.recursiveReverse(linkedList.begin());
                        actualVideo = linkedList.begin();
                    }
                    case "4" -> linkedList.recursivePrint(actualVideo);
                    case "5" -> {
                        System.out.println("Enviar a lista de 'ver más tarde' vídeo " + actualVideo.getVideoTitle());
                        pqMax.insert(actualVideo);
                        pqMin.insert(actualVideo);
                    }
                    case "6" -> {
                        System.out.println("Ir a la lista de reproducción 'ver más tarde'");
                        seeLaterMenu(br);
                    }
                    case "7" -> exit = true;
                    default -> System.out.println("Ingrese opción válida");
            }
        }
    }

    //Incorporar lógica de eliminación y busqueda
    public static void main(String[] args) {
        System.out.println("Cargando datos...");
        String file = "C:\\YoutubeDTSV2.csv";
        AVLTree tree = new AVLTree(file);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))){
            boolean exit = false;
            while (!exit){
                System.out.println("1: Para ver canales (in Order inverse)");
                System.out.println("2: Seleccionar canal (find)");
                System.out.println("3: Eliminar canal (delete)");
                System.out.println("4: para salir");
                System.out.println();
                switch (br.readLine()) {
                    case "1" -> {
                        tree.inOrderInv(tree.getRoot());
                    }
                    //Completar caso 2
                    case "2" -> {
                        System.out.println("Ingrese nombre del canal a buscar");

                        String targetChannel = br.readLine();
                        AVLTree.Node target = tree.find(targetChannel);
                        if(target != null){
                            System.out.println("Canal encontrado");
                            channelVideosMenu(target.videos, br);
                        } else {
                            System.out.println("Canal no encontrado");
                        }

                    }
                    //Completar caso 3
                    case "3" -> {
                        System.out.println("Ingrese nombre del canal a eliminar");
                        String targetChannel = br.readLine();
                        AVLTree.Node target = tree.find(targetChannel);
                        if(target != null){
                            tree.delete(targetChannel);
                            System.out.println("Canal eliminado");
                        } else {
                            System.out.println("Canal no encontrado");
                        }
                    }
                    case "4" -> exit = true;
                    default -> System.out.println("Ingrese opción válida");
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

