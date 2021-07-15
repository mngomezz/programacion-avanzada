package monjesvikingos;

public class MonjeVikingo {
	int daño = 10;
	int vida = 100;

	public Estado estado = new Normal();

	public Estado getEstado() {
		return estado;
	}

	public void recibirDaño(int d) {
		estado = estado.recibirDaño(d);
	}

	public void meditar() {
		estado = estado.meditar();
	}

	public void atacar(MonjeVikingo o) {
		estado.atacar(o);
	}
}
