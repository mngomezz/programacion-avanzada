package algoritmos;

public class Arista implements Comparable<Arista> {

	private int origen;
	private int destino;
	private int costo;

	public Arista(int origen, int destino, int costo) {
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}

	@Override
	public int compareTo(Arista o) {
		return this.costo - o.costo;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

}