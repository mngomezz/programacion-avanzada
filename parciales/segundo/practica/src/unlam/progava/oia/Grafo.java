package unlam.progava.oia;

import java.util.ArrayList;

public class Grafo {

	private ArrayList<Arista> aristas;
	private int cantNodos;

	public Grafo(final int SIZE) {
		aristas = new ArrayList<Arista>();
		cantNodos = SIZE;
	}

	public void ponerArista(int nodo1, int nodo2) {
		aristas.add(new Arista(nodo1, nodo2));
		aristas.add(new Arista(nodo2, nodo1));
	}

	public boolean hayArista(int nodo1, int nodo2) {
		for (Arista arista : aristas) {
			if (arista.getNodoSalida() == nodo1) {
				if (arista.getNodoLlegada() == nodo2) {
					return true;
				}
			} else if (arista.getNodoSalida() == nodo2) {
				if (arista.getNodoLlegada() == nodo1) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean estaEnAdyacentes(int nodo, int adyacentes[]) {
		for (int n = 0; n < adyacentes.length; n++) {
			if (adyacentes[n] == nodo) {
				return true;
			}
		}
		return false;
	}

	public int getCantNodos() {
		return cantNodos;
	}
}
