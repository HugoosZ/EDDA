//Alonso Vera, Hugo Rojas 
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int N = stdin.nextInt();
        Luchador[] luchadores = new Luchador[N];
        int Ganador = 0;
        for(int i=0;i<N;i++)
        {
            int V = stdin.nextInt();
            int R = stdin.nextInt();
            int D = stdin.nextInt();
            int A = stdin.nextInt();
            int S = stdin.nextInt();
            luchadores[i]= new Luchador();
            luchadores[i].Asignacion(V,R,D,A,S);
        }

        for (int i = 0; i < N - 1; i++) {

            int vida1 = luchadores[i].vida;
            int vida2 = luchadores[i + 1].vida;
            int resistencia1 = luchadores[i].resistencia;
            int resistencia2 = luchadores[i + 1].resistencia;
            int ataque1 = luchadores[i].ataque;
            int ataque2 = luchadores[i + 1].ataque;
            int durabilidad1 = luchadores[i].durabilidad;
            int durabilidad2 = luchadores[i + 1].durabilidad;
            int superpatada1 = luchadores[i].superpatada;
            int superpatada2 = luchadores[i + 1].superpatada;

            while (luchadores[i].vida > 0 && luchadores[i + 1].vida > 0) {
                if(superpatada1==vida2){
                    Ganador = i;
                    vida2=0;
                    break;
                }
                else{
                    if(durabilidad2>0){
                        ataque1 -= resistencia2;
                        vida2 -= ataque1;
                        durabilidad2--;
                    }
                    else {
                        vida2 -= ataque1;
                    }
                    if(vida2 <= 0)
                    {
                        Ganador = i;
                        break;
                    }
                }
                if(superpatada2==vida1){
                    Ganador = i+1;
                    vida1=0;
                    break;
                }
                else{
                    if(durabilidad1>0){
                        ataque2 -= resistencia1;
                        vida1 -= ataque2;
                        durabilidad1--;
                    }
                    else {
                        vida1 -= ataque2;
                    }
                    if(vida1 <= 0)
                    {
                        Ganador = i+1;
                        break;
                    }
                }
            }
        }
        if(Ganador == 0)
        {
            System.out.println("gane yo :D");
        }else
        {
            System.out.println(Ganador+1);
        }

    }

}
class Luchador
{
    int vida,resistencia,durabilidad,ataque,superpatada;
    public void Asignacion(int V,int R,int D,int A ,int S)
    {
        this.vida = V;
        this.resistencia = R;
        this.durabilidad = D;
        this.ataque = A;
        this.superpatada = S;
    }
}