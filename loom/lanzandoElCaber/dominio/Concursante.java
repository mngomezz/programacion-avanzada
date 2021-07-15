package lanzandoElCaber;

import java.util.ArrayList;

public class Concursante {

	private ArrayList<LanzamientoCaber> cabersLanzados = new ArrayList<>();
	private int numero;
	private boolean tirosDescalificados = false;
	private static int cantidadDeConcursantes = 0;

	public Concursante() {
		this.numero = ++cantidadDeConcursantes;
	}

	public ArrayList<LanzamientoCaber> obtenerCabers() {
		return this.cabersLanzados;
	}

	public void agregarCaberLanzado(LanzamientoCaber lanzamiento) {
		this.cabersLanzados.add(lanzamiento);
	}

	public int obtenerNumero() {
		return this.numero;
	}

	public float obtenerDistanciaTotal() {
		float sumaDistanciaTotal = 0;

		for (LanzamientoCaber iterador : this.cabersLanzados) {
			sumaDistanciaTotal += iterador.obtenerDistanciaReal();
		}

		return sumaDistanciaTotal;
	}

	public boolean tieneTirosDescalificados() {
		return this.tirosDescalificados;
	}

	public int obtenerConsistencia() {
		for (LanzamientoCaber lanzamiento : this.cabersLanzados) {
			if (!lanzamiento.esTiroValido()) {
				this.tirosDescalificados = true;

				return -1;
			}
			if (!lanzamiento.esConsistente()) {
				return 0;
			}
		}

		return 1;
	}

	public int obtenerCantidadCabersLanzados() {
		return cabersLanzados.size();
	}
}
