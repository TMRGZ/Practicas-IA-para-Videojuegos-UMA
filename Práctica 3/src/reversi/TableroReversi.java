package reversi;

import java.util.Arrays;
import java.util.List;

public class TableroReversi extends Tablero {
    public static final Ficha<Integer> pVacia = new Ficha<>(0);

    public TableroReversi(int nFil, int nCol) {
        super(nFil, nCol, TableroReversi.pVacia);

        int centroC = nCol / 2;
        int centroF = nFil / 2;

        this.ponerFicha(centroC - 1, centroF, Reversi.ficha2);
        this.ponerFicha(centroC - 1, centroF - 1, Reversi.ficha1);
        this.ponerFicha(centroC, centroF, Reversi.ficha1);
        this.ponerFicha(centroC, centroF - 1, Reversi.ficha2);
    }

    public TableroReversi(Tablero tab) {
        super(tab);
    }

    public int puntuacion(Ficha f) {
        int cont = 0;

        for (int i = 0; i < this.nFilas(); i++) {
            for (int j = 0; j < this.nColumnas(); j++) {
                if (this.contenido(i, j).equals(f)) cont++;
            }
        }

        return cont;
    }

    public void soltarFicha(int x, int y, Ficha ficha, List<Boolean> validos) {
        if (validos == null){
            validos = movimientoValido(x, y, ficha);
        }

        ponerFicha(x, y, ficha);

        //Indices
        //arriba pos = 0
        int j_arriba = y - 1;
        //abajo pos = 1
        int j_abajo = y + 1;
        //izquierda pos = 2
        int i_izquierda = x - 1;
        //derecha pos = 3
        int i_derecha = x + 1;
        // izq arriba pos = 4
        int i_izqArriba = i_izquierda;
        int j_izqArriba = j_arriba;
        // der arriba pos = 5
        int i_derArriba = i_derecha;
        int j_derArriba = j_arriba;
        // izq abajo pos = 6
        int i_izqAbajo = i_izquierda;
        int j_izqAbajo = j_abajo;
        // der abajo pos = 7
        int i_derAbajo = i_derecha;
        int j_derAbajo = j_abajo;

        while (validos.get(0) || validos.get(1) || validos.get(2) || validos.get(3) || validos.get(4) || validos.get(5) || validos.get(6) || validos.get(7)) {
            if (validos.get(0)) {
                validos.set(0, !this.contenido(x, j_arriba).equals(ficha));
                this.ponerFicha(x, j_arriba, ficha);
                j_arriba--;
            }

            if (validos.get(1)) {
                validos.set(1, !this.contenido(x, j_abajo).equals(ficha));
                this.ponerFicha(x, j_abajo, ficha);
                j_abajo++;
            }

            if (validos.get(2)) {
                validos.set(2, !this.contenido(i_izquierda, y).equals(ficha));
                this.ponerFicha(i_izquierda, y, ficha);
                i_izquierda--;
            }

            if (validos.get(3)) {
                validos.set(3, !this.contenido(i_derecha, y).equals(ficha));
                this.ponerFicha(i_derecha, y, ficha);
                i_derecha++;
            }

            if (validos.get(4)) {
                validos.set(4, !this.contenido(i_izqArriba, j_izqArriba).equals(ficha));
                this.ponerFicha(i_izqArriba, j_izqArriba, ficha);
                i_izqArriba--;
                j_izqArriba--;
            }

            if (validos.get(5)) {
                validos.set(5, !this.contenido(i_derArriba, j_derArriba).equals(ficha));
                this.ponerFicha(i_derArriba, j_derArriba, ficha);
                i_derArriba++;
                j_derArriba--;
            }

            if (validos.get(6)) {
                validos.set(6, !this.contenido(i_izqAbajo, j_izqAbajo).equals(ficha));
                this.ponerFicha(i_izqAbajo, j_izqAbajo, ficha);
                i_izqAbajo--;
                j_izqAbajo++;
            }

            if (validos.get(7)) {
                validos.set(7, !this.contenido(i_derAbajo, j_derAbajo).equals(ficha));
                this.ponerFicha(i_derAbajo, j_derAbajo, ficha);
                i_derAbajo++;
                j_derAbajo++;
            }
        }
    }

