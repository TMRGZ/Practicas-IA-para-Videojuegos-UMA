package MundoRio;

import agentesolitario.AgenteSolitario;

public class AgenteRio extends AgenteSolitario<EstadoRio> {
    /**
     * Guarda el estado de salida.
     *
     * @param estadoRio estado de salida
     */
    protected AgenteRio(EstadoRio estadoRio) {
        super(estadoRio);
    }

    @Override
    public boolean esFinal(EstadoRio estadoRio) {
        return estadoRio.getDerCanibal() == 3 && estadoRio.getDerMisionero() == 3;
    }
}
