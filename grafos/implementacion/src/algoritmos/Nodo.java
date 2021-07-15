package algoritmos;
public class Nodo implements Comparable<Nodo> {
	
	private int nodo;
	private int grado;
	
	public Nodo(int nodo, int grado) {
		this.nodo = nodo;
		this.grado = grado;
	}

	public int getNodo() {
		return nodo;
	}

	public void setNodo(int nodo) {
		this.nodo = nodo;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	@Override
	public int compareTo(Nodo o) {
		return this.grado - o.grado;
	}

	@Override
	public String toString() {
		return "Nodo [nodo=" + nodo + ", grado=" + grado + "]";
	}
	
	
}