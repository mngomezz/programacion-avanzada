package lanzandoElCaber;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class GestorArchivos {
	final int TIROS_POR_CONCURSANTE = 3;
	String filename;

	public GestorArchivos(String ruta) {
		filename = ruta;
	}

	public TreeSet<Concursante> leerEntrada() throws FileNotFoundException {

		Scanner scanner = new Scanner(new File(filename + ".in"));

		int cantConcursantes = scanner.nextInt(); // Leo cant. de concursantes
		int tirosRestantes = TIROS_POR_CONCURSANTE;
		TreeSet<Concursante> concursantes = new TreeSet<Concursante>(new DistanciaComparator());
		Concursante concursante = new Concursante();
		while (cantConcursantes-- > 0) {
			tirosRestantes = TIROS_POR_CONCURSANTE;
			while (tirosRestantes-- > 0) {
				LanzamientoCaber caber = new LanzamientoCaber(scanner.nextFloat(), scanner.nextFloat());
				concursante.agregarCaberLanzado(caber);
			}
			concursantes.add(concursante);
			concursante = new Concursante();
		}
		return concursantes;
	}

	public void generarSalida(ArrayList<Integer> ganadoresCons, ArrayList<Integer> ganadoresDist) throws IOException {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(filename + ".out"));

			for (Integer participante : ganadoresCons) {
				pw.print(participante + " ");
			}
			pw.println();
			for (Integer participante : ganadoresDist) {
				pw.print(participante + " ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != pw)
				pw.close();
		}
	}

}
