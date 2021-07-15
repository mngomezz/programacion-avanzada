package lanzandoElCaber;

public class LanzamientoCaber {
	private float distancia;
	private float angulo;

	public LanzamientoCaber(float distancia, float angulo) {
		this.distancia = distancia;
		this.angulo = angulo;
	}

	public boolean esTiroValido() {
		return !(this.angulo > 90 || this.angulo < -90);
	}

	public float obtenerDistanciaReal() {
		if (this.esTiroValido() == false) {
			return 0;
		}

		return this.angulo <= 30 && this.angulo >= -30 ? this.distancia : this.distancia * 0.8f;
	}

	public boolean esConsistente() {
		return this.esTiroValido() && this.angulo <= 30 && this.angulo >= -30;
	}

	public float obtenerAngulo() {
		return this.angulo;
	}

	public float obtenerDistancia() {
		return this.distancia;
	}
}
