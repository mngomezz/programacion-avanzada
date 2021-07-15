package unlam.progava.oia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LectorDeArchivos {

	public static Algoritmo leer(String path) {
		Algoritmo algoritmo = null;

		File file = null;
		Scanner scanner = null;
		try {
			file = new File(path);
			scanner = new Scanner(file);
			ArrayList<Accion> acciones = new ArrayList<Accion>();
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				String[] campos = linea.split(" ");
				acciones.add(new Accion(campos));
			}

			algoritmo = new Algoritmo(acciones);
			// ... Aqui deberia leer el archivo y enviar datos al algoritmo
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return algoritmo;
	}

}
