/**
 *  Miguel Angel Ruiz Gomez 48789569z
 */


package mundoexamen;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EstadoExamen extends OverrideHashCode implements RepresentacionEstadoOptimizacion<EstadoExamen> {
    private LinkedList<Character> listaA; // Lista a cambiar
    private LinkedList<Character> listaB; // Lista referencia

    public EstadoExamen(LinkedList<Character> listaA, LinkedList<Character> listaB) {
        if (listaA.size() != listaB.size()) throw new RuntimeException("Listas de diferente tamaño");
        this.listaA = listaA;
        this.listaB = listaB;
    }

    @Override
    public List<EstadoExamen> calculaSucesores() {
        List<EstadoExamen> list = new ArrayList<>();
        LinkedList<Character> auxA = (LinkedList<Character>) listaA.clone();

        // Rotación
        Character auxC = auxA.getLast();
        auxA.removeLast();
        auxA.addFirst(auxC);
        list.add(new EstadoExamen(auxA, listaB));

        // Permutacion
        for (int i = 0; i < listaA.size(); i++) {
            auxA = (LinkedList<Character>) listaA.clone();
            list.add(new EstadoExamen(permutaSiguiente(auxA, i), listaB));
        }

        return list;
    }

    public LinkedList<Character> permutaSiguiente(LinkedList<Character> list, int i) {
        Character auxA = list.get(i);
        Character auxB = list.get((i + 1) % (list.size()));
        list.set((i + 1) % (list.size()), auxA);
        list.set(i, auxB);
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoExamen that = (EstadoExamen) o;

        if (!Objects.equals(listaA, that.listaA)) return false;
        return Objects.equals(listaB, that.listaB);
    }

    @Override
    public int hashCode() {
        int result = listaA != null ? listaA.hashCode() : 0;
        result = 31 * result + (listaB != null ? listaB.hashCode() : 0);
        return result;
    }

    @Override
    public int costeArco(EstadoExamen eDestino) {
        return 1;
    }

    public LinkedList<Character> getListaA() {
        return listaA;
    }

    public LinkedList<Character> getListaB() {
        return listaB;
    }

    public void ver() {
        System.out.println("Lista A: " + listaA.toString() + " <--");
        System.out.println("Lista B: " + listaB.toString());
    }
}
