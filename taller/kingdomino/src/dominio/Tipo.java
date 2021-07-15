package dominio;

import java.awt.Color;

public enum Tipo {
	CASTILLO, BOSQUE, AGUA, PANTANO, TRIGO, LLANURA, MINA;

	public Color getColor() {
		switch (this) {
		case CASTILLO:
			return Color.DARK_GRAY;// new Color(59, 22, 14);
		case BOSQUE:
			return new Color(18, 118, 34);
		case AGUA:
			return new Color(114, 159, 207);
		case PANTANO:
			return new Color(107, 94, 155);
		case TRIGO:
			return new Color(255, 255, 109);
		case LLANURA:
			return new Color(187, 227, 61);
		case MINA:
			return new Color(51, 51, 51);
		default:
			return new Color(0, 0, 0, 0.1f);
		}
	}

	/* Solo es utilizado para visualizar el terreno por consola */
	@Override
	public String toString() {
		switch (this) {
		case CASTILLO:
			return "castillo";
		case BOSQUE:
			return "bosque";
		case AGUA:
			return "agua";
		case PANTANO:
			return "pantano";
		case TRIGO:
			return "trigo";
		case LLANURA:
			return "llanura";
		case MINA:
			return "mina";
		default:
			return "undefined";
		}
	}

	String getInicial() {
		switch (this) {
		case CASTILLO:
			return "C";
		case BOSQUE:
			return "B";
		case AGUA:
			return "A";
		case PANTANO:
			return "P";
		case TRIGO:
			return "T";
		case LLANURA:
			return "L";
		case MINA:
			return "M";
		default:
			return " ";
		}
	}

}
