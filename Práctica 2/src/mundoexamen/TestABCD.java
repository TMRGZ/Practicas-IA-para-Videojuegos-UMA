/**
 *  Miguel Angel Ruiz Gomez 48789569z
 */

package mundoexamen;

import java.util.LinkedList;
import java.util.List;

public class TestABCD {
    public static void main(String[] args) {
        LinkedList<Character> listaA = new LinkedList<>(); // La que va a cambiar
        listaA.add('D');
        listaA.add('C');
        listaA.add('B');
        listaA.add('A');
        LinkedList<Character> listaB = new LinkedList<>(); // La referencia
        listaB.add('A');
        listaB.add('B');
        listaB.add('C');
        listaB.add('D');

        System.out.println("Estado inicial");
        EstadoExamen estadoExamen = new EstadoExamen(listaA, listaB);
        estadoExamen.ver();

        //pruebaPermutacion(listaB, estadoExamen);

        System.out.println("Sucesores");
        for (EstadoExamen estado : estadoExamen.calculaSucesores()) {
            estado.ver();
        }

        System.out.println("Resolver");
        AgenteExamen agenteExamen = new AgenteExamen(estadoExamen);
        List<EstadoExamen> sol = agenteExamen.amplitud();

        for (EstadoExamen estado : sol) {
            estado.ver();
            System.out.println("----");
        }
    }

    private static void pruebaPermutacion(LinkedList<Character> lista, EstadoExamen estadoExamen) {
        System.out.println("Prueba de permutacion");
        System.out.println(estadoExamen.permutaSiguiente(lista, 0).toString());
    }
}
