package runnable;

import java.util.LinkedList;

import algoritmos.Dijkstra;
import algoritmos.Floyd;
import algoritmos.Kruskal;
import algoritmos.LectorDeGrafo;
import algoritmos.MatrizDeAdyacencia;
import algoritmos.Prim;
import algoritmos.RecorridoBFS;
import algoritmos.Warshall;

public class Main {
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		final int UNO = 1;

//		int[][] matriz = {
//				// 1 2 3 4 4
//				{ INF, 100, INF, 300, INF }, { INF, INF, 500, INF, INF }, { INF, INF, INF, INF, 100 },
//				{ INF, INF, 200, INF, 600 }, { INF, INF, INF, INF, INF }, };
////		
//		int [][] matriz2 = {
//            {INF,4,INF,INF,INF,INF,INF,INF,8},
//            {4,INF,INF,8,INF,INF,INF,INF,11},
//            {INF,INF,INF,2,INF,INF,INF,6,7},
//            {INF,8,2,INF,7,INF,4,INF,INF},
//            {INF,INF,INF,7,INF,9,INF,014,INF},
//            {INF,INF,INF,INF,9,INF,10,INF,INF},
//            {INF,INF,INF,4,INF,10,INF,2,INF},
//            {INF,INF,6,INF,14,INF,2,INF,1},
//            {8,11,7,INF,INF,INF,INF,1,INF},
//        };
//		
//		int[][] matrizTarzan = {
//			{INF,UNO,INF,INF,UNO,INF,INF,INF,INF,INF,INF,INF,INF},
//			{UNO,INF,UNO,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
//			{INF,UNO,INF,UNO,INF,UNO,INF,INF,INF,INF,INF,INF,INF},
//			{INF,INF,UNO,INF,INF,UNO,INF,INF,INF,INF,INF,INF,INF},
//			{UNO,INF,INF,INF,INF,UNO,INF,INF,INF,INF,INF,INF,INF},
//			{INF,INF,UNO,UNO,UNO,INF,UNO,INF,INF,INF,INF,INF,INF},
//			{INF,INF,INF,INF,INF,UNO,INF,UNO,INF,INF,INF,INF,INF},
//			{INF,INF,INF,INF,INF,INF,UNO,INF,UNO,INF,INF,INF,INF},
//			{INF,INF,INF,INF,INF,INF,INF,UNO,INF,UNO,UNO,INF,INF},
//			{INF,INF,INF,INF,INF,INF,INF,INF,UNO,INF,UNO,UNO,UNO},
//			{INF,INF,INF,INF,INF,INF,INF,INF,UNO,UNO,INF,UNO,INF},
//			{INF,INF,INF,INF,INF,INF,INF,INF,INF,UNO,UNO,INF,UNO},
//			{INF,INF,INF,INF,INF,INF,INF,INF,INF,UNO,INF,UNO,INF}
//		};
//		MatrizDeAdyacencia mat = new MatrizDeAdyacencia(matrizTarzan);
//		Dijkstra dijkstra = new Dijkstra(mat, 1);
//		System.out.println("Camino mas corto del nodo 0 al nodo 13: "+dijkstra.obtenerCamino(13));

//		MatrizDeAdyacencia mat2 = new MatrizDeAdyacencia(matriz2);
//		Prim prim = new Prim(mat2);
//		prim.prim().mostrarMatriz();

//		Kruskal kruskal = LectorDeGrafo.leerAristas("entrada00.txt");
//		kruskal.kruskal().mostrarMatriz();
//		System.out.println(kruskal.getCostoMinimo());
////		int[][] matrizFloyd = {
//			{ INF, 8, 5 ,INF},
//			{ 3, INF, INF,INF },
//			{ INF, 2, INF,INF },
//			{ INF, INF, INF,INF}
//		};
//		
		int[][] floyd = { { 0, 5, 10, 80, 90 }, { 5, 0, 70, 60, 50 }, { 10, 70, 0, 8, 20 }, { 80, 60, 8, 0, 10 },
				{ 90, 50, 20, 10, 0 } };
		MatrizDeAdyacencia mat = new MatrizDeAdyacencia(floyd);

		Floyd FloydAlgorithm = new Floyd(mat);

