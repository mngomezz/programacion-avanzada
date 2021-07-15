package unlam.progava.oia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectorDeArchivos {

	public static Calculadora leer(String path) {
		Calculadora calculadora = null;

		File file = new File(path);
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			int cantApps = scanner.nextInt();
			calculadora = new Calculadora(cantApps);
			calculadora.setMBNecesarios(scanner.nextInt());
			for (int i = 0; i < cantApps; i++) {
				calculadora.agregar(scanner.nextInt());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return calculadora;
	}

}
