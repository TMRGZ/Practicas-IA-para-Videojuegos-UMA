package puzzle8;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Permite resolver el n-puzzle empleando como heuristico una base de datos de patrones (BDP).
 * La BDP se guarda internamente como una tabla hash de costes indexados por Puzzle.
 *
 * @author Esteban Calderon Pinto, Jose Miguel Horcas Aguilera, Lorenzo Mandow
 * @version 2013-10-09
 */
public class AgentePuzzleMAN extends AgentePuzzleH0 {
    private HashMap<Puzzle, Integer> bdp;
    //tamaño de tablero y numero de comodines de la actual bdp:
    private int numFilas = -1;
    private int numCol = -1;
    private int numComodines = -1;
    private int numPiezas = -1;
    private static final int COMODIN = -1;

    /**
     * Constructor. Establece el estado de salida del problema y calcula una BDP con el numero de comodines indicado.
     */
    public AgentePuzzleMAN(Puzzle p, int comodines) {
        super(p);
        calculaBDP(comodines);
    }

    /**
     * Cambia el estado de salida del problema. Si el tamaño del puzzle no coincide con el anterior,
     * se invalida la bdp, y será necesario recalcularla de nuevo con calculaBDP.
     */
    public void cambiarSalida(Puzzle e) {
        this.salida = e;
        if (e.nf() != numFilas || e.nc() != numCol) {
            bdp = null;
            numFilas = -1;
            numCol = -1;
            numComodines = -1;
            numPiezas = -1;
        }
    }


    /**
     * Calcula la bdp para un puzzle del tamano del puzzle de salida, y con el numero de
     * comodines indicado.
     *
     * @param n numero de fichas comodin.
     */
    public void calculaBDP(int n) {
        if (n < 0 || n >= this.salida.nf() * this.salida.nc()) {
            System.out.println("Numero incorrecto de comodines para generar la BDP");
            return;
        }

        int nf = this.salida.nf();
        int nc = this.salida.nc();
        int tablero[][] = new int[nf][nc];
        int cont = 0;
        this.numPiezas = nf * nc - n;  //total de piezas menos el numero de comodines
        this.numComodines = n;
        this.numFilas = nf;
        this.numCol = nc;

        // Calculamos un patron con el numero de piezas adecuado: 

        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                if (cont < numPiezas) {
                    tablero[i][j] = cont;
                } else {
                    tablero[i][j] = COMODIN;
                }
                cont += 1;
            }
        }
        Puzzle patron = new Puzzle(tablero, 0, 0);

        // Calculamos la base de datos de patrones partiendo del patron. Aprovechamos el hecho de que al ser los costes 1, siempre que
        // encontramos un puzzle nuevo, hemos encontrado ya el camino optimo hasta el.

        this.bdp = new HashMap<>();
        LinkedList<Puzzle> abiertos = new LinkedList<>();
        int g2;
        Puzzle e = patron;
        bdp.put(e, 0);

        int it = 0;

        while (e != null) {
            for (Puzzle e2 : e.calculaSucesores()) {
                if (!bdp.containsKey(e2)) {
                    g2 = bdp.get(e) + e.costeArco(e2);
                    abiertos.offer(e2);
                    bdp.put(e2, g2);
                }
            }
            e = abiertos.poll();
            it += 1;
        }

        System.out.println("Entradas en la tabla: " + it);

    }

    /**
     * Funcion heuristica que construye un patron a partir del puzzle y
     * consulta el contenido asociado en la base de datos de patrones.
     *
     * @param p el puzzle cuyo heuristico se va a devolver.
     * @return estimacion del coste de llegar desde p al objetivo, consultado en la
     * base de datos de patrones.
     */
    @Override
    public int h(Puzzle p) {
        int sumaTotDesc = 0;

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numCol; j++) {
                if (p.tab[i][j] != 0)

                sumaTotDesc += Math.abs(p.tab[i][j]/numFilas - i) + Math.abs(p.tab[i][j]%numCol - j);
            }
        }
        return sumaTotDesc;

    }

}
