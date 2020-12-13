package MundoTorres;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EstadoTorre extends OverrideHashCode implements RepresentacionEstadoOptimizacion<EstadoTorre> {
    public Stack<Integer> torreA;
    public Stack<Integer> torreB;
    public Stack<Integer> torreC;
    private int n;

    public EstadoTorre(int n) {
        torreA = new Stack<>();
        torreB = new Stack<>();
        torreC = new Stack<>();
        this.n = n;

        for (int i = n; i > 0; i--) {
            torreA.push(i);
        }
    }

    public EstadoTorre(Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
        this.torreA = a;
        this.torreB = b;
        this.torreC = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoTorre that = (EstadoTorre) o;

        if (torreA != null ? !torreA.equals(that.torreA) : that.torreA != null) return false;
        if (torreB != null ? !torreB.equals(that.torreB) : that.torreB != null) return false;
        return torreC != null ? torreC.equals(that.torreC) : that.torreC == null;
    }

    @Override
    public int hashCode() {
        int result = torreA != null ? torreA.hashCode() : 0;
        result = 31 * result + (torreB != null ? torreB.hashCode() : 0);
        result = 31 * result + (torreC != null ? torreC.hashCode() : 0);
        return result;
    }

    public int numEnTorre(Stack<Integer> s) {
        return s.size();
    }

    @Override
    public List<EstadoTorre> calculaSucesores() {
        List<EstadoTorre> list = new ArrayList<>();
        int tamA = numEnTorre(torreA);
        int tamB = numEnTorre(torreB);
        int tamC = numEnTorre(torreC);

        if (tamA > 0) {
            Stack<Integer> copiaA = (Stack<Integer>) torreA.clone();
            Stack<Integer> copiaB = (Stack<Integer>) torreB.clone();
            Stack<Integer> copiaC = (Stack<Integer>) torreC.clone();
            int cima = copiaA.pop();

            if (copiaB.isEmpty() || copiaB.peek() > cima) {
                copiaB.push(cima);
                list.add(new EstadoTorre(copiaA, copiaB, torreC));
            }
            if (copiaC.isEmpty() || copiaC.peek() > cima) {
                copiaC.push(cima);
                list.add(new EstadoTorre(copiaA, torreB, copiaC));
            }
        }

        if (tamB > 0) {
            Stack<Integer> copiaA = (Stack<Integer>) torreA.clone();
            Stack<Integer> copiaB = (Stack<Integer>) torreB.clone();
            Stack<Integer> copiaC = (Stack<Integer>) torreC.clone();
            int cima = copiaB.pop();

            if (copiaA.isEmpty() || copiaA.peek() > cima) {
                copiaA.push(cima);
                list.add(new EstadoTorre(copiaA, copiaB, torreC));
            }
            if (copiaC.isEmpty() || copiaC.peek() > cima) {
                copiaC.push(cima);
                list.add(new EstadoTorre(torreA, copiaB, copiaC));
            }
        }

        if (tamC > 0) {
            Stack<Integer> copiaA = (Stack<Integer>) torreA.clone();
            Stack<Integer> copiaB = (Stack<Integer>) torreB.clone();
            Stack<Integer> copiaC = (Stack<Integer>) torreC.clone();
            int cima = copiaC.pop();

            if (copiaA.isEmpty() || copiaA.peek() > cima) {
                copiaA.push(cima);
                list.add(new EstadoTorre(copiaA, torreB, copiaC));
            }

            if (copiaB.isEmpty() || copiaB.peek() > cima) {
                copiaB.push(cima);
                list.add(new EstadoTorre(torreA, copiaB, copiaC));
            }
        }

        return list;
    }

    public void ver() {
        System.out.println("Torre A: " + torreA.toString() +
                "\nTorre B: " + torreB.toString() +
                "\nTorre C: " + torreC.toString());
    }

    @Override
    public int costeArco(EstadoTorre eDestino) {
        return 1;
    }
}
