package unlam.progava.oia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Algoritmo {

	private static final int MAX_COMPUTADORAS = 1000000;
	private Grafo grafo;
	private ArrayList<Accion> acciones;
	private String salida;

	public Algoritmo(ArrayList<Accion> actions) {
		grafo = new Grafo(MAX_COMPUTADORAS);
		acciones = actions;
		salida = "";
	}

	public void calcular() {

		for (Accion accion : acciones) {
			if (accion.getAccion() == 'F') {
				break;
			}

			int compu1 = accion.getComputadora1();
			int compu2 = accion.getComputadora2();

			if (accion.getAccion() == 'C') {
				grafo.ponerArista(compu1, compu2);
				grafo.ponerArista(compu2, compu1);
			} else if (accion.getAccion() == 'P') {
				BFS bfs = new BFS(compu1);
				int adyacentes[] = bfs.ejecutar();
				boolean esAdyacente1 = grafo.estaEnAdyacentes(compu1, adyacentes);
				boolean esAdyacente2 = grafo.estaEnAdyacentes(compu2, adyacentes);

				salida += (esAdyacente1 && esAdyacente2) ? "S" : "N";
				salida += "\n";
			}
		}

	}

	public String getSalida() {
		return salida;
	}

	private class BFS {
		private int nodoInicial;
		private ArrayList<Arista> arbol;
		private boolean[] nodoVisitado;

		public BFS(int origen) {
			nodoInicial = origen;
			arbol = new ArrayList<Arista>();
			nodoVisitado = new boolean[grafo.getCantNodos()];
		}

		public int[] ejecutar() {
			Queue<Integer> nodosAVisitar = new LinkedList<Integer>();
			nodosAVisitar.offer(this.nodoInicial);
			int nodo;
			while (!nodosAVisitar.isEmpty()) {
				nodo = nodosAVisitar.peek();
				for (int i = 0; i < grafo.getCantNodos(); i++) {
					if (nodo != i && !nodoVisitado[i]) {
						if (grafo.hayArista(nodo, i)) {
							arbol.add(new Arista(nodo, i));
							nodosAVisitar.offer(i);
							nodoVisitado[i] = true;
						}
					}
				}
				nodo = nodosAVisitar.poll();
				this.nodoVisitado[nodo] = true;
			}

			int i = 0;
			for (int j = 0; j < arbol.size(); j++) {
				i += 3;
			}
			int adyacentes[] = new int[i];
			i = 0;
			for (Arista rama : arbol) {
				i++;
				adyacentes[i] = rama.getNodoSalida();
				i++;
				adyacentes[i] = rama.getNodoLlegada();
			}

			return adyacentes;
		}
	}
}