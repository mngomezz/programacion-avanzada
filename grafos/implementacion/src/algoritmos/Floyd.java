package algoritmos;

/* PARA QUE: Halla el costo minimo de ir desde cualquier nodo a cualquier otro
 * 
 * PARAMETROS: Recibe una matriz de adyacencia
 * RETORNO: Otra matriz de adyacencia con los costos minimos ya calculados
 *  
 * NOTA: Este algoritmo es muy complejo y no soporta mas de 500 nodos.
 * */
public class Floyd {

	private static final int INF = Integer.MAX_VALUE;
	private MatrizDeAdyacencia matrizInicial;
	private MatrizDeAdyacencia matrizResultado;

	public Floyd(MatrizDeAdyacencia matrizInicial) {
		this.matrizInicial = matrizInicial;
	}

	public MatrizDeAdyacencia floyd() {
		this.matrizResultado = matrizInicial.clone();

		for (int i = 0; i < matrizResultado.getSize(); i++) {
			matrizResultado.conectarNodos(i, i, 0, true);
		}

		for (int k = 0; k < matrizResultado.getSize(); k++) {
			for (int i = 0; i < matrizResultado.getSize(); i++) {
				for (int j = 0; j < matrizResultado.getSize(); j++) {
					int costoIK = matrizResultado.getCosto(i, k);
					int costoKJ = matrizResultado.getCosto(k, j);
					int costoIJ = matrizResultado.getCosto(i, j);
					
					if (k != i && k != j && costoIK != INF && costoKJ != INF) {
						int min = Math.min(costoIJ, costoIK + costoKJ);
						this.matrizResultado.conectarNodos(i, j, min, true);
					}
				}
			}
		}

		return this.matrizResultado;
	}

}
