package lanzandoElCaber;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Torneo {
	private TreeSet<Concursante> concursantes;
	final int CANT_GANADORES = 3;

	public Torneo(GestorArchivos archivo) throws FileNotFoundException {
		this.concursantes = archivo.leerEntrada();
	}

	public ArrayList<Integer> calcularGanadoresDistancia() {
		ArrayList<Integer> ganadores = new ArrayList<Integer>();
		Iterator<Concursante> concursantesOrdenadosPorDistancia = this.concursantes.iterator();
		while (concursantesOrdenadosPorDistancia.hasNext() && ganadores.size() < CANT_GANADORES) {
			Concursante concursante = concursantesOrdenadosPorDistancia.next();
			ganadores.add(concursante.obtenerNumero());
		}
		return ganadores;
	}

	public ArrayList<Integer> calcularGanadoresConsistencia() {
		TreeSet<Concursante> concursantes = new TreeSet<Concursante>(new ConsistenciaComparator());
		concursantes.addAll(this.concursantes);
		ArrayList<Integer> ganadores = new ArrayList<Integer>();
		Iterator<Concursante> concursantesConsistentes = concursantes.iterator();
		while (concursantesConsistentes.hasNext() && ganadores.size() < CANT_GANADORES) {
			Concursante concursante = concursantesConsistentes.next();
			if (!concursante.tieneTirosDescalificados()) {
				ganadores.add(concursante.obtenerNumero());
			}
		}
		return ganadores;
	}
}
