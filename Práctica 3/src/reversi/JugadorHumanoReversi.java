package reversi;

import javafx.util.Pair;
import jugadores.Jugador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author José Miguel Horcas Aguilera, Lorenzo Mandow
 * @version 21-10-2013
 */
public class JugadorHumanoReversi implements Jugador<Reversi> {

    @Override
    public Reversi mueve(Reversi e) {
        Pair<Integer, Integer> c;

        TableroReversi tableroReversi;
        Reversi nuevo;
        do {
            c = pedirMovimiento();
            tableroReversi = ((TableroReversi) e.tablero()).clone();
            tableroReversi.soltarFicha(c.getValue(), c.getKey(), e.fichaActual(), null);
            nuevo = new Reversi(!e.turno1(), new TableroReversi(tableroReversi), e.agotado());

        } while (!((TableroReversi) e.tablero()).movimientoValido(c.getValue(), c.getKey(), e.fichaActual()).contains(true) && !e.agotado());


        return nuevo;
    }

    /**
     * Pide una columna sobre la que soltar una ficha.
     *
     * @return Columna.
     */
    private Pair<Integer, Integer> pedirMovimiento() {
        Pair<Integer, Integer> mov = null;

        System.out.println("Donde soltamos la ficha (columna, fila)?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String linea = br.readLine();
            try (Scanner sc = new Scanner(linea)) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                mov = new Pair<>(x, y);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return mov;
    }
}
