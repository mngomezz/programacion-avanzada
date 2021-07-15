package dominio;

import java.util.ArrayList;

public class Zona {
	private Tipo tipo;
	private int coronas;
	ArrayList<Posicion> posiciones;

	public Zona(Terreno terreno, Posicion posicion) {
		tipo = terreno.getTipo();
		posiciones = new ArrayList<Posicion>();
		posiciones.add(posicion);
		coronas = terreno.getCoronas();
	}

	public boolean insertarTerreno(Terreno terreno, Posicion posicion) {
		if (terreno.getTipo() != tipo)
			return false;

		if (!existeAdyacencia(posicion))
			return false;

		posiciones.add(posicion);
		coronas += terreno.getCoronas();

		return true;
	}

	private boolean existeAdyacencia(Posicion posicion) {
		for (Posicion pos : posiciones) {
			if (pos.esAdyacente(posicion))
				return true;
		}
		return false;
	}

	public int obtenerPuntaje() {
		return posiciones.size() * coronas;
	}

	@Override
	public String toString() {
		int size = posiciones.size();
		return "Zona de " + size + " terreno" + (size == 1 ? "" : "s") + " de "
				+ tipo + " con " + coronas + " coronas";
	}

}
