package oia2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class MesadasDeGranitoApp {

	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();
		int cantPilas = getCantPilas(GestorArchivos.leerEntrada("mesadas.in"));
		GestorArchivos.generarSalida(cantPilas, "mesadas.out");
		long end = System.nanoTime();
		System.out.println("nanosecond: " + (end - start));
		System.out.println("millisecond: " + ((end - start) / Math.pow(10, 6)));
		System.out.println("second: " + ((end - start) / Math.pow(10, 9)));
	}

	public static int getCantPilas(TreeSet<Mesada> mesadas) {
		ArrayList<Stack<Mesada>> pilas = new ArrayList<Stack<Mesada>>();

		for (Mesada mesada : mesadas) {
			boolean seAgrego = false;
			for (Stack<Mesada> pila : pilas) {
				if (agregarEnPila(pila, mesada) == true)
					seAgrego = true;
			}

			if (seAgrego == false) {
				// Si no se pudo agregar, creo pila nueva
				Stack<Mesada> nuevaPila = new Stack<Mesada>();
				nuevaPila.push(mesada);
				pilas.add(nuevaPila);
			}
		}

		return pilas.size();
	}

	/* Chequeo si es posible ingresar en la pila y la ubico en su lugar */
	public static boolean agregarEnPila(Stack<Mesada> pila, Mesada mesada) {
		// Si la pila esta vacia, debo insertar si o si
		if (pila.empty() == true) {
			pila.push(mesada);
			return true;
		}

		// Verifico que el elemento se deba ingresar
		if (mesada.esCompatible(pila.peek()) == false)
			return false;

		return ubicarEnPila(pila, mesada);
	}

	/* Ingreso elemento en la posicion correcta de la pila */
	private static boolean ubicarEnPila(Stack<Mesada> pila, Mesada mesada) {
		Stack<Mesada> pilaAux = new Stack<Mesada>();

		// Quito elementos de la pila hasta hallar el lugar a ingresar
		while (pila.empty() == false && mesada.esMasChica(pila.peek()))
			pilaAux.push(pila.pop());

		pila.push(mesada); // Ingreso el elemento en cuestion

		// Mientras haya algo en la pila auxiliar, lo llevo a la otra
		while (pilaAux.empty() == false)
			pila.push(pilaAux.pop());

		return true;
	}

}