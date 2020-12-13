package MundoCartas;

import agentesolitario.AgenteSolitario;

import java.util.List;

public class AgenteCartas extends AgenteSolitario<EstadoCartas> {
    /**
     * Guarda el estado de salida.
     *
     * @param estadoCartas estado de salida
     */
    protected AgenteCartas(EstadoCartas estadoCartas) {
        super(estadoCartas);
    }

    @Override
    public boolean esFinal(EstadoCartas estadoCartas) {
        return ordenadoContinuo(estadoCartas.getColA(), estadoCartas.getColB());
    }

    private boolean ordenadoContinuo(List<Integer> listA, List<Integer> listB){
        int cont = 1;

        for (Integer integer : listA) {
            if (integer != cont) return false;
            cont++;
        }

        for (Integer integer : listB) {
            if (integer != cont) return false;
            cont++;
        }
        return true;
    }
}
