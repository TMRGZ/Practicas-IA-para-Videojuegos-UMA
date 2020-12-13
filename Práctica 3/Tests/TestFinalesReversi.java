import org.junit.Assert;
import org.junit.Test;
import reversi.Reversi;

/**
 * Test para comprobar los métodos ganaActual, ganaOtro y agotado.
 * <p>
 * NOTA: Se utiliza el constructor Reversi (tablero, turno1, anteriorPasa).
 * Si la implementación utiliza cualquier otro dato interno (por ejemplo,
 * número de fichas colocadas, etc.), y esos datos se usan en los métodos
 * comentados, el constructor debe darles valor adecuado.
 *
 * @author Lawrence
 * @date 2018-11-28
 */

public class TestFinalesReversi extends TestSucesoresReversi {

    @Test
    public void pruebaGanaActual() {
        System.out.println("\n*******************************\nTest ganaActual\n");
        for (int i = 0; i < ganaActual.length; i++) {
            Reversi e = obtenerReversi(ganaActual[i]);
//			e.ver();
            //Assert.assertTrue("ganaActual: Estado " + i + " agotado: " + e.agotado(), e.ganaActual());
            e.ver();
            System.out.println(e.agotado());
            Assert.assertTrue("ganaActual: Estado " + i + " agotado: " + e.agotado(), e.ganaActual());
        }
    }

    @Test
    public void pruebaGanaOtro() {
        System.out.println("\n*******************************\nTest ganaOtro\n");
        for (int i = 0; i < ganaOtro.length; i++) {
            Reversi e = obtenerReversi(ganaOtro[i]);
//			e.ver();
            //Assert.assertTrue("ganaOtro: Estado " + i + " agotado: " + e.agotado(), e.ganaOtro());
            Assert.assertEquals(e.agotado(), e.ganaOtro());
        }
    }

    @Test
    public void pruebaAgotado() {
        System.out.println("\n*******************************\nTest agotado\n");
        for (int i = 0; i < agotado.length; i++) {
            Reversi e = obtenerReversi(agotado[i]);
//			e.ver();
            //System.out.println(e.agotado());
            Assert.assertTrue(e.agotado());

        }
    }

