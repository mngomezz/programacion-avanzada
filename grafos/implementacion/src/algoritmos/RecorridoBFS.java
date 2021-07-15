package algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* PARA QUE: Halla la distancia al nodo de origen
 * 
 * PARAMETROS: Recibe la matriz de adyacencia del grafo
 * RETORNO: El arbol de menor costo, es decir, otra matriz de adyacencia
 * 
 * NOTA: A diferencia de Kruskal, Prim parte en un nodo y calcula el resto
 * */
public class RecorridoBFS {
	private static int GRAFO_SIZE;
	private static final int INF = Integer.MAX_VALUE;
	private MatrizDeAdyacencia grafo;
	private MatrizDeAdyacencia arbol;

	// en distanciaNodos guardamos la distancia de cada nodo frente
	// al nodo de origen. En un principio, toda distancia es INF.
	// Por ende, si la distancia != INF ==> el nodo ya fue visitado.
	private List<Integer> distanciaNodos = new ArrayList<Integer>();

	private Queue<Integer> nodosPorVisitar = new LinkedList<Integer>();

	public RecorridoBFS(MatrizDeAdyacencia grafo) {
		this.grafo = grafo;
		GRAFO_SIZE = grafo.getSize();
		this.arbol = new MatrizDeAdyacencia(GRAFO_SIZE);

		// En este loop se inicializa el array de distancias
		// Que una distancia sea INF indica que el nodo NO fue visitado
		for (int i = 0; i < GRAFO_SIZE; i++) {
			distanciaNodos.add(INF);
		}

	}

	public void recorrerGrafo() {
		// Mientras haya un nodo el cual no haya visitado
		while (distanciaNodos.contains(INF)) {

			// Mientras haya adyacentes del nodoActual por visitar
			while (!nodosPorVisitar.isEmpty()) {

				int nodoActual = nodosPorVisitar.poll();
				int[] adyacentes = grafo.obtenerAdyacentes(nodoActual);

				for (int i = 0; i < GRAFO_SIZE; i++) {

					if (adyacentes[i] != INF) {
						if (distanciaNodos.get(i) == INF) {
							distanciaNodos.set(i, distanciaNodos.get(nodoActual) + 1);
							nodosPorVisitar.add(i);
							arbol.conectarNodos(nodoActual, i, adyacentes[i], false);
						}
					}
				}
			}

			// Obtengo el proximo nodo SIN visitar
			for (int i = 0; i < GRAFO_SIZE; i++) {
				if (distanciaNodos.get(i) == INF) {
					nodosPorVisitar.add(i); // agrego a la cola de nodos a visitar
					distanciaNodos.set(i, 0); // marco como visitado
					break;
				}
			}

		}

	}
}
