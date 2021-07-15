package dominio;

public class Posicion {
	public int fila = 0;
	public int columna = 0;

	public Posicion(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public boolean esAdyacente(Posicion otro) {
		if (fila == otro.fila) {
			if (Math.abs(columna - otro.columna) <= 1)
				return true;
		} else if (columna == otro.columna) {
			if (Math.abs(fila - otro.fila) <= 1)
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "(" + fila + ";" + columna + ")";
	}

	@Override
	public boolean equals(Object obj) {
		Posicion pos = (Posicion) obj;
		return pos.fila == this.fila && pos.columna == this.columna;
	}

}
