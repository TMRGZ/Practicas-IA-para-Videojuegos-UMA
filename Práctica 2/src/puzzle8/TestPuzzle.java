package puzzle8;

import java.util.List;
/**
 * Pruebas para el puzzle 2D de piezas deslizantes.
 * 
 * @Jose Miguel Horcas Aguilera, Lorenzo Mandow
 *
 */
public class TestPuzzle {
    
    public static void main(String[] args) {
    	/*testH0();
    	testDPB();
    	testPD();
    	testMAN();*/

    	testNIteraciones();
    }

    private static void testNIteraciones() {
        int nCom = 5;

        System.out.println("--------------MAPA 1----------------");
        int[][] ttt1 = {{3,5,0},{6,2,4},{7,8,1}};
        Puzzle s1 = new Puzzle(ttt1);
        AgentePuzzleH0 problemaH0 = new AgentePuzzleH0(s1);
        AgentePuzzleBDP problemaBDP = new AgentePuzzleBDP(s1, nCom);
        AgentePuzzlePD problemaPD = new AgentePuzzlePD(s1, nCom);
        AgentePuzzleMAN problemaMAN = new AgentePuzzleMAN(s1, nCom);
        //System.out.println(problemaMAN.h(s1));

        System.out.println("A ciegas");
        List<Puzzle> H0 = problemaH0.aMono();
        System.out.println("BDP");
        List<Puzzle> BDP = problemaBDP.aMono();
        System.out.println("Piezas Descolocadas");
        List<Puzzle> PD = problemaPD.aMono();
        System.out.println("Manhattan");
        List<Puzzle> MAN = problemaMAN.aMono();

        System.out.println("--------------MAPA 2----------------");
        int[][] ttt2 = {{8,0,7},{4,1,6},{2,5,3}};
        Puzzle s2 = new Puzzle(ttt2);
        AgentePuzzleH0 problemaH02 = new AgentePuzzleH0(s2);
        AgentePuzzleBDP problemaBDP2 = new AgentePuzzleBDP(s2, nCom);
        AgentePuzzlePD problemaPD2 = new AgentePuzzlePD(s2, nCom);
        AgentePuzzleMAN problemaMAN2 = new AgentePuzzleMAN(s2, nCom);

        System.out.println("A ciegas");
        List<Puzzle> H02 = problemaH02.aMono();
        System.out.println("BDP");
        List<Puzzle> BDP2 = problemaBDP2.aMono();
        System.out.println("Piezas Descolocadas");
        List<Puzzle> PD2 = problemaPD2.aMono();
        System.out.println("Manhattan");
        List<Puzzle> MAN2 = problemaMAN2.aMono();

    }

    public static void testH0(){
        System.out.println("Test H0");

        //    Puzzle s = new Puzzle(3,3);
        int[][] ttt = {{3,4,5},{6,7,8},{1,2,0}};
        Puzzle s = new Puzzle(ttt);
		AgentePuzzleH0 problema = new AgentePuzzleH0(s);
		
		List<Puzzle> l =  problema.aMono();
		
		if (l != null) {
			System.out.println("Profundidad de la solucion: " + (l.size()-1));
            System.out.println("Camino Solucion:");
            for (Puzzle e : l) {
               e.ver();
            }
        } else {
            System.out.println("No se ha podido encontrar la Solucion");
        }        
	}
	
	public static void testDPB() {
        System.out.println("Test DPB");

        //Puzzle s = new Puzzle(3,3);
        int[][] ttt = {{3,4,5},{6,7,8},{1,2,0}};
        Puzzle s = new Puzzle(ttt);
		AgentePuzzleBDP problema = new AgentePuzzleBDP(s, 5);

		
		List<Puzzle> l = problema.aMono();
		
		if (l != null) {
			System.out.println("Profundidad de la solucion: " + (l.size()-1));
            System.out.println("Camino Solucion:");
            for (Puzzle e : l) {
               e.ver();
            }
        } else {
            System.out.println("No se ha podido encontrar la Solucion");
        }        
	}

    public static void testPD() {
        System.out.println("Test piezas descolocadas");

        //Puzzle s = new Puzzle(3,3);
        int[][] ttt = {{3,4,5},{6,7,8},{1,2,0}};
        Puzzle s = new Puzzle(ttt);
        AgentePuzzlePD problema = new AgentePuzzlePD(s, 5);

        List<Puzzle> l = problema.aMono();

        if (l != null) {
            System.out.println("Profundidad de la solucion: " + (l.size()-1));
            System.out.println("Camino Solucion:");
            for (Puzzle e : l) {
                e.ver();
            }
        } else {
            System.out.println("No se ha podido encontrar la Solucion");
        }
    }

    public static void testMAN() {
        System.out.println("Test Manhattan");

        //Puzzle s = new Puzzle(3,3);
        int[][] ttt = {{3,4,5},{6,7,8},{1,2,0}};
        Puzzle s = new Puzzle(ttt);
        AgentePuzzleMAN problema = new AgentePuzzleMAN(s, 5);

        List<Puzzle> l = problema.aMono();

        if (l != null) {
            System.out.println("Profundidad de la solucion: " + (l.size()-1));
            System.out.println("Camino Solucion:");
            for (Puzzle e : l) {
                e.ver();
            }
        } else {
            System.out.println("No se ha podido encontrar la Solucion");
        }
    }
	
}
