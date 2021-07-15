package excepciones;

public class NoHayMasDominos extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoHayMasDominos() {
		super("No hay siguiente domino");
	}

}
