package MundoCartas;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.*;

public class EstadoCartas extends OverrideHashCode implements RepresentacionEstadoOptimizacion<EstadoCartas> {
    private LinkedList<Integer> colA = new LinkedList<>();
    private LinkedList<Integer> colB = new LinkedList<>();

    public EstadoCartas(int n) {
        if (n % 2 != 0) throw new RuntimeException("N cartas no par");

        List<Integer> cartas = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            cartas.add(i + 1);
        }
        Collections.shuffle(cartas);

        for (int i = 0; i < cartas.size(); i++) {
            if (i < cartas.size() / 2) colA.add(cartas.get(i));
            else colB.add(cartas.get(i));
        }
    }

    public EstadoCartas(LinkedList<Integer> a, LinkedList<Integer> b){
        this.colA = a;
        this.colB = b;
    }


    @Override
    public List<EstadoCartas> calculaSucesores() {
        List<EstadoCartas> list = new ArrayList<>();


        // Mod A
        LinkedList<Integer> modA = (LinkedList<Integer>) colA.clone();
        int auxNA = modA.getFirst();
        modA.removeFirst();
        modA.addLast(auxNA);
        list.add(new EstadoCartas(modA, colB));

        // Mod B
        LinkedList<Integer> modB = (LinkedList<Integer>) colB.clone();
        int auxNB = modB.getFirst();
        modB.removeFirst();
        modB.addLast(auxNB);
        list.add(new EstadoCartas(colA, modB));

        // Int
        LinkedList<Integer> copyA = (LinkedList<Integer>) colA.clone();
        LinkedList<Integer> copyB = (LinkedList<Integer>) colB.clone();
        auxNA = copyA.getFirst();
        auxNB = copyB.getFirst();
        copyA.removeFirst();
        copyB.removeFirst();
        copyA.addFirst(auxNB);
        copyB.addFirst(auxNA);
        list.add(new EstadoCartas(copyA, copyB));


        return list;
    }

    public void ver() {
        System.out.println("A: " + colA.toString());
        System.out.println("B: " + colB.toString());
    }

    public LinkedList<Integer> getColA() {
        return colA;
    }

    public LinkedList<Integer> getColB() {
        return colB;
    }

    @Override
    public int costeArco(EstadoCartas eDestino) {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoCartas that = (EstadoCartas) o;
        return Objects.equals(colA, that.colA) &&
                Objects.equals(colB, that.colB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colA, colB);
    }
}
