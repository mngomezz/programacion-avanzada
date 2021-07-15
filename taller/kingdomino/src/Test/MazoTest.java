package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dominio.Domino;
import dominio.Mazo;
import excepciones.NoHayMasDominos;

public class MazoTest {

	@Test
	public void testCantidadDeFichasIniciales() {
		Mazo mazo = new Mazo();
		assertEquals(4, mazo.getFichasIniciales().size());
	}

	@Test
	public void testCantidadDeDominos() {
		Mazo mazo = new Mazo();
		assertEquals(48, mazo.getDominos().size());
	}

	@Test
	public void testObtieneTodosDominos() throws NoHayMasDominos {
		Mazo mazo = new Mazo();
		ArrayList<Integer> numDominos = new ArrayList<Integer>();
		boolean estado = true;

		for (int i = 0; i < 12; i++) {

			for (Domino domino : mazo.obtenerDominos())
				numDominos.add(domino.getNumero());

		}

		numDominos.sort(null);

		for (int i = 0; i < 48; i++) {
			if (i + 1 != numDominos.get(i))
				estado = false;
		}

		assertEquals(true, estado);
	}

	@Test
	public void testLanzaExcepcionObtenerDomino() {
		Mazo mazo = new Mazo();
		boolean estado = true;

		try {
			for (int i = 0; i < 13; i++) {
				mazo.obtenerDominos();
			}
		} catch (Exception e) {
			if (e.getMessage().equals("No hay siguiente domino"))
				estado = false;
		}

		assertEquals(false, estado);
	}

}