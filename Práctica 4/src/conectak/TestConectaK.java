package conectak;

import jugadores.*;
import mundoadversario.Juego;

public class TestConectaK {

    public static void main(String[] args) {
        //entrenable();
        //aleatorio();
        //aleatorioHumano();
        //evaluarHumano();
        //evaluarTVHumano();
        //testVerEntrenamiento();
        testSiMismo();
    }

    public static void aleatorio() {
        ConectaK e = new ConectaK(3, 3, 3);
        Jugador j1 = new JugadorAleatorio<ConectaK>();
        Jugador j2 = new JugadorAleatorio<ConectaK>();
        Juego juego1 = new Juego(j1, j2, e);

        verPartida(juego1);
    }

    public static void aleatorioHumano() {
        ConectaK e = new ConectaK(3, 3, 3);
        Jugador j1 = new JugadorAleatorio<ConectaK>();
        Jugador j2 = new JugadorHumanoCK();
        Juego juego1 = new Juego(j1, j2, e);

        verPartida(juego1);
    }

    public static void evaluarHumano() {
        ConectaK e = new ConectaK(3, 3, 3);
        Jugador j1 = new JugadorEvaluar(new EvaluadorCK(3, 3, 3));
        Jugador j2 = new JugadorHumanoCK();
        Juego juego1 = new Juego(j1, j2, e);

        verPartida(juego1);
    }

    public static void evaluarTVHumano() {
        ConectaK e = new ConectaK(3, 3, 3);
        JugadorEvaluarTV<ConectaK> j1 = new JugadorEvaluarTV<>();


        //entrenamos
        double epsilon = 0.1;//probabilidad de exploración
        int nPartidas = 5000;
        Jugador ja = new JugadorAleatorio<>();
        System.out.println("Entrenando...");
        for (int i = 0; i < nPartidas; i++) {
            j1.aprendeTurno1(ja, e, epsilon);
        }
        System.out.println("OK");

        //jugamos
        Jugador j2 = new JugadorHumanoCK();
        Juego juego1 = new Juego<>(j1, j2, e);

        verPartida(juego1);
    }

    public static void verPartida(Juego juego) {
        int i = juego.jugarPartida(true);

        if (i == 0) {
            System.out.println("Empate.");
        } else if (i == 1) {
            System.out.println("Gana el primer jugador.");
        } else {
            System.out.println("Gana el segundo jugador.");
        }
    }

    public static void entrenable() {
        ConectaK e = new ConectaK(3, 3, 3);
        Jugador j1 = new JugadorAleatorio<ConectaK>();
        JugadorEvaluarLineal j2 = new JugadorEvaluarLineal<>(e);
        JugadorHumanoCK jh = new JugadorHumanoCK();

        Juego juego = new Juego<>(jh, j2, e);

        verPartida(juego);

        for (int i = 0; i < 3000; i++) {
            j2.aprendeTurno2(j1, e, 0.2);
        }

        juego = new Juego<>(jh, j2, e);
        verPartida(juego);
    }

    public static void testVerEntrenamiento() {
        System.out.println("-------------------------------APARTADO A----------------------------------------");
        System.out.println(" -------- \n Entrenamos c-3 3x3. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Segundo jugador \n --------");
        verEntramientoAleatorio(3, 3, 3, 0.1, 0.1, 10000, 100, 1000, false);
        System.out.println(" -------- \n Entrenamos c-3 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Segundo jugador \n --------");
        verEntramientoAleatorio(4, 4, 3, 0.1, 0.1, 10000, 100, 1000, false);
        System.out.println(" -------- \n Entrenamos c-4 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Segundo jugador \n --------");
        verEntramientoAleatorio(4, 4, 4, 0.1, 0.1, 10000, 100, 1000, false);

        System.out.println("-------------------------------APARTADO B----------------------------------------");
        System.out.println(" -------- \n Entrenamos c-3 3x3. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Primer jugador \n --------");
        verEntramientoAleatorio(3, 3, 3, 0.1, 0.1, 10000, 100, 1000, true);
        System.out.println(" -------- \n Entrenamos c-3 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Primer jugador \n --------");
        verEntramientoAleatorio(4, 4, 3, 0.1, 0.1, 10000, 100, 1000, true);
        System.out.println(" -------- \n Entrenamos c-4 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100: Primer jugador \n --------");
        verEntramientoAleatorio(4, 4, 4, 0.1, 0.1, 10000, 100, 1000, true);
    }

