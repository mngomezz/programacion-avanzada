package lanzandoElCaber;

import java.util.Comparator;

public class ConsistenciaComparator implements Comparator<Concursante> {

	@Override
	public int compare(Concursante unConcursante, Concursante otroConcursante) {
		int result = Integer.compare(unConcursante.obtenerConsistencia(), otroConcursante.obtenerConsistencia());
		if(result == 0) {
			result = Float.compare(unConcursante.obtenerDistanciaTotal(), otroConcursante.obtenerDistanciaTotal());
		}
		
		return result;
	}
}
