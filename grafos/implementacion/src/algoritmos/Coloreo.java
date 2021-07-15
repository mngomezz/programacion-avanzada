package algoritmos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Coloreo {
	
	private MatrizDeAdyacencia matriz;
	private int cantNodos;
	
	public Coloreo(MatrizDeAdyacencia matriz) {
		this.matriz = matriz;
		this.cantNodos = matriz.getSize();
	}
	
	public void coloreoWelshPowell() {
		
		ArrayList<Nodo> noColoreados = new ArrayList<Nodo>();
		ArrayList<Color> colores = new ArrayList<Color>();
		
		hallarGrados(noColoreados);
		
		Collections.sort(noColoreados, Collections.reverseOrder());
		
		while(!noColoreados.isEmpty()) {
			
			Nodo nodoactual = noColoreados.remove(0);
			int[] adyacentes = matriz.obtenerAdyacentes(nodoactual.getNodo());
			boolean encontroColor = false;
			
			for (Color color : colores) {
				
				boolean colorUsado = false;
				
				for(int i=0; i<cantNodos; i++) {
					if (adyacentes[i] != Integer.MAX_VALUE && color.poseeNodo(i)) {
						colorUsado = true;
						break;
					}	
				}
				
				if(!colorUsado) {
					color.agregarNodo(nodoactual);
					encontroColor = true;
					break;
				}
			}
			
			if (!encontroColor) 
				colores.add(new Color(nodoactual));
		}
		
//		System.out.println(colores.size());
//		
//		for (Color color : colores) {
//			color.mostrarNodos();
//			System.out.println(" --------- ");
//		}
	}
	
	public void hallarGrados(ArrayList<Nodo> nodos) {
		
		int cant = 0;
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				if (matriz.getCosto(i, j) != Integer.MAX_VALUE) 
					cant++;
			}			
			nodos.add(new Nodo(i, cant));
			cant = 0;
		}
		
	}	
}