    private static final Object[][][] ganaActual = {{
            {-1, 1, 1, 1},
            {1, -1, 1, -1},
            {1, 1, -1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {-1, 0, -1, -1},
            {1, 1, 1, -1},
            {0, 1, -1, -1},
            {0, 0, 1, -1},
            {false}, {true}}

            , {
            {1, 1, 1, 1},
            {1, 1, 1, -1},
            {0, -1, -1, 0},
            {0, -1, -1, -1},
            {true}, {true}}

            , {
            {-1, 1, 1, 1},
            {-1, 1, 1, 1},
            {-1, -1, 1, 1},
            {-1, -1, 1, 1},
            {true}, {false}}

            , {
            {1, -1, -1, 1},
            {1, 1, -1, 1},
            {1, -1, 1, 1},
            {-1, -1, -1, 1},
            {true}, {false}}

            , {
            {0, 0, 0, 1},
            {0, -1, 1, 1},
            {0, 1, 1, 1},
            {0, 1, 0, -1},
            {true}, {true}}

            , {
            {-1, -1, -1, 1},
            {0, -1, -1, -1},
            {0, 1, -1, -1},
            {1, -1, -1, -1},
            {false}, {true}}

            , {
            {-1, -1, -1, 0},
            {-1, -1, -1, 0},
            {-1, -1, 1, 1},
            {-1, -1, -1, 1},
            {false}, {true}}

            , {
            {-1, -1, -1, -1},
            {1, -1, 1, 0},
            {1, 1, 1, 1},
            {0, -1, -1, -1},
            {false}, {true}}

            , {
            {0, 0, 1, 1},
            {0, -1, 1, 1},
            {0, -1, -1, 1},
            {0, -1, 0, 0},
            {true}, {true}}

            , {
            {-1, -1, -1, 0},
            {1, -1, 1, -1},
            {0, 1, 1, -1},
            {0, 0, 1, -1},
            {false}, {true}}

            , {
            {-1, 1, 1, 1},
            {1, 1, 1, 0},
            {1, 1, -1, 0},
            {1, 0, 0, 0},
            {true}, {true}}

            , {
            {-1, 1, 1, 1},
            {1, 1, 1, 0},
            {1, 1, -1, 0},
            {1, 0, 0, 0},
            {true}, {true}}

            , {
            {1, 1, 1, 0},
            {1, 1, 1, -1},
            {1, 1, -1, -1},
            {1, -1, -1, -1},
            {true}, {true}}

            , {
            {-1, -1, -1, 0},
            {1, -1, -1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {true}, {true}}

            , {
            {-1, 1, 1, 1},
            {-1, -1, -1, -1},
            {-1, 1, 1, -1},
            {0, 0, 1, -1},
            {false}, {true}}

            , {
            {1, 1, 1, 1},
            {-1, 1, 1, 1},
            {-1, -1, 1, 1},
            {1, 0, -1, 1},
            {true}, {true}}

            , {
            {1, 1, 1, 0},
            {1, 1, -1, -1},
            {1, 1, -1, -1},
            {1, 1, 1, -1},
            {true}, {true}}

            , {
            {0, -1, -1, -1},
            {1, 1, 1, -1},
            {1, 1, -1, -1},
            {0, -1, -1, -1},
            {false}, {true}}

            , {
            {0, 0, -1, 0},
            {1, 1, -1, 0},
            {1, 1, 1, 1},
            {1, 0, -1, 1},
            {true}, {true}}

    };
    private static final Object[][][] ganaOtro = {{
            {-1, -1, -1, -1},
            {-1, -1, 1, -1},
            {-1, -1, -1, -1},
            {1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, 1, 1, 1},
            {-1, -1, 1, 1},
            {-1, -1, -1, 0},
            {0, 0, 0, 0},
            {true}, {true}}

            , {
            {0, 0, 1, 0},
            {0, 1, 1, -1},
            {1, 1, 1, -1},
            {0, -1, 0, -1},
            {false}, {true}}

            , {
            {-1, -1, -1, 1},
            {-1, -1, 1, -1},
            {-1, -1, -1, -1},
            {1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, 1, 1, 1},
            {-1, 1, 1, -1},
            {-1, 1, -1, -1},
            {-1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, 1},
            {1, -1, -1, -1},
            {1, 1, -1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, -1},
            {-1, 1, -1, -1},
            {-1, -1, 1, -1},
            {-1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, 0, 0, 0},
            {-1, 1, 1, 0},
            {-1, 1, 1, 0},
            {0, 0, 1, 0},
            {false}, {true}}

            , {
            {0, 0, 0, 0},
            {0, -1, -1, -1},
            {1, 1, -1, -1},
            {1, 1, 1, -1},
            {true}, {true}}

            , {
            {-1, -1, -1, -1},
            {1, -1, 1, -1},
            {1, 1, -1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {-1, 1, 1, -1},
            {-1, -1, 1, -1},
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, -1},
            {1, -1, 1, -1},
            {1, 1, -1, -1},
            {1, 1, -1, -1},
            {true}, {false}}

            , {
            {1, 0, 1, -1},
            {1, 1, -1, -1},
            {1, -1, 1, 0},
            {-1, -1, 1, 1},
            {false}, {true}}

            , {
            {-1, -1, -1, 1},
            {-1, -1, -1, -1},
            {-1, 1, -1, -1},
            {-1, -1, -1, -1},
            {true}, {false}}

            , {
            {1, 1, 1, -1},
            {0, 1, -1, -1},
            {1, -1, 1, 1},
            {-1, -1, 0, 1},
            {false}, {true}}

            , {
            {-1, 1, 1, 1},
            {-1, 1, 1, 1},
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, 1},
            {-1, -1, -1, 1},
            {-1, 1, -1, 1},
            {-1, -1, -1, -1},
            {true}, {false}}

            , {
            {0, 1, 0, 1},
            {-1, 1, 1, 1},
            {-1, 1, 1, 1},
            {-1, -1, -1, -1},
            {false}, {true}}

            , {
            {1, 1, 1, -1},
            {1, -1, -1, -1},
            {1, -1, -1, -1},
            {1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, 1},
            {1, -1, 1, 1},
            {1, -1, -1, -1},
            {1, -1, -1, 1},
            {true}, {false}}

    };
    private static final Object[][][] agotado = {{
            {-1, -1, -1, 0},
            {-1, 1, 1, 0},
            {-1, 1, 1, 1},
            {0, 0, 0, 0},
            {false}, {true}}

            , {
            {-1, -1, -1, 0},
            {-1, -1, 1, 0},
            {-1, 1, 1, 1},
            {1, 1, 1, -1},
            {false}, {true}}

            , {
            {-1, -1, -1, 0},
            {-1, 1, 1, 0},
            {-1, 1, 1, 1},
            {0, 0, 0, 0},
            {false}, {true}}

            , {
            {-1, -1, -1, -1},
            {1, -1, 1, -1},
            {1, 1, 1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, 0},
            {1, -1, 1, 0},
            {1, 1, -1, 1},
            {1, 0, -1, 0},
            {false}, {true}}

            , {
            {-1, 1, 1, 1},
            {-1, -1, 1, 1},
            {-1, -1, 1, 1},
            {1, -1, -1, -1},
            {true}, {false}}

            , {
            {-1, -1, -1, 1},
            {-1, 1, 1, 1},
            {-1, 1, 1, 1},
            {-1, -1, 0, 0},
            {false}, {true}}

            , {
            {-1, -1, -1, 1},
            {-1, -1, 1, 1},
            {-1, 1, 1, 1},
            {0, 1, 0, -1},
            {false}, {true}}

            , {
            {-1, -1, -1, 0},
            {1, 1, 1, -1},
            {0, 1, 1, -1},
            {0, 0, 1, -1},
            {false}, {true}}

            , {
            {-1, 1, -1, -1},
            {-1, 1, -1, 1},
            {-1, -1, 1, 1},
            {-1, 1, 1, 1},
            {true}, {false}}

            , {
            {-1, -1, -1, 0},
            {-1, -1, 1, 0},
            {-1, 1, 1, 1},
            {1, 1, 1, -1},
            {false}, {true}}

            , {
            {-1, -1, -1, -1},
            {1, -1, 1, -1},
            {1, 1, 1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {1, 1, 1, -1},
            {-1, 1, -1, -1},
            {-1, -1, 1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {-1, 1, 0, 0},
            {-1, 1, 1, 0},
            {-1, 1, 1, 1},
            {0, -1, -1, -1},
            {false}, {true}}

            , {
            {-1, -1, -1, 1},
            {-1, -1, 1, 1},
            {-1, 1, -1, 1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {1, 1, 1, -1},
            {1, -1, -1, -1},
            {1, -1, -1, -1},
            {1, 1, 1, -1},
            {true}, {false}}

            , {
            {1, -1, -1, -1},
            {0, 1, -1, -1},
            {1, 1, 1, 0},
            {-1, 0, 1, 0},
            {false}, {true}}

            , {
            {-1, -1, -1, 0},
            {-1, 1, 1, 0},
            {-1, 1, 1, 0},
            {0, 0, 1, 0},
            {false}, {true}}

            , {
            {-1, -1, -1, 0},
            {-1, -1, 1, 0},
            {-1, 1, 1, 0},
            {1, 1, 1, 0},
            {false}, {true}}

            , {
            {0, 1, 0, 0},
            {0, 1, 1, -1},
            {0, 1, 1, -1},
            {0, -1, -1, -1},
            {false}, {true}}

    };


}
