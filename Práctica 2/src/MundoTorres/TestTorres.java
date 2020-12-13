package MundoTorres;

import java.util.List;

public class TestTorres {
    public static void main(String[] args) {
        System.out.println("Estado inicial");
        EstadoTorre e = new EstadoTorre(15);
        e.ver();

        List<EstadoTorre> suc = e.calculaSucesores();
        System.out.println("Sucesores");
        for (EstadoTorre estadoTorre : suc) {
            estadoTorre.ver();
        }
        System.out.println("Solucionando");
        AgenteTorres ag = new AgenteTorres(e);
        List<EstadoTorre> sol = ag.amplitud();

        if (sol != null) {
            System.out.println("----");
            for (EstadoTorre estadoTorre : sol) {
                estadoTorre.ver();
                System.out.println("----");
            }
        }
    }
}
