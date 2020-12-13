package MundoTorres;

import agentesolitario.AgenteSolitario;

public class AgenteTorres extends AgenteSolitario<EstadoTorre> {

    /**
     * Guarda el estado de salida.
     *
     * @param estadoTorre estado de salida
     */
    protected AgenteTorres(EstadoTorre estadoTorre) {
        super(estadoTorre);
    }

    @Override
    public boolean esFinal(EstadoTorre estadoTorre) {
        return estadoTorre.torreA.isEmpty() && estadoTorre.torreB.isEmpty() && !estadoTorre.torreC.isEmpty();
    }
}
