package dominio;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import soporte.Imagenes;

public class FichaInicial {

	// El numero de id es para manejo interno y puede crearse en base a una variable
	// estatica
	// incremental que cuenta la cantidad de fichas internas
	private int numeroFichaInicial = 0;
	private String color;

	public int getNumeroFichaInicial() {
		return this.numeroFichaInicial;
	}

	public String getColor() {
		return color;
	}

	public FichaInicial(int num, String color) {
		this.numeroFichaInicial = num;
		this.color = color;
	}

	@Override
	public String toString() {
		return "FichaInicial [" + numeroFichaInicial + " , " + color + "]";
	}

	public Color getColorFromFicha() {
		switch (color) {
		case "ROJO":
			return Color.RED;
		case "AZUL":
			return Color.BLUE;
		case "AMARILLO":
			return Color.YELLOW;
		case "VERDE":
			return Color.GREEN;
		}
		return null;
	}

	public Image getImagen() {
		try {
			return Imagenes.getImagen(color);
		} catch (IOException e) {
			System.out.println("UPS!");
		}
		return null;
	}

}
