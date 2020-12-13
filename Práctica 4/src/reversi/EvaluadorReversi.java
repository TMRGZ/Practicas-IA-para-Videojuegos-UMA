package reversi;

import jugadores.Evaluador;

public class EvaluadorReversi extends Evaluador<Reversi> {
    private int[][] matrizPesos = {
            {120, -20, 20, 5, 5, 20, -20, 120},
            {-20, -40, -5, -5, -5, -5, -40, -20},
            {20, -5, 15, 3, 3, 15, -5, 20},
            {5, -5, 3, 3, 3, 3, -5, 5},
            {5, -5, 3, 3, 3, 3, -5, 5},
            {20, -5, 15, 3, 3, 15, -5, 20},
            {-20, -40, -5, -5, -5, -5, -40, -20},
            {120, -20, 20, 5, 5, 20, -20, 120}};

    public EvaluadorReversi(int nFilas, int nColumnas) {
        if (nFilas != 8 || nColumnas != 8) {
            throw new RuntimeException("La evaluacion solo es compatible con tablero 8x8");
        }
        //int[][] matrizPesos = new int[nFilas][nColumnas];
    }


    @Override
    protected double evaluacion(Reversi estado, boolean miTurno) {
        int jug1 = 0;
        int jug2 = 0;

        for (int i = 0; i < estado.tablero().nFilas(); i++) {
            for (int j = 0; j < estado.tablero().nColumnas(); j++) {
                if (estado.tablero().contenido(i, j).equals(Reversi.ficha1)) {
                    jug1 += matrizPesos[i][j];
                } else if (estado.tablero().contenido(i, j).equals(Reversi.ficha2)) {
                    jug2 += matrizPesos[i][j];
                }
            }
        }

        return miTurno ? jug1 - jug2 : jug2 - jug1;
    }
}
