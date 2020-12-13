package jugadores;

import conectak.ConectaK;
import mundoadversario.EstadoJuego;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Jugador que elige el siguiente movimiento evaluando los sucesores
 * de la posición actual a una profundidad dada, y propagando los
 * valores mediante la estrategia Alfa-Beta.
 *
 * @author Lorenzo Mandow
 * @versión: 2018-10-03
 */
public class JugadorAlfaBeta<E extends EstadoJuego<E>> extends JugadorEvaluar<E> {

    private int profMax;            // Profundidad máxima de búsqueda.
    private int nNodos = 0;         // Contador de nodos
    private boolean ordenar = false;

    /**
     * Constructor.
     *
     * @param ev                Evaluador.
     * @param profundidadMaxima Profundidad máxima de búsqueda.
     */
    public JugadorAlfaBeta(Evaluador<E> ev, int profundidadMaxima) {
        super(ev);
        this.profMax = profundidadMaxima;
    }

    public JugadorAlfaBeta(Evaluador<E> ev, int profundidadMaxima, boolean ordenar) {
        super(ev);
        this.profMax = profundidadMaxima;
        this.ordenar = ordenar;
    }

    @Override
    public E mueve(E e) {

        boolean miTurno = e.turno1();

        VE<E> resultado = negamaxAB(e, this.profMax, miTurno, 1);

        // System.out.println("Evaluación del movimiento: " + resultado.v());

        return resultado.e();
    }

    /**
     *
     */

    public VE<E> negamaxAB(E e, int prof, boolean miTurno, int signo) {
        return negamaxAB(e, prof, miTurno, signo, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public VE<E> negamaxAB(E e, int prof, boolean miTurno, int signo, double alfa, double beta) {
        E mejorE = null;
        double mejorV = Double.NEGATIVE_INFINITY;
        double v2;

        if (prof == 0 || e.ganaActual() || e.ganaOtro() || e.agotado()) {
            mejorV = signo * this.evaluador.evalua(e, miTurno);
            this.nNodos++;
        } else {
            for (E e2 : ordenar(e.calculaSucesores())) {
                v2 = -(negamaxAB(e2, prof - 1, miTurno, -signo, -beta, -alfa).v());
                if ((v2 > mejorV) || (mejorE == null)) {
                    mejorV = v2;
                    mejorE = e2;
                    if (v2 >= beta) {
                        return new VE<E>(mejorV, mejorE);
                    }
                    if (v2 > alfa) {
                        alfa = v2;
                    }
                }
            }
        }
        return new VE<E>(mejorV, mejorE);
    }

    private List<E> ordenar(List<E> l) {
        if (this.ordenar) {
            List<ConectaK> lista = (ArrayList<ConectaK>) l;
            lista.sort(new ComparatorConectaK());
            return (ArrayList<E>) lista;
        } else {
            return super.barajar(l);
        }
    }

    public int getnNodos() {
        return nNodos;
    }
}

class ComparatorConectaK implements Comparator<ConectaK> {
    @Override
    public int compare(ConectaK o1, ConectaK o2) {
        int nFilas = o1.tablero().nFilas();
        int nColumnas = o1.tablero().nColumnas();

        int distancia1 = (Math.abs(nColumnas / 2 - o1.ultimoMov.c())) + (Math.abs(nFilas / 2 - o1.ultimoMov.f()));
        int distancia2 = (Math.abs(nColumnas / 2 - o2.ultimoMov.c())) + (Math.abs(nFilas / 2 - o2.ultimoMov.f()));

        return Integer.compare(distancia1, distancia2);
    }
}
