package algoritmos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LectorDeGrafo {

	public static MatrizDeAdyacencia leer(String path, boolean esSimetrica) {

		File file = new File(path);
		Scanner scanner;
		MatrizDeAdyacencia matriz = null;
		int[][] mat;
		
		try {
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			
			int cantNodos = Integer.valueOf(scanner.nextLine());
			mat = new int[cantNodos][cantNodos];
			
			for(int i = 0; i < mat.length; i++) {
				for(int j = 0; j < mat.length; j++) 
					mat[i][j] = Integer.MAX_VALUE;
			}
			
			while(scanner.hasNext()) {
				
				String linea = scanner.nextLine();
				String valoresLinea[] = linea.split(" ");
				
				int nodoInicio = Integer.valueOf(valoresLinea[0]);
				int nodoFin = Integer.valueOf(valoresLinea[1]);
				int valorArista = Integer.valueOf(valoresLinea[2]);
				
				if(esSimetrica)
					mat[nodoFin][nodoInicio] = valorArista;	
				
				mat[nodoInicio][nodoFin] = valorArista;		
				
				matriz = new MatrizDeAdyacencia(mat);
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		return matriz;
	}
	
	public static Kruskal leerAristas (String path) {

		File file = new File(path);
		Scanner scanner;
		ArrayList<Arista> aristas = null;
		Kruskal kruskal = null;
		
		try {
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);
			
			int cantNodos = Integer.valueOf(scanner.nextLine());
			aristas = new ArrayList<Arista>();
			
			while(scanner.hasNext()) {
				
				String linea = scanner.nextLine();
				String valoresLinea[] = linea.split(" ");
				
				int nodoInicio = Integer.valueOf(valoresLinea[0]);
				int nodoFin = Integer.valueOf(valoresLinea[1]);
				int valorArista = Integer.valueOf(valoresLinea[2]);
				
				aristas.add(new Arista(nodoInicio, nodoFin, valorArista));
			}
			
			kruskal = new Kruskal(aristas, cantNodos);
			
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		return kruskal;
	}

}
