/**
 * Clase con métodos útiles para realizar pruebas.
 *
 * @author Lawrence
 * @date 2018-11-29
 */

class TestUtil {

    /*
     * Recibe un array de object y devuelve un array de int
     */
    static int[] copiar(Object[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = (Integer) a[i];
        }//for i
        return b;
    }


}
