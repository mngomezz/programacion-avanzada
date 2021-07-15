package algoritmos;

/* PARA QUE: Indica si es posible llegar de un nodo cualquiera hacia otro
 * 
 * PARAMETROS: Recibe una matriz de adyacencia
 * RETORNO: Una matriz de adyacencia booleana
 *  
 * NOTA: Se comporta de manera similar a Floyd pero sin preocuparse por los
 *  pesos. Al igual que este, no soporta mas de 500 nodos por su complejidad
 * */
public class Warshall {
	
	private MatrizDeAdyacencia matrizInicial;
	private MatrizDeAdyacencia matrizResultado;
	
	public Warshall(MatrizDeAdyacencia matrizInicial) {
		this.matrizInicial = matrizInicial;
	}

	public MatrizDeAdyacencia warshall() {
			
		this.matrizResultado = matrizInicial.clone();
		
		matrizResultado.matrizConexiones();
		
		for (int k = 0; k < matrizResultado.getSize(); k++) {
			for (int i = 0; i < matrizResultado.getSize(); i++) {
				for (int j = 0; j < matrizResultado.getSize(); j++) {
					if (k != i && k != j && i != j) {
						if (matrizResultado.getCosto(i, j) == 1 || (matrizResultado.getCosto(i, k) == 1 && matrizResultado.getCosto(k, j) == 1))
								this.matrizResultado.conectarNodos(i, j, 1, true);
					}
				}
			}
		}
		return this.matrizResultado;		
		
	}
	

}
