package algoritmos;
import java.util.ArrayList;

public class Color implements Cloneable{
	
	private ArrayList<Nodo> nodos;
	private int color;
	private static int cantColores=0;
	
	public Color (Nodo nodo) {
		this.color = cantColores++;
		this.nodos = new ArrayList<Nodo>();
		this.nodos.add(nodo);
	}
	
	public void agregarNodo(Nodo nodo) {
		this.nodos.add(nodo);
	}
	
	public boolean poseeNodo (int nodo2) {
		
		for (Nodo nodo : nodos) {
			if(nodo.getNodo() == nodo2)
				return true;
		}
		
		return false;
	}
	
	public void mostrarNodos() {
		for (Nodo nodo : nodos) {
			System.out.println(nodo);
		}
	}
}
