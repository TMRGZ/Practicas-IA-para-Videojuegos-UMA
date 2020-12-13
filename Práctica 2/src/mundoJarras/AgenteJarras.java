package mundoJarras;

import agentesolitario.AgenteSolitario;

public class AgenteJarras extends AgenteSolitario<estadoJarra> {

    /**
     * Guarda el estado de salida.
     *
     * @param estadoJarra estado de salida
     */
    protected AgenteJarras(estadoJarra estadoJarra) {
        super(estadoJarra);
    }

    @Override
    public boolean esFinal(estadoJarra estadoJarra) {
        return estadoJarra.jp == 1;
    }
}
