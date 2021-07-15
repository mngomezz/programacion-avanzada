package dominio;

/* Estas orientaciones tienen como referencia el terreno1 del domino, por lo tanto
 * HORIZONTAL_IZQUIERDA seria un domino del tipo [2][1] y VERTICAL_ARRIBA seria [2]
 * 																			    [1]
 */
public enum Orientacion {
	VERTICAL_ARRIBA, HORIZONTAL_IZQUIERDA, VERTICAL_ABAJO, HORIZONTAL_DERECHA;

	public static Orientacion getRandom() {
		int randomNumber = (int) (Math.random() * 4);

		if (randomNumber == 0)
			return VERTICAL_ARRIBA;
		else if (randomNumber == 1)
			return HORIZONTAL_IZQUIERDA;
		else if (randomNumber == 2)
			return VERTICAL_ABAJO;
		else
			return HORIZONTAL_DERECHA;
	}

	public Orientacion rotar() {
		switch (this) {
		case VERTICAL_ARRIBA:
			return HORIZONTAL_DERECHA;
		case HORIZONTAL_DERECHA:
			return VERTICAL_ABAJO;
		case HORIZONTAL_IZQUIERDA:
			return VERTICAL_ARRIBA;
		case VERTICAL_ABAJO:
			return HORIZONTAL_IZQUIERDA;
		default:
			return this;
		}
	}
}
