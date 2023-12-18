//Alonso Vera, Hugo Rojas
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner stdin = new Scanner (System.in);
        int N = stdin.nextInt();
        Participante[] torneo = new Participante [N];
        for (int i = 0; i < N; i++)
        {
            String nombre = stdin.next();
            int poder = stdin.nextInt ();
            torneo[i] = new Participante (nombre, poder);
        }
        Arrays.sort(torneo,new Ordenador());
        for (int i = 0; i < N; i++)
        {
            System.out.println(torneo[i].Nombre + " " + torneo[i].Poder);
        }
    }
}
class Participante
{
    String Nombre;
    int Poder;
    Participante (String nombre, int poder)
    {
        this.Nombre = nombre;
        this.Poder = poder;
    }
}
class Ordenador implements Comparator<Participante> {
    public int compare(Participante A, Participante B){
        return A.Poder-B.Poder;
    }

}