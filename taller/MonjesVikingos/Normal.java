package monjesvikingos;

public class Normal extends Estado {

	@Override
	public Estado recibirDaño(int d) {
		this.vida -= d;
		return new Colerico();
	}

	@Override
	public Estado meditar() {
		return new Calmado();
	}

}
