package MundoMapa;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EstadoMapa extends OverrideHashCode implements RepresentacionEstadoOptimizacion<EstadoMapa> {
    private static char[][] mapa;

    public int x, y;
    public static int destx, desty;

    public EstadoMapa(char[][] mapa, int x, int y, int destx, int desty) {
        this.x = x;
        this.y = y;
        EstadoMapa.destx = destx;
        EstadoMapa.desty = desty;
        EstadoMapa.mapa = mapa;
    }

    public EstadoMapa(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public List<EstadoMapa> calculaSucesores() {
        List<EstadoMapa> list = new ArrayList<>();
        // Rectos
        boolean puedeDer = x + 1 < 512 && mapa[x + 1][y] == '.';
        boolean puedeIzq = x - 1 >= 0 && mapa[x - 1][y] == '.';
        boolean puedeArr = y - 1 >= 0 && mapa[x][y - 1] == '.';
        boolean puedeAb = y + 1 < 512 && mapa[x][y + 1] == '.';
        // Diagonales
        boolean diaDerArr = x + 1 < 512 && y - 1 >= 0 && mapa[x + 1][y - 1] == '.';
        boolean diaDerAb = x + 1 < 512 && y + 1 < 512 && mapa[x + 1][y + 1] == '.';
        boolean diaIzqArr = x - 1 >= 0 && y - 1 >= 0 && mapa[x - 1][y - 1] == '.';
        boolean diaIzqAb = x - 1 >= 0 && y + 1 < 512 && mapa[x - 1][y + 1] == '.';

        if (puedeDer) {
            list.add(new EstadoMapa(x + 1, y));
        }
        if (puedeIzq) {
            list.add(new EstadoMapa(x - 1, y));
        }
        if (puedeArr) {
            list.add(new EstadoMapa(x, y - 1));
        }
        if (puedeAb) {
            list.add(new EstadoMapa(x, y + 1));
        }
        if (diaDerArr) {
            list.add(new EstadoMapa(x + 1, y - 1));
        }
        if (diaDerAb) {
            list.add(new EstadoMapa(x + 1, y + 1));
        }
        if (diaIzqArr) {
            list.add(new EstadoMapa(x - 1, y - 1));
        }
        if (diaIzqAb) {
            list.add(new EstadoMapa(x - 1, y + 1));
        }

        return list;
    }

    public void ver() {
        System.out.println(x + " " + y);
    }

    @Override
    public int costeArco(EstadoMapa eDestino) {
        if (Math.abs(eDestino.x + eDestino.y - (x + y)) == 1) {
            return 1000;
        } else {
            return (int) (Math.sqrt(2) * 1000);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoMapa that = (EstadoMapa) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
