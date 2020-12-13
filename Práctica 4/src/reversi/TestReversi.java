package reversi;

import jugadores.JugadorAleatorio;
import jugadores.JugadorEvaluar;
import jugadores.JugadorEvaluarLineal;
import mundoadversario.Juego;
import java.util.Arrays;

public class TestReversi {
    public static void main(String[] args) {
        testVerEntrenamiento();
        //testCodifica();
    }

    public static void verEntramientoAleatorioNorvig(int f, int c, double alfa, double pexp, int partidas, int cada, int pruebas, boolean primEv) {
        JugadorEvaluar<Reversi> jugadorEvaluar = new JugadorEvaluar<>(new EvaluadorReversi(f, c));
        Reversi reversi = new Reversi(true, new TableroReversi(f, c), false);
        JugadorEvaluarLineal<Reversi> jugadorEvaluarLineal = new JugadorEvaluarLineal<>(reversi, alfa);

        for (int i = 0; i < partidas; i++) {
            if (i % cada == 0 && i != 0) {
                float g1 = 0;
                float e = 0;
                float g2 = 0;

                for (int j = 0; j < pruebas; j++) {
                    int part = !primEv ? new Juego<>(jugadorEvaluar, jugadorEvaluarLineal, reversi).jugarPartida(false) : new Juego<>(jugadorEvaluarLineal, jugadorEvaluar, reversi).jugarPartida(false);

                    if (part > 0) {
                        g1++;
                    } else if (part < 0) {
                        g2++;
                    } else {
                        e++;
                    }
                }
                System.out.println("Gana 1: " + (int) ((g1 / pruebas) * 100) + "% (" + (int) g1 + ")\t\t\t Empate: " + (int) ((e / pruebas) * 100) + "% (" + (int) e + ")\t\t\t Gana 2: " + (int) ((g2 / pruebas) * 100) + "% (" + (int) g2 + ")");
            }

            if (!primEv) {
                jugadorEvaluarLineal.aprendeTurno2(jugadorEvaluar, reversi, pexp);
            } else {
                jugadorEvaluarLineal.aprendeTurno1(jugadorEvaluar, reversi, pexp);
            }
        }
    }

    public static void verEntramientoAleatorio(int f, int c, double alfa, double pexp, int partidas, int cada, int pruebas, boolean primEv) {
        JugadorAleatorio<Reversi> jugadorAleatorio = new JugadorAleatorio<>();
        Reversi reversi = new Reversi(true, new TableroReversi(f, c), false);
        JugadorEvaluarLineal<Reversi> jugadorEvaluarLineal = new JugadorEvaluarLineal<>(reversi, alfa);

        for (int i = 0; i < partidas; i++) {
            if (i % cada == 0 && i != 0) {
                float g1 = 0;
                float e = 0;
                float g2 = 0;

                for (int j = 0; j < pruebas; j++) {
                    int part = !primEv ? new Juego<>(jugadorAleatorio, jugadorEvaluarLineal, reversi).jugarPartida(false) : new Juego<>(jugadorEvaluarLineal, jugadorAleatorio, reversi).jugarPartida(false);

                    if (part > 0) {
                        g1++;
                    } else if (part < 0) {
                        g2++;
                    } else {
                        e++;
                    }
                }
                System.out.println("Gana 1: " + (int) ((g1 / pruebas) * 100) + "% (" + (int) g1 + ")\t\t\t Empate: " + (int) ((e / pruebas) * 100) + "% (" + (int) e + ")\t\t\t Gana 2: " + (int) ((g2 / pruebas) * 100) + "% (" + (int) g2 + ")");
            }

            if (!primEv) {
                jugadorEvaluarLineal.aprendeTurno2(jugadorAleatorio, reversi, pexp);
            } else {
                jugadorEvaluarLineal.aprendeTurno1(jugadorAleatorio, reversi, pexp);
            }
        }
    }

    public static void testVerEntrenamiento() {
        //System.out.println("-------------------------------APARTADO A----------------------------------------");
        //System.out.println(" -------- \n Entrenamos Reversi 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Segundo jugador \n --------");
        //verEntramientoAleatorio(4, 4, 0.1, 0.01, 10000, 100, 1000, false);
//
        //System.out.println("-------------------------------APARTADO B----------------------------------------");
        //System.out.println(" -------- \n Entrenamos Reversi 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Primer Jugador \n --------");
        //verEntramientoAleatorio(4, 4, 0.1, 0.01, 10000, 100, 1000, true);

        //System.out.println("-------------------------------APARTADO C----------------------------------------");
        //System.out.println(" -------- \n Entrenamos Reversi 8x8. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Segundo Jugador \n --------");
        //verEntramientoAleatorioNorvig(8, 8, 0.001, 0.2, 10000, 100, 100, false);

        System.out.println("-------------------------------APARTADO D----------------------------------------");
        System.out.println(" -------- \n Entrenamos Reversi 8x8. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Primer Jugador \n --------");
        verEntramientoAleatorioNorvig(8, 8, 0.001, 0.2, 10000, 100, 100, true);

    }

    public static void testCodifica(){
        Reversi reversi = new Reversi(true, new TableroReversi(4,4), false);
        reversi.ver();
        System.out.println(Arrays.toString(reversi.codifica()));
    }

}
