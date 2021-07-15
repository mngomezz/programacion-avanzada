package lanzandoElCaber;

import java.util.Comparator;

public class DistanciaComparator implements Comparator<Concursante> {

	@Override
	public int compare(Concursante unConcursante, Concursante otroConcursante) {
		return Float.compare(unConcursante.obtenerDistanciaTotal(), otroConcursante.obtenerDistanciaTotal());
	}
}
