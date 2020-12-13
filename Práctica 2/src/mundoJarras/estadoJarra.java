package mundoJarras;

import mundosolitario.OverrideHashCode;
import mundosolitario.RepresentacionEstadoOptimizacion;

import java.util.ArrayList;
import java.util.List;

public class estadoJarra extends OverrideHashCode implements RepresentacionEstadoOptimizacion<estadoJarra> {
    public final static int MAXG = 5;
    public final static int MAXP = 2;
    public int jg;
    public int jp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        estadoJarra that = (estadoJarra) o;

        if (jg != that.jg) return false;
        return jp == that.jp;
    }

    @Override
    public int hashCode() {
        int result = jg;
        result = 31 * result + jp;
        return result;
    }

    public estadoJarra(int jg, int jp) {
        this.jp = jp;
        this.jg = jg;
    }

    @Override
    public List<estadoJarra> calculaSucesores() {
        List<estadoJarra> list = new ArrayList<>();

        //Descartes
        if (this.jg > 0) {
            list.add(new estadoJarra(0, this.jp));
        }

        if (this.jp > 0) {
            list.add(new estadoJarra(this.jg, 0));
        }
        // Trasvase
        int total = this.jg + this.jp;
        if (this.jg > 0 && total <= MAXP) {
            list.add(new estadoJarra(0, total));
        }

        if (this.jp > 0 && total <= MAXG) {
            list.add(new estadoJarra(total, 0));
        }
        // Trasvase parcial
        if (this.jg > 0 && total > MAXP) {
            list.add(new estadoJarra(total - MAXP, MAXP));
        }

        if (this.jp > 0 && total > MAXG) {
            list.add(new estadoJarra(MAXG, total - MAXG));
        }

        return list;
    }

    @Override
    public int costeArco(estadoJarra eDestino) {
        return 1;
    }

    public void ver(){
        System.out.println(this.jg + " " + this.jp);
    }
}
