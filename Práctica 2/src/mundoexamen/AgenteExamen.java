/**
 *  Miguel Angel Ruiz Gomez 48789569z
 */

package mundoexamen;

import agentesolitario.AgenteSolitario;

public class AgenteExamen extends AgenteSolitario<EstadoExamen> {
    /**
     * Guarda el estado de salida.
     *
     * @param estadoExamen estado de salida
     */
    protected AgenteExamen(EstadoExamen estadoExamen) {
        super(estadoExamen);
    }

    public boolean esFinal(EstadoExamen estadoExamen) {
        for (int i = 0; i < estadoExamen.getListaA().size(); i++) {
            if (!estadoExamen.getListaA().get(i).equals(estadoExamen.getListaB().get(i))){
                return false;
            }
        }
        return true;
    }
}
