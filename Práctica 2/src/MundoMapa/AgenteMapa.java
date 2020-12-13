package MundoMapa;

import agentesolitario.AgenteSolitario;

public class AgenteMapa extends AgenteSolitario<EstadoMapa> {
    /**
     * Guarda el estado de salida.
     *
     * @param estadoMapa estado de salida
     */
    protected AgenteMapa(EstadoMapa estadoMapa) {
        super(estadoMapa);
    }

    @Override
    public int h(EstadoMapa estadoMapa) {
        int deltaF = Math.abs(estadoMapa.x - EstadoMapa.destx);
        int deltaC = Math.abs(estadoMapa.y - EstadoMapa.desty);

        int max = Math.max(deltaC, deltaF);
        int min = Math.min(deltaC, deltaF);

        return 1420 * min + 1000 * (max - min);
    }

    @Override
    public boolean esFinal(EstadoMapa estadoMapa) {
        return estadoMapa.x == EstadoMapa.destx && estadoMapa.y == EstadoMapa.desty;
    }
}
