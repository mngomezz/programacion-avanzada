package oia1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Tablero {
	Reina[][] posiciones;
	int dimension;
	TreeSet<Reina> reinas;

	Tablero(int d) {
		dimension = d;
		posiciones = new Reina[d][d];
		reinas = new TreeSet<Reina>(new ReinaComparator<Reina>());
	}

	public int obtenerReinasEnConflicto(Reina r) {
		ArrayList<Integer> reinas = new ArrayList<Integer>();
		Integer up, down, left, right, upLeft, upRight, downLeft, downRight;
		Integer cantidadReinas = 0;

		// Busco conflicto en cada direccion posible
		if ((up = buscarConflicto(r, 0, 1)) != null) {
			reinas.add(up);
			cantidadReinas++;
		}
		if ((down = buscarConflicto(r, 0, -1)) != null) {
			reinas.add(down);
			cantidadReinas++;
		}
		if ((left = buscarConflicto(r, -1, 0)) != null) {
			reinas.add(left);
			cantidadReinas++;
		}
		if ((right = buscarConflicto(r, 1, 0)) != null) {
			reinas.add(right);
			cantidadReinas++;
		}
		if ((upLeft = buscarConflicto(r, -1, 1)) != null) {
			reinas.add(upLeft);
			cantidadReinas++;
		}
		if ((upRight = buscarConflicto(r, 1, 1)) != null) {
			reinas.add(upRight);
			cantidadReinas++;
		}
		if ((downLeft = buscarConflicto(r, -1, -1)) != null) {
			reinas.add(downLeft);
			cantidadReinas++;
		}
		if ((downRight = buscarConflicto(r, 1, -1)) != null) {
			reinas.add(downRight);
			cantidadReinas++;
		}
		Collections.sort(reinas);

		r.setReinasEnConflicto(reinas);
		return cantidadReinas;
	}

	public boolean agregarReina(Reina r) {
		if (getReina(r.x, r.y) != null)
			return false;

		setReina(r);
		return true;
	}

	private void setReina(Reina r) {
		reinas.add(r);
		posiciones[r.x][r.y] = r;
	}

	private Integer buscarConflicto(Reina r, int offsetX, int offsetY) {
		int xBis = r.x + offsetX, yBis = r.y + offsetY;

		// Mientras siga dentro de las dimensiones del tablero
		while (this.dimension > Math.max(xBis, yBis) && 0 <= Math.min(xBis, yBis)) {
			if (getReina(xBis, yBis) != null) {
				return getReina(xBis, yBis).getNumber();
			}
			xBis += offsetX;
			yBis += offsetY;
		}
		return null;
	}

	public Reina getReina(int i, int j) {
		return posiciones[i][j];
	}

}
