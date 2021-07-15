package monjesvikingos;

public abstract class Estado {
	int daño = 10;
	int vida = 100;

	public Estado recibirDaño(int d) {
		this.vida -= d;
		return this;
	}

	public Estado meditar() {
		return this;
	}

	public void atacar(MonjeVikingo o) {
		o.recibirDaño(daño);
	}
}
