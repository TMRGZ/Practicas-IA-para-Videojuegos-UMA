package MundoCartas;

import java.util.List;

public class TestCartas {
    public static void main(String[] args) {
        System.out.println("Estado inicial");
        EstadoCartas estadoCartas = new EstadoCartas(6);
        estadoCartas.ver();

        System.out.println("Sucesores");
        List<EstadoCartas> suc = estadoCartas.calculaSucesores();

        for (EstadoCartas cartas : suc) {
            cartas.ver();
        }

        System.out.println("Solucionando");
        AgenteCartas agenteCartas = new AgenteCartas(estadoCartas);
        List<EstadoCartas> list = agenteCartas.amplitud();

        if (list != null) {
            System.out.println("----");
            for (EstadoCartas cartas : list) {
                cartas.ver();
                System.out.println("----");
            }
        }
    }
}
