package dominio;
import java.util.Comparator;

/* Esta comparacion se utiliza para ordenar los dominos en la mesa.
 * En la mesa se ordenan de menor a mayor ya que asi al elegir uno
 * de mayor numero (con mas coronas) en la proxima ronda ese jugador
 * elegiria ultimo.
 */
public class DominosEnJuegoComparator implements Comparator<Domino> {
	
	@Override
	public int compare(Domino primerDomino, Domino segundoDomino) {
		return Integer.compare(primerDomino.getNumero(), segundoDomino.getNumero());
	}
	
}
