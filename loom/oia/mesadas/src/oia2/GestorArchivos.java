package oia2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class GestorArchivos {
	public static TreeSet<Mesada> leerEntrada(String filename) {
		try {
			Scanner s = new Scanner(new File(filename));

			int cantidadMesadas = s.nextInt();

			TreeSet<Mesada> mesadas = new TreeSet<Mesada>();

			while (cantidadMesadas-- != 0) {
				mesadas.add(new Mesada(s.nextInt(), s.nextInt()));
			}

			s.close();
			return mesadas;
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}

	public static void generarSalida(int nroMesadas, String filename) throws IOException {
		PrintWriter pw = null;
		try {

			if (filename == null) {
				pw = new PrintWriter(System.out);
			} else {
				pw = new PrintWriter(new FileWriter(filename));
			}

			pw.print(nroMesadas);
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
