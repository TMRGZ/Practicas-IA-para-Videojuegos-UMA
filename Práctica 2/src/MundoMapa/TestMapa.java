package MundoMapa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestMapa {
    public static void main(String[] args) {
        File f = new File("AR0011SR.map");
        int destx = 104, desty = 166;
        int inix = 77, iniy = 192;
        //int destx = 394, desty = 359;
        //int inix = 370, iniy = 244;
        char[][] mapa = crearMapaMatriz(f);
        //verMapa(mapa, 512, 512);

        System.out.println("Estado inicial");
        EstadoMapa estadoMapa = new EstadoMapa(mapa, inix, iniy, destx, desty);
        estadoMapa.ver();

        List<EstadoMapa> suc = estadoMapa.calculaSucesores();
        System.out.println("Sucesores");
        for (EstadoMapa estadoTorre : suc) {
            estadoTorre.ver();
        }

        System.out.println("Solucionando");
        AgenteMapa agenteMapa = new AgenteMapa(estadoMapa);
        List<EstadoMapa> sol = agenteMapa.aMono();

        /*if (sol != null) {
            System.out.println("----");
            for (EstadoMapa estadoMapa1 : sol) {
                estadoMapa1.ver();

            }
            System.out.println("----");
        } else {
            System.out.println("Sol no encontrada");
        }*/

        TreeSet<Integer> listX = new TreeSet<>();
        TreeSet<Integer> listY = new TreeSet<>();

        for (EstadoMapa estadoMapa3 : sol) {
            listX.add(estadoMapa3.x);
            listY.add(estadoMapa3.y);
        }

        verMapa(creaMapaSol(mapa, sol, listX.last(), listY.last(), listX.first(), listY.first()), listX.last(), listY.last());
    }

    private static char[][] crearMapaMatriz(File f) {
        char[][] mapa = new char[512][512];

        try (Scanner sc = new Scanner(f)) {
            sc.useDelimiter("\n");
            while (!sc.next().equals("map"));


            int i = 0;
            while (sc.hasNext()) {
                mapa[i] = sc.next().toCharArray();
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return mapa;
    }

    private static char[][] creaMapaSol(char[][] mapa, List<EstadoMapa> sol, int maxX, int maxY, int minX, int minY) {
        char[][] mapaSol = new char[maxX][maxY];

        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (mapa[i][j] == '@') {
                    mapaSol[i - minX][j - minY] = 'x';
                } else {
                    mapaSol[i - minX][j - minY] = ' ';
                }
            }
        }

        for (EstadoMapa estadoMapa : sol) {
            mapaSol[estadoMapa.x - minX][estadoMapa.y - minY] = 'o';
        }


        return mapaSol;
    }

    public static void verMapa(char[][] mapa, int limx, int limy) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < limx; i++) {
            for (int j = 0; j < limy; j++) {
                stringBuilder.append(mapa[i][j]);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