		FloydAlgorithm.floyd().mostrarMatriz();
		// System.out.println("-------");
//		floyd.floyd().mostrarMatriz();

//		int[][] matrizWarshall = {
//			{ INF, 800, 500 },
//			{ 300, INF, INF },
//			{ INF, 200, INF }
//		};
//		
//		MatrizDeAdyacencia mat = new MatrizDeAdyacencia(matrizWarshall);
//		
//		Warshall warshall = new Warshall(mat);
//		
//		warshall.warshall();

//        int[][] matrizColoreo = {
//                { m, 1, m, m, m, 1, m, m, m ,m },
//                { 1, m, m, m, 1, m, m, m, m ,m },
//                { m, m, m, m, m, 1, 1, m, m ,m },
//                { m, m, m, m, 1, m, m, 1, m ,m },
//                { m, 1, m, 1, m, m, m, m, 1 ,m },
//                { 1, m, 1, m, m, m, 1, m, 1 ,1 },
//                { m, m, 1, m, m, 1, m, m, m ,1 },
//                { m, m, m, 1, m, m, m, m, 1 ,m },
//                { m, m, m, m, 1, m, m, 1, m ,1 },
//                { m, m, m, m, m, 1, 1, m, 1 ,m },
//        };

//        int[][] matrizColoreo = {
//                { m, 1, 1, m, m, m, m, m, m },
//                { 1, m, m, 1, m, m, m, m, m },
//                { 1, m, m, 1, m, m, m, m, m },
//                { m, 1, 1, m, 1, 1, m, m, m },
//                { m, m, m, 1, m, 1, 1, m, m },
//                { m, m, m, 1, m, m, m, 1, m },
//                { m, m, m, m, 1, m, m, 1, 1 },
//                { m, m, m, m, m, 1, 1, m, 1 },
//                { m, m, m, m, m, m, 1, 1, m }
//        };
//        
//        MatrizDeAdyacencia matriz = new MatrizDeAdyacencia(matrizColoreo);
//
//        Coloreo coloreo = new Coloreo(matriz);
//        coloreo.coloreoWelshPowell();

//		int[][] matrizColoreo = {
//			{ INF, UNO, INF, INF, INF, INF, INF, INF, UNO, INF, INF, INF, INF },
//			{ UNO, INF, UNO, INF, INF, UNO, INF, INF, INF, INF, INF, INF, INF },
//			{ INF, INF, INF, UNO, INF, INF, INF, INF, INF, INF, INF, INF, INF },
//			{ INF, INF, UNO, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
//			{ INF, INF, INF, INF, INF, UNO, INF, INF, UNO, INF, INF, INF, INF },
//			{ INF, UNO, INF, INF, UNO, INF, UNO, INF, INF, UNO, INF, INF, INF },
//			{ INF, INF, INF, INF, INF, UNO, INF, UNO, INF, INF, INF, INF, INF },
//			{ INF, INF, INF, INF, INF, INF, UNO, INF, INF, INF, INF, INF, INF },
//			{ UNO, INF, INF, INF, UNO, INF, INF, INF, INF, UNO, INF, INF, INF },
//			{ INF, INF, INF, INF, INF, UNO, INF, INF, UNO, INF, UNO, INF, INF },
//			{ INF, INF, INF, INF, INF, INF, INF, INF, INF, UNO, INF, INF, INF },
//			{ INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, UNO },
//			{ INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF, INF }
//		};
//		
//		MatrizDeAdyacencia matrizBFS = new MatrizDeAdyacencia(matrizColoreo);
//
//		RecorridoBFS recorrido = new RecorridoBFS(matrizBFS);
//
//		recorrido.recorrerGrafo();

//		/* Cambio de manos de calles */
//		final int NODO_SALIDA = 2;
//		final int NODO_LLEGADA = 7;
//
//		int[][] matrizDirigida = { { INF, INF, INF, INF, 3, 7, INF, INF }, { 5, INF, 2, 4, INF, INF, INF, INF },
//				{ 3, INF, INF, INF, INF, INF, INF, INF }, { 4, INF, INF, INF, INF, INF, INF, INF },
//				{ INF, INF, 2, INF, INF, INF, INF, INF }, { INF, INF, INF, INF, INF, INF, 8, 1 },
//				{ INF, INF, INF, INF, 3, INF, INF, INF }, { INF, INF, INF, 2, INF, INF, 6, INF } };
//		// Misma matriz pero no dirigida
//		int[][] matrizNoDirigida = {
//				{ INF, 5, 3, 4, 3, 7, INF, INF }, { 5, INF, 2, 4, INF, INF, INF, INF },
//				{ 3, 2, INF, INF, 2, INF, INF, INF }, { 4, 4, INF, INF, INF, INF, INF, 2 },
//				{ 3, INF, 2, INF, INF, INF, 3, INF }, { 7, INF, INF, INF, INF, INF, 8, 1 },
//				{ INF, INF, INF, INF, 3, 8, INF, 6 }, { INF, INF, INF, 2, INF, 1, 6, INF } };
//		MatrizDeAdyacencia dirigida = new MatrizDeAdyacencia(matrizDirigida);
//		MatrizDeAdyacencia noDirigida = new MatrizDeAdyacencia(matrizNoDirigida);
//		Dijkstra algoritmo = new Dijkstra(noDirigida, NODO_SALIDA);
//		
//		System.out.println("COSTO: " + algoritmo.obtenerCosto(NODO_LLEGADA - 1));
//		
//		System.out.println("--CAMINO--");
//		int nodoSalida = NODO_SALIDA - 1; // ajusto offset
//		
//		LinkedList<String> callesInvertidas = new LinkedList<String>();
//		LinkedList<Integer> caminoMasCorto = algoritmo.obtenerCamino(NODO_LLEGADA);
//		for (Integer nodoLlegada : caminoMasCorto) {
//			if (nodoLlegada != nodoSalida) { // no me interesa el primer caso (nodoSalida -> nodoSalida)
//				int salida = nodoSalida + 1; // ajusto offset
//				int llegada = nodoLlegada + 1; // ajusto offset
//				
//				/* IMPRESION */
//				System.out.println(salida + " -> " + llegada + " = " + matrizNoDirigida[nodoSalida][nodoLlegada]);
//				
//				/*CHEQUEO INVERSION*/
//				if(matrizDirigida[nodoSalida][nodoLlegada] == INF) {
//					callesInvertidas.add(salida + " -> " + llegada);
//				}
//				
//				nodoSalida = nodoLlegada;
//			}
//		}
//		System.out.println("---CALLES INVERTIDAS---");
//		for(String calle : callesInvertidas) {
//			System.out.println(calle);
//		}
//		

//		
//		System.out.println("\n");
//		System.out.println("-------DIRIGIDA-------");
//		dirigida.mostrarMatriz();
//		System.out.println("------NODIRIGIDA------");
//		noDirigida.mostrarMatriz();
	}
}
