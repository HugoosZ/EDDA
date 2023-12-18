//Alonso Vera, Hugo Rojas
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int N = stdin.nextInt();
        int M = stdin.nextInt();
        int S = stdin.nextInt();
        int[] arreglo = new int[N];
        for (int i = 0; i < N; i++) {
            arreglo[i] = stdin.nextInt();
        }
        int[] subarreglo = SubarregloS(arreglo, M, S, N);
        for (int i = 0; i < S; i++) {
            System.out.print(subarreglo[i] + " ");
        }
        System.out.println();
    }

    public static int[] SubarregloS(int[] arreglo, int M, int S, int N) {
        int[] subarreglo = new int[S];
        if (S == 0) {
            return subarreglo;
        }
        if (M >= N) {
            M %= N;
        }
        subarreglo[0] = arreglo[M];
        int[] temp = SubarregloS(arreglo, M + 1, S - 1, N);
        for (int i = 0; i < S - 1; i++) {
            subarreglo[i + 1] = temp[i];
        }
        return subarreglo;
    }
}