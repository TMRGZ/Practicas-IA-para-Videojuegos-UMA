package reversi;

import mundoadversario.EstadoJuegoAprox;
import mundosolitario.OverrideHashCode;

import java.util.*;

public class Reversi extends OverrideHashCode implements EstadoJuegoAprox<Reversi> {
    private boolean turno1;
    private boolean anteriorPasa;
    private TableroReversi tableroReversi;
    private int[][] tablero;

    public static Ficha<Integer> ficha1 = new Ficha<>(1);    // Ficha del jugador 1 (el que empieza el juego).
    public static Ficha<Integer> ficha2 = new Ficha<>(-1);    // Ficha del jugador 2.

    public Reversi(int[][] tablero, Boolean turno1, Boolean anteriorPasa) {
        this.anteriorPasa = anteriorPasa;
        this.turno1 = turno1;
        this.tablero = tablero;

        tableroReversi = new TableroReversi(tablero.length, tablero[0].length);

        for (int i = 0; i < tableroReversi.nColumnas(); i++) {
            for (int j = 0; j < tableroReversi.nFilas(); j++) {
                tableroReversi.ponerFicha(i, j, new Ficha<>(tablero[i][j]));
            }
        }
    }

    public Reversi(boolean turno1, TableroReversi tableroReversi, boolean anteriorPasa) {
        this.turno1 = turno1;
        this.tableroReversi = tableroReversi;
        this.anteriorPasa = anteriorPasa;
    }

    @Override
    public List<Reversi> calculaSucesores() {
        Set<Reversi> list = new HashSet<>();

        for (int i = 0; i < tableroReversi.nFilas(); i++) {
            for (int j = 0; j < tableroReversi.nColumnas(); j++) {
                if (tableroReversi.contenido(i, j).equals(fichaOtro()))
                    list.addAll(nSucesor(i, j));
            }
        }

        if (list.isEmpty()) {
            list.add(new Reversi(!turno1, tableroReversi, true));
        }

        return new ArrayList<>(list);
    }

    private Set<Reversi> nSucesor(int i, int j) {
        Set<Reversi> nuevosSucesores = new HashSet<>();

        List<Boolean> pos = tableroReversi.movimientoValido(i, j - 1, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i, j - 1, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i, j + 1, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i, j + 1, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i - 1, j, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i - 1, j, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i + 1, j, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i + 1, j, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i - 1, j - 1, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i - 1, j - 1, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i + 1, j + 1, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i + 1, j + 1, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i + 1, j - 1, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i + 1, j - 1, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }

        pos = tableroReversi.movimientoValido(i - 1, j + 1, fichaActual());
        if (pos.contains(true)) {
            TableroReversi temp = tableroReversi.clone();
            temp.soltarFicha(i - 1, j + 1, fichaActual(), pos);

            Reversi reversi = new Reversi(!turno1, temp, false);
            nuevosSucesores.add(reversi);
        }
        return nuevosSucesores;
    }

    public Ficha<Integer> fichaActual() {
        return turno1 ? ficha1 : ficha2;
    }

    public Ficha<Integer> fichaOtro() {
        return turno1 ? ficha2 : ficha1;
    }

    public void ver() {
        this.tableroReversi.ver();
    }

    public boolean ganaActual() {
        return agotado() && (tableroReversi.puntuacion(fichaActual()) > tableroReversi.puntuacion(fichaOtro()));
    }

    public boolean ganaOtro() {
        return agotado() && (tableroReversi.puntuacion(fichaActual()) < tableroReversi.puntuacion(fichaOtro()));
    }

    public Tablero tablero() {
        return tableroReversi;
    }

    public int[][] contenidoTablero() {
        return tablero;
    }

    public boolean turno1() {
        return turno1;
    }

    public boolean anteriorPasa() {
        return anteriorPasa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reversi reversi = (Reversi) o;
        return turno1 == reversi.turno1 &&
                anteriorPasa == reversi.anteriorPasa &&
                tableroReversi.equals(reversi.tableroReversi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(turno1, anteriorPasa, tableroReversi);
    }

    @Override
    public boolean agotado() {
        return anteriorPasa || (!tableroReversi.puedeMover(fichaOtro()) && !tableroReversi.puedeMover(fichaActual()));
    }

    @Override
    public int[] codifica() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < tableroReversi.nColumnas(); i++) {
            for (int j = 0; j < tableroReversi.nFilas(); j++) {
                if (tableroReversi.contenido(i, j).equals(ficha1)) {
                    list.add(1);
                } else if (tableroReversi.contenido(i, j).equals(ficha2)) {
                    list.add(-1);
                } else {
                    list.add(0);
                }
            }
        }

        for (int i = 0; i < tableroReversi.nColumnas(); i++) {
            for (int j = 0; j < tableroReversi.nFilas(); j++) {
                if (tableroReversi.contenido(i, j).equals(TableroReversi.pVacia)) {
                    list.add(1);
                } else {
                    list.add(0);
                }
            }
        }

        list.add(1);

        return list.stream().mapToInt(i -> i).toArray();
    }
}
