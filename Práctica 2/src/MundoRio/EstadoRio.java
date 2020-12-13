package MundoRio;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EstadoRio extends OverrideHashCode implements RepresentacionEstadoOptimizacion<EstadoRio> {
    // Izquierda
    private int izqCanibal;
    private int izqMisionero;
    // Derecha
    private int derCanibal;
    private int derMisionero;
    // Barca
    private int barcaCanibal;
    private int barcaMisionero;

    public EstadoRio() {
        // Izquierda
        this.izqCanibal = 3;
        this.izqMisionero = 3;
        // Derecha
        this.derCanibal = 0;
        this.derMisionero = 0;
        // Barca
        this.barcaCanibal = 0;
        this.barcaMisionero = 0;
    }

    public EstadoRio(int izqCanibal, int izqMisionero, int derCanibal, int derMisionero, int barcaCanibal, int barcaMisionero) {
        // Izquierda
        this.izqCanibal = izqCanibal;
        this.izqMisionero = izqMisionero;
        // Derecha
        this.derCanibal = derCanibal;
        this.derMisionero = derMisionero;
        // Barca
        this.barcaCanibal = barcaCanibal;
        this.barcaMisionero = barcaMisionero;
    }

    @Override
    public List<EstadoRio> calculaSucesores() {
        List<EstadoRio> list = new ArrayList<>();

        if (izqCanibal + izqMisionero > 0) {
            // Solo se mueve uno de cada
            if (izqMisionero - 1 >= izqCanibal) {
                list.add(new EstadoRio(izqCanibal, izqMisionero - 1, derCanibal, derMisionero, barcaCanibal, barcaMisionero + 1));
            }

            if (derCanibal + 1 < derMisionero || derMisionero == 0) {
                list.add(new EstadoRio(izqCanibal - 1, izqMisionero, derCanibal, derMisionero));
            }

            // Se mueven dos de cada
            if (izqMisionero - 2 >= izqCanibal) {
                list.add(new EstadoRio(izqCanibal, izqMisionero - 1, derCanibal, derMisionero + 1));
            }

            if (derCanibal + 2 < derMisionero || derMisionero == 0) {
                list.add(new EstadoRio(izqCanibal - 2, izqMisionero, derCanibal + 2, derMisionero));
            }

            //Se mueve de los dos tipos
            if (izqMisionero - 1 >= izqCanibal - 1) {
                list.add(new EstadoRio(izqCanibal - 1, izqMisionero - 1, derCanibal + 1, derMisionero + 1));
            }
        }

        if (derCanibal + derMisionero > 0) {
            // Solo se mueve uno de cada
            if (derMisionero - 1 >= derCanibal) {
                list.add(new EstadoRio(izqCanibal, izqMisionero + 1, derCanibal, derMisionero - 1));
            }

            if (izqCanibal + 1 < izqMisionero || izqMisionero == 0) {
                list.add(new EstadoRio(izqCanibal + 1, izqMisionero, derCanibal - 1, derMisionero));
            }

            // Se mueven dos de cada
            if (derMisionero - 2 >= derCanibal) {
                list.add(new EstadoRio(izqCanibal, izqMisionero + 2, derCanibal, derMisionero - 2));
            }

            if (izqCanibal + 2 < izqMisionero || izqMisionero == 0) {
                list.add(new EstadoRio(izqCanibal + 2, izqMisionero, derCanibal - 2, derMisionero));
            }

            //Se mueve de los dos tipos
            if (derMisionero - 1 >= derCanibal - 1) {
                list.add(new EstadoRio(izqCanibal + 1, izqMisionero + 1, derCanibal - 1, derMisionero - 1));
            }
        }


        return list;
    }

    public void ver() {
        System.out.println("Izquierda: " + getIzqCanibal() + "-Canibales, " + getIzqMisionero() + "-Misioneros");
        System.out.println("Derecha: " + getDerCanibal() + "-Canibales, " + getDerMisionero() + "-Misioneros");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoRio estadoRio = (EstadoRio) o;
        return izqCanibal == estadoRio.izqCanibal &&
                izqMisionero == estadoRio.izqMisionero &&
                derCanibal == estadoRio.derCanibal &&
                derMisionero == estadoRio.derMisionero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(izqCanibal, izqMisionero, derCanibal, derMisionero);
    }

    @Override
    public int costeArco(EstadoRio eDestino) {
        return 1;
    }

    public int getDerCanibal() {
        return derCanibal;
    }

    public int getDerMisionero() {
        return derMisionero;
    }

    public int getIzqCanibal() {
        return izqCanibal;
    }

    public int getIzqMisionero() {
        return izqMisionero;
    }
}
