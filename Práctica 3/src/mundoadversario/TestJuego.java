package mundoadversario;

import conectak.ConectaK;
import conectak.EvaluadorCK;
import jugadores.*;

public class TestJuego {
    public static void main(String[] args) {
        System.out.println("-------------------------------EJERCICIO 1----------------------------------");

        JugadorAleatorio jugadorAleatorio = new JugadorAleatorio();
        EvaluadorCK evaluadorCK = new EvaluadorCK(3, 3, 3);
        ConectaK conectaK = new ConectaK(3, 3, 3);

        JugadorEvaluar<ConectaK> jugadorEvaluar = new JugadorEvaluar<>(evaluadorCK);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBeta2 = new JugadorAlfaBeta<>(evaluadorCK, 2);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBeta3 = new JugadorAlfaBeta<>(evaluadorCK, 3);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBeta4 = new JugadorAlfaBeta<>(evaluadorCK, 4);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBeta5 = new JugadorAlfaBeta<>(evaluadorCK, 5);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBeta6 = new JugadorAlfaBeta<>(evaluadorCK, 6);
        int n = 1000;

        System.out.println("\t\t\t\t\t\t\t Gana 1 \t Empate \t Gana 2");
        System.out.println("-----------------------------------------------------------");
        System.out.print("Aleatorio vs Aleatorio     ");

        new Juego<>(jugadorAleatorio, jugadorAleatorio, conectaK).juega(n);
        System.out.print("Aleatorio vs Evaluar       ");
        new Juego<>(jugadorAleatorio, jugadorEvaluar, conectaK).juega(n);
        System.out.print("Aleatorio vs Alfabeta (p=2)");
        new Juego<>(jugadorAleatorio, jugadorAlfaBeta2, conectaK).juega(n);
        System.out.print("Aleatorio vs Alfabeta (p=3)");
        new Juego<>(jugadorAleatorio, jugadorAlfaBeta3, conectaK).juega(n);
        System.out.print("Aleatorio vs Alfabeta (p=4)");
        new Juego<>(jugadorAleatorio, jugadorAlfaBeta4, conectaK).juega(n);
        System.out.print("Aleatorio vs Alfabeta (p=5)");
        new Juego<>(jugadorAleatorio, jugadorAlfaBeta5, conectaK).juega(n);
        System.out.print("Aleatorio vs Alfabeta (p=6)");
        new Juego<>(jugadorAleatorio, jugadorAlfaBeta6, conectaK).juega(n);

        System.out.println("\n-------------------------------EJERCICIO 2---------------------------------");

        System.out.println("\nProfundidad \t Minimax \t AB Aleatorio \t AB Ordenado");
        System.out.println("-----------------------------------------------------------");
        EvaluadorCK evaluadorCK2 = new EvaluadorCK(6, 7, 4);
        ConectaK conectaK1 = new ConectaK(6, 7, 4);

        // Profundidad 2
        JugadorMinimax<ConectaK> jugadorMinimax = new JugadorMinimax<>(evaluadorCK2, 2);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBetaAleatorio = new JugadorAlfaBeta<>(evaluadorCK2, 2);
        JugadorAlfaBeta<ConectaK> jugadorAlfaBetaOrdenado = new JugadorAlfaBeta<>(evaluadorCK2, 2, true);
        jugadorMinimax.mueve(conectaK1);
        jugadorAlfaBetaAleatorio.mueve(conectaK1);
        jugadorAlfaBetaOrdenado.mueve(conectaK1);
        System.out.println("2 \t\t\t\t\t" + jugadorMinimax.getnNodos() + "\t\t\t" + jugadorAlfaBetaAleatorio.getnNodos() + "\t\t\t\t" + jugadorAlfaBetaOrdenado.getnNodos());

        // Profundidad 3
        jugadorMinimax = new JugadorMinimax<>(evaluadorCK2, 3);
        jugadorAlfaBetaAleatorio = new JugadorAlfaBeta<>(evaluadorCK2, 3);
        jugadorAlfaBetaOrdenado = new JugadorAlfaBeta<>(evaluadorCK2, 3, true);
        jugadorAlfaBetaOrdenado.mueve(conectaK1);
        jugadorMinimax.mueve(conectaK1);
        jugadorAlfaBetaAleatorio.mueve(conectaK1);
        System.out.println("3 \t\t\t\t\t" + jugadorMinimax.getnNodos() + "\t\t\t" + jugadorAlfaBetaAleatorio.getnNodos() + "\t\t\t\t" + jugadorAlfaBetaOrdenado.getnNodos());

        // Profundidad 4
        jugadorMinimax = new JugadorMinimax<>(evaluadorCK2, 4);
        jugadorAlfaBetaAleatorio = new JugadorAlfaBeta<>(evaluadorCK2, 4);
        jugadorAlfaBetaOrdenado = new JugadorAlfaBeta<>(evaluadorCK2, 4, true);
        jugadorAlfaBetaOrdenado.mueve(conectaK1);
        jugadorMinimax.mueve(conectaK1);
        jugadorAlfaBetaAleatorio.mueve(conectaK1);
        System.out.println("4 \t\t\t\t\t" + jugadorMinimax.getnNodos() + "\t\t" + jugadorAlfaBetaAleatorio.getnNodos() + "\t\t\t\t" + jugadorAlfaBetaOrdenado.getnNodos());

        // Profundidad 5
        jugadorMinimax = new JugadorMinimax<>(evaluadorCK2, 5);
        jugadorAlfaBetaAleatorio = new JugadorAlfaBeta<>(evaluadorCK2, 5);
        jugadorAlfaBetaOrdenado = new JugadorAlfaBeta<>(evaluadorCK2, 5, true);
        jugadorAlfaBetaOrdenado.mueve(conectaK1);
        jugadorMinimax.mueve(conectaK1);
        jugadorAlfaBetaAleatorio.mueve(conectaK1);
        System.out.println("5 \t\t\t\t\t" + jugadorMinimax.getnNodos() + "\t\t" + jugadorAlfaBetaAleatorio.getnNodos() + "\t\t\t" + jugadorAlfaBetaOrdenado.getnNodos());

        // Profundidad 6
        jugadorMinimax = new JugadorMinimax<>(evaluadorCK2, 6);
        jugadorAlfaBetaAleatorio = new JugadorAlfaBeta<>(evaluadorCK2, 6);
        jugadorAlfaBetaOrdenado = new JugadorAlfaBeta<>(evaluadorCK2, 6, true);
        jugadorAlfaBetaOrdenado.mueve(conectaK1);
        jugadorMinimax.mueve(conectaK1);
        jugadorAlfaBetaAleatorio.mueve(conectaK1);
        System.out.println("6 \t\t\t\t\t" + jugadorMinimax.getnNodos() + "\t\t" + jugadorAlfaBetaAleatorio.getnNodos() + "\t\t\t" + jugadorAlfaBetaOrdenado.getnNodos());
    }
}
