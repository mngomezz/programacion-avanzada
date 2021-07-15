package oia1;

import java.util.ArrayList;

public class Reina {
	static int cantReinas = 0;

	int x;
	int y;
	private final int num;

	private ArrayList<Integer> reinasEnConflicto;

	public ArrayList<Integer> getReinasEnConflicto() {
		return reinasEnConflicto;
	}

	public void setReinasEnConflicto(ArrayList<Integer> reinas) {
		this.reinasEnConflicto = reinas;
	}

	public Reina(int i, int j) {
		// La entrada del archivo.in va del 1 al 8 y la matriz va del 0 al 7, por eso
		// resto 1 a la entrada.
		x = i - 1;
		y = j - 1;
		num = ++cantReinas;
	}

	public Integer getNumber() {
		return num;
	}

}
