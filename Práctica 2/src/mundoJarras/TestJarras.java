package mundoJarras;

import java.util.List;

public class TestJarras {
    public static void main(String[] args) {
        estadoJarra e = new estadoJarra(5,0);
        e.ver();

        List<estadoJarra> suc = e.calculaSucesores();

        System.out.println("Sucesores");
        for (estadoJarra estadoJarra : suc) {
            estadoJarra.ver();
        }

        System.out.println("Solucionando...");
        AgenteJarras ag = new AgenteJarras(e);
        List<estadoJarra> sol = ag.amplitud();

        if (sol != null) {
            for (estadoJarra estadoJarra : sol) {
                estadoJarra.ver();
            }
        }


    }
}
