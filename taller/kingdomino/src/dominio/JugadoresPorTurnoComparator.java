package dominio;

import java.util.Comparator;

public class JugadoresPorTurnoComparator implements Comparator<Jugador> {

	@Override
	public int compare(Jugador j1, Jugador j2) {
		return Integer.compare(j1.getTurno(), j2.getTurno());
	}

}
