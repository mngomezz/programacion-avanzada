package algoritmos;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * 9
7 8 1
6 7 2
2 3 2
3 6 4
0 1 4
2 7 6
2 8 7
3 4 7
1 3 8
0 8 8
4 5 9
5 6 10
1 8 11
4 6 14
 * */
/* PARA QUE: Halla el arbol de menor costo que abarque todos los nodos
 * 
 * PARAMETROS: Recibe la matriz de adyacencia del grafo
 * RETORNO: El arbol de menor costo, es decir, otra matriz de adyacencia
 * 
 * NOTA: A diferencia de Prim, Kruskal agrupa nodos segun aristas de menor peso
 * */
public class Kruskal {

	private MatrizDeAdyacencia matrizResultado;
	private ArrayList<Arista> listaAristas;
	private UnionFind unionfind;
	private int cantNodos;
	private int costoMinimo = 0;

	public int getCostoMinimo() {
		return costoMinimo;
	}

	public Kruskal(ArrayList<Arista> aristas, int cantNodos) {
		this.listaAristas = aristas;
		this.cantNodos = cantNodos;
		this.matrizResultado = new MatrizDeAdyacencia(cantNodos);
	}

	public MatrizDeAdyacencia kruskal() {

		PriorityQueue<Arista> colaAristas = new PriorityQueue<Arista>(this.listaAristas.size());
		colaAristas.addAll(listaAristas);
//		for (Arista arista : this.listaAristas) {
//			colaAristas.add(arista);
//		}

		unionfind = new UnionFind(cantNodos);

		/* INI Plan de Electrificacion */
		// centralesElectricas es leido del archivo.
		int[] centrales = { 0, 3 };
		for (int nodo = 0; nodo < centrales.length - 1; nodo++) {
			// Primero uno las centrales, asi sus aristas NO son tomadas en cuenta
			unionfind.union(centrales[nodo], centrales[nodo + 1]);
		}
		/* FIN Plan de Electrificacion */

		int i = 0;
		final int NODOS_A_CHEQUEAR = (cantNodos - 1) - (centrales.length - 1);
		while (i < NODOS_A_CHEQUEAR) {

			Arista arista = colaAristas.remove(); // Quita la arista de menor costo

			int representanteOrigen = unionfind.find(arista.getOrigen());
			int representanteDestino = unionfind.find(arista.getDestino());

			if (representanteOrigen != representanteDestino) {
				i++;
				unionfind.union(representanteOrigen, representanteDestino);
				matrizResultado.conectarNodos(arista.getOrigen(), arista.getDestino(), arista.getCosto(), false);
				costoMinimo += arista.getCosto();
			}
		}

		matrizResultado.setCostoMinimo(costoMinimo);

		return matrizResultado;
	}
}
