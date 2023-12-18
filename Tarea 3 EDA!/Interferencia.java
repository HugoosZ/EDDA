import java.util.Scanner;
public class Interferencia {


    public static int MaxSum(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = (left + right) / 2;
        int SumaIzq = arr[mid];
        int SumaDer = arr[mid+1]; //Inicializamos las variables con un valor chiquitito
        int suma = 0;
        for (int i = mid; i >= left; i--) {//Obtenemos la suma de los valores de la izquierda
            suma += arr[i];
            if (suma > SumaIzq) {
                SumaIzq = suma;
            }
        }
        for (int i = mid; i >= left; i--) {
            suma += arr[i];
            if (suma > SumaIzq) {
                SumaIzq = suma;
            }
        }
        suma = 0;
        for (int i = mid + 1; i <= right; i++) {//Obtenemos la suma de los valores de la derecha
            suma += arr[i];
            if(suma > SumaDer){
                SumaDer = suma;
            }
        }
        int SumaAmbos = SumaIzq + SumaDer; //Sumamos las dos sumas
        int SumaIzqChica = MaxSum(arr, left, mid); //Obtenemos la suma de la izquierda pero del subarreglo
        int SumaDerChica = MaxSum(arr, mid + 1, right);//Obtenemos la suma de la derecha pero del subarreglo
        return Math.max(SumaAmbos, Math.max(SumaIzqChica, SumaDerChica)); //Obtenemos el máximo de las tres sumas
    }
    public static void main(String[] args) {
        Scanner z = new Scanner(System.in);
        int n = z.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = z.nextInt();
        }
        int max = MaxSum(arr, 0, arr.length - 1);
        System.out.println(max);
    }
}











