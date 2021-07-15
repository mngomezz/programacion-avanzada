package algoritmos;

public class MatrizDeAdyacencia implements Cloneable {

	private int matriz[][];
	private int tamanio;
	private int costoMinimo;

	public MatrizDeAdyacencia(int[][] matriz) {
		this.matriz = matriz;
		this.tamanio = matriz.length;
	}

	public MatrizDeAdyacencia(int tamanio) {
		this.matriz = new int[tamanio][tamanio];

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++)
				matriz[i][j] = Integer.MAX_VALUE;
		}

		this.tamanio = tamanio;
	}

	public int[] obtenerAdyacentes(int nodo) {
		return matriz[nodo];
	}

	public int getSize() {
		return tamanio;
	}

	public int getCostoMinimo() {
		return costoMinimo;
	}

	public void setCostoMinimo(int costoMinimo) {
		this.costoMinimo = costoMinimo;
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public void conectarNodos(int nodo1, int nodo2, int costo, boolean esDirigido) {
		matriz[nodo1][nodo2] = costo;

		if (!esDirigido) {
			matriz[nodo2][nodo1] = costo;
		}
	}

	public void mostrarMatriz() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] == Integer.MAX_VALUE)
					System.out.print("-  ");
				else
					System.out.print(matriz[i][j] + "  ");
			}
			System.out.println();
		}
	}

	@Override
	public MatrizDeAdyacencia clone() {
		return new MatrizDeAdyacencia(this.matriz.clone());
	}

	public int getCosto(int nodo1, int nodo2) {
		return matriz[nodo1][nodo2];
	}

	public void matrizConexiones() {

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = (matriz[i][j] == Integer.MAX_VALUE) ? 0 : 1;
			}
		}
	}

}
