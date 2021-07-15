package oia2;

public class Mesada implements Comparable<Mesada> {
	private int ancho;
	private int largo;

	// Siempre el lado mayor es el largo
	public Mesada(int lado1, int lado2) {
		this.largo = Math.max(lado1, lado2);
		this.ancho = Math.min(lado1, lado2);
	}

	public int getAncho() {
		return ancho;
	}

	public int getLargo() {
		return largo;
	}

	// Este metodo indica si es necesario otra pila.
	public boolean esCompatible(Mesada o) {
		int difAncho = ancho - o.getAncho();
		int difLargo = largo - o.getLargo();

		if (difAncho >= 0 && difLargo >= 0)
			return true;

		if (difAncho <= 0 && difLargo <= 0)
			return true;

		return false;
	}

	public boolean esMasChica(Mesada o) {
		if (ancho <= o.getAncho() && largo <= o.getLargo())
			return true;

		return false;
	}

	private int getArea() {
		return ancho * largo;
	}

	@Override
	public int compareTo(Mesada o) {
		return getArea() - o.getArea();
	}

}
