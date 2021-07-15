package dominio;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import soporte.Imagenes;

public class Terreno {
	private Tipo tipo;
	private int coronas;
//	private BufferedImage imagen;

	public Terreno(Tipo tipo, String color) {
		this.tipo = tipo;
		this.coronas = 0;
//		try {
//			this.imagen = Imagenes.getImagen(color);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public Terreno(Tipo tipo, int coronas) {
		this.tipo = tipo;
		this.coronas = coronas;
//		try {
//			this.imagen = Imagenes.getImagen(tipo, coronas);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public Terreno() {

	}

	@Override
	public String toString() {
		return "[" + tipo.getInicial() + coronas + "]";
	}

	public boolean compareTo(Terreno t) {
		if (t == null)
			return false;

		if (t.tipo == Tipo.CASTILLO || this.tipo == t.tipo)
			return true;

		return false;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipoDeTerreno) {
		this.tipo = tipoDeTerreno;
	}

	public int getCoronas() {
		return coronas;
	}

	public void setCoronas(int cantidadCoronas) {
		this.coronas = cantidadCoronas;
	}

	public Color getColor() {
		return tipo.getColor();
	}

	public BufferedImage getImagen() throws IOException {
		return Imagenes.getImagen(tipo, coronas);
	}

}
