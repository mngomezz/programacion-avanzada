package monjesvikingos;

public class Colerico extends Estado {
	int daño = super.daño * 2;

	@Override
	public Estado recibirDaño(int d) {
		this.vida -= d * 2;
		return new Berseker();
	}

	@Override
	public Estado meditar() {
		return new Normal();
	}

}
