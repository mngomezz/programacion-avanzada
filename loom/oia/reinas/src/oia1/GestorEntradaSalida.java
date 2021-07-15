package oia1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GestorEntradaSalida {
	public static Tablero leerEntrada(String filename) {
		try {
			Scanner s = new Scanner(new File(filename));

			int dimensionTablero = s.nextInt();
			int cantidadReinas = s.nextInt();

			Tablero tab = new Tablero(dimensionTablero);

			while (cantidadReinas-- != 0) {
				tab.agregarReina(new Reina(s.nextInt(), s.nextInt()));
			}

			s.close();
			return tab;
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}

	public static void generarSalida(Tablero t, String filename) throws IOException {
		PrintWriter pw = null;
		try {
			if (filename == null)
				pw = new PrintWriter(System.out);
			else
				pw = new PrintWriter(new FileWriter(filename));

			for (Reina reina : t.reinas) {
				pw.print(t.obtenerReinasEnConflicto(reina)); // imprimo cantidad de reinas en conflicto
				for (Integer number : reina.getReinasEnConflicto()) {
					pw.print(" " + number);
				}
				pw.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
		return;
	}
}
