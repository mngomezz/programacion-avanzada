package lanzandoElCaber;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class GestorArchivosTest {

	@Test
	public void test() throws IOException {
		String filename = "archivo";
		// El fullPath es para tener los *.in y *.out dentro del package.
		String fullPath = "Clases" + File.separator + "lanzandoElCaber" + File.separator + filename;
		GestorArchivos gestor = new GestorArchivos(fullPath);
		ArrayList<Integer> ganadoresConsistencia = new ArrayList<Integer>();
		ganadoresConsistencia.add(2);
		ganadoresConsistencia.add(3);
		ganadoresConsistencia.add(1);

		ArrayList<Integer> ganadoresDistancia = new ArrayList<Integer>();
		ganadoresDistancia.add(1);
		ganadoresDistancia.add(2);
		ganadoresDistancia.add(3);

		gestor.generarSalida(ganadoresConsistencia, ganadoresDistancia);
	}

	@Test
	public void fullTest() throws IOException {
		String filename = "archivo";
		// El fullPath es para tener los *.in y *.out dentro del package.
		String fullPath = "Clases" + File.separator + "lanzandoElCaber" + File.separator + filename;
		GestorArchivos gestor = new GestorArchivos(fullPath);
		Torneo torneo = new Torneo(gestor);

		gestor.generarSalida(torneo.calcularGanadoresConsistencia(), torneo.calcularGanadoresDistancia());
	}
	/*
	 * TESTEADO. Problemas encontrados: 1. Genera '0' donde deberia ir null. 2.
	 * Deberia generar el podio entero cuando se calcula por distancia
	 * 
	 */

}
