import org.junit.Assert;
import reversi.Reversi;

import java.util.LinkedList;
import java.util.List;

/**
 * Test JUnit para comprobar el método calculaSucesores del Reversi.
 * Incluye los métodos de comprobación, pero no los datos y los tests, que se incluyen
 * en clases herederas (debido a la limitación de espacio de static de Java).
 *
 * @author Lawrence
 * @date 2018-11-28
 */

class TestSucesoresReversi extends TestUtil {

    static Object[][][][][][] todo;  //array donde se guardan los datos para los test

//  Modelo de método test
//	@Test
//	public void testDatos(){
//		for (int i = 0; i < todo.length; i++){
//			probarDatos(todo[i], "Probando datos del conjunto " + i);
//		}
//	}	


    void probarDatos(Object[][][][][] datosSuc, String str) {

        System.out.println(str);

        for (int i = 0; i < datosSuc.length; i++) {
            System.out.println("Caso " + i + "-------------------------------------------------");
            Assert.assertTrue("Caso " + i, probarCaso(datosSuc[i]));
        }//for i
    }

    /**
     * Para un caso, genera el estado, los Sucesores, y comprueba si los
     * Sucesores generados se corresponden con los esperados.
     * <p>
     * La comprobación se hace generando la lista de sucesores reales
     * a partir de los datos, y comparándolos con equals con los
     * calculados por el método calculaSucesores.
     * <p>
     * Cada caso contiene dos elementos:
     * - los datos de un estado
     * - un array con los datos de los Sucesores esperables (reales)
     */
    private boolean probarCaso(Object[][][][] caso) {

        Object[][] datosEstado = caso[0][0];
        Object[][][] datosSucesores = caso[1];

        Reversi e = obtenerReversi(datosEstado);
        System.out.println("Original");
        e.ver();
        List<Reversi> sucesoresReales = obtenerListaReversi(datosSucesores);

        List<Reversi> sucesoresE = e.calculaSucesores();

        if (sucesoresE.size() != sucesoresReales.size()) {
            System.out.println(" No coincide el número de Sucesores calculados. Hay " + sucesoresE.size() + " en lugar de " + sucesoresReales.size());

            System.out.println("Tuyos");
            for (Reversi reversi : sucesoresE) {
                reversi.ver();
            }
            System.out.println("Esperados");
            for (Reversi reversi : sucesoresReales) {
                reversi.ver();
            }
            return false; //el número de sucesores no coincide
        }

        //comprobamos que los sucesores calculados están entre los reales
        for (int i = 0; i < sucesoresE.size(); i++) {
            if (!sucesoresReales.contains(sucesoresE.get(i))) {  //nota: contains utiliza equals para la comparación
                System.out.println("  - El sucesor " + i + " no se corresponde (equals) con los datos de prueba");
                sucesoresE.get(i).ver();
                System.out.println("Sucesores esperados");
                for (Reversi reversi : sucesoresReales) {
                    reversi.ver();
                }



                return false; //hay un sucesor que no está en la lista de los esperados
            }//if !
        }

        //comprobamos que los sucesores reales están entre los calculados
        for (int i = 0; i < sucesoresReales.size(); i++) {
            if (!sucesoresE.contains(sucesoresReales.get(i))) {  //nota: contains utiliza equals para la comparación
                System.out.println("  - Los datos " + i + " no se corresponden (equals) con ningún sucesor calculado por calculaSucesores");
                sucesoresReales.get(i).ver();
                return false; //hay un sucesor que no está en la lista de los calculados
            }//if !
        }


        //si las longitudes coinciden y los conjuntos también, entonces todo fue bien
        System.out.println("  OK");
        return true;
    }

    /**
     * Recibe una array Object[][][] con los datos de un conjunto de sucesores.
     * Construye una lista con los objetos Reversi correspondientes.
     *
     * @return
     */
    private static List<Reversi> obtenerListaReversi(Object[][][] datosSucesores) {
        List<Reversi> resultado = new LinkedList<>();

        for (Object[][] datosSuc : datosSucesores) {
            resultado.add(obtenerReversi(datosSuc));
        }

        return resultado;
    }

    /**
     * Dado un array Object[][], extrae los datos y crea un objeto Reversi.
     * <p>
     * Este es el único método realmente dependiente de la clase Reversi.
     */
    static Reversi obtenerReversi(Object[][] datosEstado) {
        //Obtenemos los datos del estado a crear
        int nDatos = datosEstado.length;

        int nFilas = nDatos - 2;   //número de filas del tablero

        int[][] tablero = new int[nFilas][];
        for (int k = 0; k < nFilas; k++) {
            tablero[k] = copiar(datosEstado[k]);
        }
        Boolean turno1 = (Boolean) datosEstado[nFilas][0];
        Boolean anteriorPasa = (Boolean) datosEstado[nFilas + 1][0];

        //Creamos el estado y calculamos sus sucesores
        return new Reversi(tablero, turno1, anteriorPasa);
    }
}
