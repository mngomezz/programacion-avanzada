package dominio;

import java.util.Comparator;

public class NumeroDominoComparator implements Comparator<Jugador> {
	
	@Override
	public int compare(Jugador primerJugador, Jugador segundoJugador) {
		return Integer.compare(primerJugador.getDominoElegido().getNumero(), segundoJugador.getDominoElegido().getNumero());
	}
}
