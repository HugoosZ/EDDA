
import java.util.Scanner;
import java.util.Stack;
public class Embeces {

    public void Rellenar () {
        Scanner Input = new Scanner(System.in);
        int n = Input.nextInt();
        int[] arreglo = new int[n];
        for (int i = 0; i < n; i++) {

            Scanner sc = new Scanner(System.in);
            arreglo[i] = sc.nextInt();
        }
        Costo(arreglo);
    }
    public void Costo(int[] arreglo){
        Stack<Integer> Pila = new Stack<>();

        int costo = 0;
        int MinGlobal = arreglo[0];
        for(int i= 0; i < arreglo.length;i++){
            if(i==arreglo.length-1){ //este if esta porque en teoria el ultimo elemento siempre es el mayor
                break; //si tuvieramos 4 2 1 3 en el i = 0 queda 1 2 4 3 y en el i = 1 queda 1 2 3 4
                        // en el i = 2 queda 1 2 3 4 y en el i = 3 queda 1 2 3 4 y compara el 4 con el 4 lo cual no tiene sentido
                        // entonces ese costo final no se debe contar porque ya esta ordenado :)
            }
            int min = arreglo[i];
            int posMin = i;
            for(int j = i  ; j< arreglo.length ; j ++){
                if(min > arreglo[j]){
                    min = arreglo[j];
                    posMin= j;
                }
            }

            for(int j = i ; j < posMin  + 1; j++){
                Pila.push(arreglo[j]);
            }
            for(int j = i ; j < posMin + 1; j++){
                arreglo[j] = Pila.pop();
            }
            costo += posMin - i + 1 ;
        }
        System.out.println(costo);
    }

    public void ArregloFinal(){
        Rellenar();
    }
    public static void main(String[] args) {
        Embeces Uno = new Embeces();
        Uno.ArregloFinal();
    }
}