    // primEv es para seleccionar quien juega primero
    public static void verEntramientoAleatorio(int f, int c, int k, double alfa, double pexp, int partidas, int cada, int pruebas, boolean primEv) {
        JugadorAleatorio<ConectaK> jugadorAleatorio = new JugadorAleatorio<>();
        JugadorEvaluarTV<ConectaK> jugadorEvaluarTV = new JugadorEvaluarTV<>(alfa);
        ConectaK conectaK = new ConectaK(f, c, k);

        for (int i = 0; i < partidas; i++) {
            if (i % cada == 0 && i != 0) {
                float g1 = 0;
                float e = 0;
                float g2 = 0;

                for (int j = 0; j < pruebas; j++) {
                    int part = !primEv ? new Juego<>(jugadorAleatorio, jugadorEvaluarTV, conectaK).jugarPartida(false) : new Juego<>(jugadorEvaluarTV, jugadorAleatorio, conectaK).jugarPartida(false);

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
                jugadorEvaluarTV.aprendeTurno2(jugadorAleatorio, conectaK, pexp);
            } else {
                jugadorEvaluarTV.aprendeTurno1(jugadorAleatorio, conectaK, pexp);
            }
        }
    }

    public static void entranamiento(int f, int c, int k, double alfa, double pexp, int partidas, int cada, int pruebas) {
        ConectaK conectaK = new ConectaK(f, c, k);
        JugadorEvaluarTV<ConectaK> jugadorEvaluarTV_1 = new JugadorEvaluarTV<>(alfa);
        JugadorEvaluarTV<ConectaK> jugadorEvaluarTV_2 = new JugadorEvaluarTV<>(alfa);
        JugadorEvaluar<ConectaK> jugadorEvaluar_ref = new JugadorEvaluar<>(new EvaluadorCK(f, c, k));

        for (int i = 0; i < 2 * partidas; i++) {
            if (i % cada == 0 && i != 0) {
                float g1 = 0;
                float e = 0;
                float g2 = 0;

                for (int j = 0; j < pruebas; j++) {
                    int part = new Juego<>(jugadorEvaluarTV_1, jugadorEvaluar_ref, conectaK).jugarPartida(false);

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

            if (i % 2 == 0) {
                jugadorEvaluarTV_1.aprendeTurno1(jugadorEvaluarTV_2, conectaK, pexp);
            } else {
                jugadorEvaluarTV_2.aprendeTurno2(jugadorEvaluarTV_1, conectaK, pexp);
            }
        }
    }

    public static void testSiMismo() {
        int partidas = 10000;

        System.out.println("Entrenamiento entre dos Jug.EvTV y prueba de rendimiento vs no entrenado");
        System.out.println(" -------- \n Entrenamos c-3 3x3. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100 \n --------");
        int cada = 100;
        entranamiento(3, 3, 3, 0.1, 0.1, partidas, cada, 1000);
        System.out.println(" -------- \n Entrenamos c-3 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100 \n --------");
        entranamiento(4, 4, 3, 0.1, 0.1, partidas, cada, 1000);
        System.out.println(" -------- \n Entrenamos c-4 4x4. Alfa: 0.1 Pexp: 0.1 Partidas: 10000 Pruebas: 1000 cada 100 \n --------");
        entranamiento(4, 4, 4, 0.1, 0.1, partidas, cada, 1000);
    }
}
