package MundoRio;

import java.util.List;

public class TestRio {
    public static void main(String[] args) {
        EstadoRio estadoRio = new EstadoRio();

        System.out.println("Estado inicial");
        estadoRio.ver();

        System.out.println("Calcula sucesores");
        List<EstadoRio> sucesores = estadoRio.calculaSucesores();

        for (EstadoRio sucesor : sucesores) {
            sucesor.ver();
        }

        System.out.println("Solucionando");
        AgenteRio agenteRio = new AgenteRio(estadoRio);
        List<EstadoRio> list = agenteRio.amplitud();

        if (list != null) {
            System.out.println("----");
            for (EstadoRio rio : list) {
                rio.ver();
                System.out.println("----");
            }
        }


    }
}
