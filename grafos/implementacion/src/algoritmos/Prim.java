package algoritmos;

import java.util.ArrayList;
import java.util.List;

/* PARA QUE: Halla el arbol de menor costo que abarque todos los nodos
 * 
 * PARAMETROS: Recibe la matriz de adyacencia del grafo
 * RETORNO: El arbol de menor costo, es decir, otra matriz de adyacencia
 * 
 * NOTA: A diferencia de Kruskal, Prim parte en un nodo y calcula el resto
 * */
public class Prim {

	private MatrizDeAdyacencia matriz;
	private MatrizDeAdyacencia arbol;
	private List<Integer> nodosNoAbarcados = new ArrayList<Integer>();
	private List<Integer> nodosYaAbarcados = new ArrayList<Integer>();
	int costoTotal = 0;

	public Prim(MatrizDeAdyacencia matriz) {
		this.matriz = matriz;
		this.arbol = new MatrizDeAdyacencia(matriz.getSize());

		// conjuntoG = [0][1][2][3][4][5][6][7][8]
		for (int i = 0; i < matriz.getSize(); i++)
			nodosNoAbarcados.add(i);
	}

	public MatrizDeAdyacencia prim() {

		int[] adyacentes;

		int nodoConectado = 0; // Siempre arranco del nodo 0 de la matriz

		nodosYaAbarcados.add(nodoConectado);
		nodosNoAbarcados.remove(nodosNoAbarcados.indexOf(nodoConectado));

		int[][] matrizResultado = arbol.getMatriz();

		while (!nodosNoAbarcados.isEmpty()) {

			int aristaMinima = Integer.MAX_VALUE;
			int nodoQueConecta = 0;

			for (Integer nodoYaAbarcado : nodosYaAbarcados) {
				adyacentes = matriz.obtenerAdyacentes(nodoYaAbarcado);
				for (Integer NodoNoAbarcado : nodosNoAbarcados) {
					if (adyacentes[NodoNoAbarcado] < aristaMinima) {
						aristaMinima = adyacentes[NodoNoAbarcado];
						nodoConectado = NodoNoAbarcado;
						nodoQueConecta = nodoYaAbarcado;
					}
				}
			}

			if (aristaMinima != Integer.MAX_VALUE) {
				costoTotal += aristaMinima;
				matrizResultado[nodoQueConecta][nodoConectado] = aristaMinima;
				matrizResultado[nodoConectado][nodoQueConecta] = aristaMinima;
			}

			nodosYaAbarcados.add(nodoConectado);
			nodosNoAbarcados.remove(nodosNoAbarcados.indexOf(nodoConectado));
		}

		arbol.setCostoMinimo(costoTotal);

		return arbol;
	}

}
