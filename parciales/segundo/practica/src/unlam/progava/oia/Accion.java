package unlam.progava.oia;

public class Accion {

	private char accion;
	private int computadora1;
	private int computadora2;

	public Accion(String[] campos) {
		accion = campos[0].charAt(0);
		if (campos.length > 1) {
			computadora1 = Integer.parseInt(campos[1]);
			computadora2 = Integer.parseInt(campos[2]);
		}
	}

	public char getAccion() {
		return accion;
	}

	public int getComputadora1() {
		return computadora1;
	}

	public int getComputadora2() {
		return computadora2;
	}

}
