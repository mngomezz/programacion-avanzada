package oia1;

import java.io.IOException;

public class ConflictoEntreReinasApp {

	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();
		Tablero t = GestorEntradaSalida.leerEntrada("reinas.in");
		GestorEntradaSalida.generarSalida(t, "reinas.out");
		long end = System.nanoTime();
		System.out.println("nanosecond: " + (end - start));
		System.out.println("millisecond: " + ((end - start) / Math.pow(10, 6)));
		System.out.println("second: " + ((end - start) / Math.pow(10, 9)));
	}

}