    public List<Boolean> movimientoValido(int x, int y, Ficha ficha) {
        List<Boolean> validos = Arrays.asList(false, false, false, false, false, false, false, false);

        if (!posValida(x, y) || !contenido(x, y).equals(pVacia)) {
            return validos;
        }
        //Indices
        //arriba pos = 0
        int j_arriba = y - 1;
        boolean arr = posValida(x, j_arriba);
        //abajo pos = 1
        int j_abajo = y + 1;
        boolean ab = posValida(x, j_abajo);
        //izquierda pos = 2
        int i_izquierda = x - 1;
        boolean izq = posValida(i_izquierda, y);
        //derecha pos = 3
        int i_derecha = x + 1;
        boolean der = posValida(i_derecha, y);
        // izq arriba pos = 4
        int i_izqArriba = i_izquierda;
        int j_izqArriba = j_arriba;
        boolean izqArr = posValida(i_izqArriba, j_izqArriba);
        // der arriba pos = 5
        int i_derArriba = i_derecha;
        int j_derArriba = j_arriba;
        boolean derArr = posValida(i_derArriba, j_derArriba);
        // izq abajo pos = 6
        int i_izqAbajo = i_izquierda;
        int j_izqAbajo = j_abajo;
        boolean izqAb = posValida(i_izqAbajo, j_izqAbajo);
        // der abajo pos = 7
        int i_derAbajo = i_derecha;
        int j_derAbajo = j_abajo;
        boolean derAb = posValida(i_derAbajo, j_derAbajo);


        while (arr && !validos.get(0) || izq && !validos.get(2) || der && !validos.get(3) || ab && !validos.get(1) || izqArr && !validos.get(4) || derArr && !validos.get(5) || izqAb && !validos.get(6) || derAb && !validos.get(7)) {
            arr = posValida(x, j_arriba) && !this.contenido(x, j_arriba).equals((pVacia)) && this.contenido(x, y - 1).equals(fichaContraria(ficha));
            if (arr && !validos.get(0)) {
                boolean consecutiva = j_arriba + 1 == y;
                validos.set(0, (this.contenido(x, j_arriba).equals((ficha))) && !consecutiva);
                j_arriba--;
            }

            ab = posValida(x, j_abajo) && !this.contenido(x, j_abajo).equals((pVacia)) && this.contenido(x, y + 1).equals(fichaContraria(ficha));
            if (ab && !validos.get(1)) {
                boolean consecutiva = j_abajo - 1 == y;
                validos.set(1, (this.contenido(x, j_abajo).equals((ficha))) && !consecutiva);
                j_abajo++;
            }

            izq = posValida(i_izquierda, y) && !this.contenido(i_izquierda, y).equals((pVacia)) && this.contenido(x - 1, y).equals(fichaContraria(ficha));
            if (izq && !validos.get(2)) {
                boolean consecutiva = i_izquierda + 1 == x;
                validos.set(2, (this.contenido(i_izquierda, y).equals((ficha))) && !consecutiva);
                i_izquierda--;
            }

            der = posValida(i_derecha, y) && !this.contenido(i_derecha, y).equals((pVacia)) && this.contenido(x + 1, y).equals(fichaContraria(ficha));
            if (der && !validos.get(3)) {
                boolean consecutiva = i_derecha - 1 == x;
                validos.set(3, (this.contenido(i_derecha, y).equals((ficha))) && !consecutiva);
                i_derecha++;
            }

            izqArr = posValida(i_izqArriba, j_izqArriba) && !this.contenido(i_izqArriba, j_izqArriba).equals((pVacia)) && this.contenido(x - 1, y - 1).equals(fichaContraria(ficha));
            if (izqArr && !validos.get(4)) {
                boolean consecutiva = i_izqArriba + 1 == x && j_izqArriba + 1 == y;
                validos.set(4, (this.contenido(i_izqArriba, j_izqArriba).equals((ficha))) && !consecutiva);
                i_izqArriba--;
                j_izqArriba--;
            }

            derArr = posValida(i_derArriba, j_derArriba) && !this.contenido(i_derArriba, j_derArriba).equals((pVacia)) && this.contenido(x + 1, y - 1).equals(fichaContraria(ficha));
            if (derArr && !validos.get(5)) {
                boolean consecutiva = i_derArriba - 1 == x && j_derArriba + 1 == y;
                validos.set(5, (this.contenido(i_derArriba, j_derArriba).equals((ficha))) && !consecutiva);
                i_derArriba++;
                j_derArriba--;
            }

            izqAb = posValida(i_izqAbajo, j_izqAbajo) && !this.contenido(i_izqAbajo, j_izqAbajo).equals((pVacia)) && this.contenido(x - 1, y + 1).equals(fichaContraria(ficha));
            if (izqAb && !validos.get(6)) {
                boolean consecutiva = i_izqAbajo + 1 == x && j_izqAbajo - 1 == y;
                validos.set(6, (this.contenido(i_izqAbajo, j_izqAbajo).equals((ficha))) && !consecutiva);
                i_izqAbajo--;
                j_izqAbajo++;
            }

            derAb = posValida(i_derAbajo, j_derAbajo) && !this.contenido(i_derAbajo, j_derAbajo).equals((pVacia)) && this.contenido(x + 1, y + 1).equals(fichaContraria(ficha));
            if (derAb && !validos.get(7)) {
                boolean consecutiva = i_derAbajo - 1 == x && j_derAbajo - 1 == y;
                validos.set(7, (this.contenido(i_derAbajo, j_derAbajo).equals((ficha))) && !consecutiva);
                i_derAbajo++;
                j_derAbajo++;
            }
        }

        return validos;
    }

    public boolean puedeMover(Ficha ficha){
        for (int i = 0; i < this.nColumnas(); i++) {
            for (int j = 0; j < this.nFilas(); j++) {
                if (this.movimientoValido(i, j, ficha).contains(true)) return true;
            }
        }
        return false;
    }

    private Ficha fichaContraria(Ficha ficha) {
        return ficha.equals(Reversi.ficha1) ? Reversi.ficha2 : Reversi.ficha1;
    }

    public TableroReversi clone() {
        TableroReversi nuevo = new TableroReversi(this.nFilas(), this.nColumnas());

        for (int i = 0; i < this.nFilas(); i++) {
            for (int j = 0; j < this.nColumnas(); j++) {
                nuevo.ponerFicha(i, j, this.contenido(i, j));
            }
        }
        return nuevo;
    }

    public void ver() {
        super.ver();
    }


}
