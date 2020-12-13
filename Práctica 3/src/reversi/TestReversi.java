package reversi;

import jugadores.JugadorAleatorio;
import jugadores.JugadorEvaluar;
import mundoadversario.Juego;

import java.util.List;

public class TestReversi {
    private static void testTableroReversi() {
        TableroReversi tableroReversi = new TableroReversi(8, 8);
        tableroReversi.ver();
        tableroReversi.soltarFicha(1, 1, Reversi.ficha1, null);
        tableroReversi.ver();
        tableroReversi.soltarFicha(2, 2, Reversi.ficha1, null);
        tableroReversi.ver();
        tableroReversi.soltarFicha(3, 3, Reversi.ficha2, null);
        tableroReversi.ver();
        tableroReversi.soltarFicha(0, 0, Reversi.ficha2, null);
        tableroReversi.ver();
    }

    private static void testSucesores() {
        TableroReversi tableroReversi = new TableroReversi(4, 4);
        int[][] test = {{-1, 1, 1, 1},
                {0, -1, 1, 1},
                {-1, 1, 1, 1},
                {0, 1, 0, -1}};


        Reversi reversi = new Reversi(test, false, false);
        reversi.ver();

        List<Reversi> lista = reversi.calculaSucesores();

        for (Reversi reversi1 : lista) {
            reversi1.ver();
            System.out.println(reversi1.hashCode());
            //System.out.println(reversi1.ultimoMov.toString());
        }

        System.out.println(lista.size());


    }

    private static void testJuegaciones() {
        System.out.println("Test aleatorio vs humano");

        JugadorAleatorio<Reversi> jugadorAleatorio = new JugadorAleatorio<>();
        JugadorHumanoReversi jugadorHumanoReversi = new JugadorHumanoReversi();

        new Juego<>(jugadorAleatorio, jugadorHumanoReversi, new Reversi(false, new TableroReversi(8, 8), false)).jugarPartida(true);

    }

    private static void testJuegacionesAleatorias() {
        System.out.println("Test aleatorio vs aleatorio");
        new Juego<>(new JugadorAleatorio(), new JugadorAleatorio(), new Reversi(true, new TableroReversi(8, 8), false)).jugarPartida(true);
    }

    private static void testJuegacionesEvaluadas() {
        System.out.println("Test jugador aleatorio vs evaluado");
        JugadorAleatorio<Reversi> jugadorAleatorio = new JugadorAleatorio<>();
        EvaluadorReversi evaluadorReversi = new EvaluadorReversi(8, 8);
        JugadorEvaluar<Reversi> jugadorEvaluar = new JugadorEvaluar<>(evaluadorReversi);
        System.out.println("Aleatorio empieza");
        new Juego<>(jugadorAleatorio, jugadorEvaluar, new Reversi(true, new TableroReversi(8, 8), false)).jugarPartida(true);
        //System.out.println("Evaluado empieza");
        //new Juego<>(jugadorAleatorio, jugadorEvaluar, new Reversi(false, new TableroReversi(8, 8), false)).juega(1000);

        //System.out.println("Evaluado vs evaluado");
        //new Juego<>(jugadorEvaluar, jugadorEvaluar, new Reversi(false, new TableroReversi(8, 8), false)).juega(1000);
    }

    public static void main(String[] args) {
        //testTableroReversi();
        //testSucesores();
        //testJuegaciones();
        //testJuegacionesAleatorias();
        testJuegacionesEvaluadas();
    }
}
