package algoritmos;

import java.util.LinkedList;
import java.util.List;

/* PARA QUE: Halla el camino mas corto desde un nodo inicial (origen) hacia
 *  el resto de nodos del grafo
 * 
 * PARAMETROS: Recibe una matriz de adyacencia y un nodo origen (source)
 * RETORNO: Lista de nodos que conforman el camino mas corto hacia un nodo
 *  particular(indicado por parametro)
 *  
 * NOTA: Este algoritmo primero calcula distancia y camino a todos los nodos
 *  y luego se pregunta por un objetivo en particular
 * */
public class Dijkstra {

	private static final int INF = Integer.MAX_VALUE;
	private MatrizDeAdyacencia matriz;
	private int nodoInicial;
	private int[] distancias;
	private int[] predecesores;
	private List<Integer> nodosVisitados = new LinkedList<Integer>();
	private List<Integer> nodosRestantes = new LinkedList<Integer>();

	public Dijkstra(MatrizDeAdyacencia matriz, int nodoInicial) {
		this.matriz = matriz;
		this.nodoInicial = nodoInicial - 1; // ajusto offset

		distancias = new int[matriz.getSize()];
		predecesores = new int[matriz.getSize()];
		dijkstra();
	}

	private void dijkstra() {

		nodosVisitados.add(nodoInicial);

		for (int i = 0; i < matriz.getSize(); i++) {
			if (i != nodoInicial)
				nodosRestantes.add(i);
		}

		int[] adyacentes = matriz.obtenerAdyacentes(nodoInicial);

		for (int i = 0; i < adyacentes.length; i++) {
			distancias[i] = adyacentes[i];
			predecesores[i] = (adyacentes[i] == INF) ? INF : nodoInicial;
		}
		// [0][MAX][MAX]
		// [0][3][1][0]
		while (!nodosRestantes.isEmpty()) {

			int nodoMasCercano = encontrarNodoMasCercano();

			adyacentes = matriz.obtenerAdyacentes(nodoMasCercano);

			nodosVisitados.add(nodoMasCercano);
			nodosRestantes.remove(nodosRestantes.indexOf(nodoMasCercano));

			for (Integer nodo : nodosRestantes) {
				if (adyacentes[nodo] != INF) {
					int distanciaPrevia = distancias[nodo];
					distancias[nodo] = Math.min(distanciaPrevia, distancias[nodoMasCercano] + adyacentes[nodo]);
					if (distanciaPrevia != distancias[nodo])
						predecesores[nodo] = nodoMasCercano;
				}
			}
		}
	}

	private int encontrarNodoMasCercano() {

		int min = nodosRestantes.get(0);

		for (Integer nodo : nodosRestantes) {
			if (distancias[nodo] < distancias[min])
				min = nodo;
		}

		return min;
	}

	// Obtengo el camimo para la matriz ya cargada
	public LinkedList<Integer> obtenerCamino(final int objetivo) {
		final int objetivoReal = objetivo - 1;
		if (predecesores[objetivoReal] == INF)
			return null;

		LinkedList<Integer> camino = new LinkedList<Integer>();
		camino.push(objetivoReal);
		int nodo = objetivoReal;

		while (predecesores[nodo] != INF && predecesores[nodo] != nodoInicial) {
			camino.push(predecesores[nodo]);
			nodo = predecesores[nodo];
		}

		camino.push(nodoInicial);

		return camino;
	}
	
	public int obtenerCosto(final int objetivo) {
		return distancias[objetivo];
	}

	// Obtengo el camino hacia el objetivo desde el origen para la matriz ya cargada
	public LinkedList<Integer> dijkstra(int origen, int objetivo) {
		if (origen != nodoInicial) {
			// Si pido por un origen distinto, entonces primero calculo.
			nodoInicial = origen;
			dijkstra();
		}
		return obtenerCamino(objetivo);
	}

	// Calculo minimo costo de ir del origen al objetivo para un grafo
	public LinkedList<Integer> dijkstra(MatrizDeAdyacencia grafo, int origen, int objetivo) {
		matriz = grafo;
		nodoInicial = origen;
		distancias = new int[matriz.getSize()];
		predecesores = new int[matriz.getSize()];
		dijkstra();
		return obtenerCamino(objetivo);
	}

}
