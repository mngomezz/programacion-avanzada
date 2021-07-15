package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Domino;
import dominio.Orientacion;
import dominio.Posicion;
import dominio.Terreno;
import dominio.Tipo;

public class DominoTest {
	private final Orientacion HI = Orientacion.HORIZONTAL_IZQUIERDA;
	private final Orientacion HD = Orientacion.HORIZONTAL_DERECHA;
	private final Orientacion VAr = Orientacion.VERTICAL_ARRIBA;
	private final Orientacion VAb = Orientacion.VERTICAL_ABAJO;
	private final Terreno B0 = new Terreno(Tipo.BOSQUE, 0);
	private final Terreno B1 = new Terreno(Tipo.BOSQUE, 1);
	private final Terreno L0 = new Terreno(Tipo.LLANURA, 0);
	private final Terreno L1 = new Terreno(Tipo.LLANURA, 1);
	private final Terreno A0 = new Terreno(Tipo.AGUA, 0);
	private final Terreno A1 = new Terreno(Tipo.AGUA, 1);
//	private final Terreno P0 = new Terreno(Tipo.PANTANO, 0);
//	private final Terreno P1 = new Terreno(Tipo.PANTANO, 1);
//	private final Terreno T0 = new Terreno(Tipo.TRIGO, 0);
//	private final Terreno T1 = new Terreno(Tipo.TRIGO, 1);
//	private final Terreno M0 = new Terreno(Tipo.MINA, 0);
//	private final Terreno M1 = new Terreno(Tipo.MINA, 1);

	@Test
	public void test() {
		Domino domino1 = new Domino(B0, B1, HI);
		Domino domino2 = new Domino(L0, L1, HD);
		Domino domino3 = new Domino(A0, A1, VAr);

		// orientacion al rotar el domino
		assertEquals(domino1.getOrientacion(), HI);
		domino1.rotar();
		assertEquals(domino1.getOrientacion(), VAr);
		domino1.rotar();
		assertEquals(domino1.getOrientacion(), HD);
		domino1.rotar();
		assertEquals(domino1.getOrientacion(), VAb);

		// volteos de domino
		assertFalse(domino2.estaVolteado());
		domino2.voltearDomino();
		assertTrue(domino2.estaVolteado());

		// getters de Terrenos
		assertEquals(domino3.getTerreno1(), A0);
		assertEquals(domino3.getTerreno2(), A1);

		// Obtener posicion de terreno 2 segun orientacion
		Posicion posT2 = new Posicion(1, 2);
		assertEquals(posT2, domino2.getPosicionTerreno2(new Posicion(1, 1)));

	}

}
