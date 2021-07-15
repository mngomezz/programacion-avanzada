package unlam.progava.oia;

public class Arista {

	private int nodoSalida;
	private int nodoLlegada;

	public Arista(int salida, int llegada) {
		this.setNodoSalida(salida);
		this.setNodoLlegada(llegada);
	}

	public int getNodoSalida() {
		return nodoSalida;
	}

	public void setNodoSalida(int nodo1) {
		this.nodoSalida = nodo1;
	}

	public int getNodoLlegada() {
		return nodoLlegada;
	}

	public void setNodoLlegada(int nodo2) {
		this.nodoLlegada = nodo2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (nodoSalida != other.nodoSalida)
			return false;
		if (nodoLlegada != other.nodoLlegada)
			return false;
		return true;
	}

}
