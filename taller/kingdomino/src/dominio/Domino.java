package dominio;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import soporte.Imagenes;

public class Domino {
	private static int cantidadDominos = 0;
	private Terreno[] terrenos = new Terreno[2];
	private Orientacion orientacion;
	private boolean estoyVolteado = false;
	int numero;

	public Domino(Terreno t1, Terreno t2, Orientacion o) {
		terrenos[0] = t1;
		terrenos[1] = t2;
		orientacion = o;
		numero = cantidadDominos++;
	}

	public Orientacion getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Orientacion orientacion) {
		this.orientacion = orientacion;
	}

	public void rotar() {
		orientacion = orientacion.rotar();
	}

	public int getNumero() {
		return numero;
	}

	public boolean estaVolteado() {
		return estoyVolteado;
	}

	public void voltearDomino() {
		estoyVolteado = !estoyVolteado;
	}

	@Override
	public String toString() {
		return "Domino [numeroDomino=" + numero + ", terrenos=" + Arrays.toString(terrenos) + ", orientacion="
				+ orientacion + "]";
	}

	public Terreno getTerreno1() {
		return this.terrenos[0];
	}

	public Terreno getTerreno2() {
		return this.terrenos[1];
	}

	/* Utilizado para calcular si se puede insertar domino en tablero */
	public Posicion getPosicionTerreno2(Posicion t1) {
		int offsetF = 0;
		int offsetC = 0;
		switch (orientacion) {
		case VERTICAL_ARRIBA: {
			offsetF = -1;
			break;
		}
		case VERTICAL_ABAJO: {
			offsetF = 1;
			break;
		}
		case HORIZONTAL_IZQUIERDA: {
			offsetC = -1;
			break;
		}
		case HORIZONTAL_DERECHA: {
			offsetC = 1;
			break;
		}
		}
		return new Posicion(t1.fila + offsetF, t1.columna + offsetC);
	}

}
