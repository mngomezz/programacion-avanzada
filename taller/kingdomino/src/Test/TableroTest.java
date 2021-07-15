package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Domino;
import dominio.FichaInicial;
import dominio.Orientacion;
import dominio.Posicion;
import dominio.Tablero;
import dominio.Terreno;
import dominio.Tipo;

public class TableroTest {
	private final FichaInicial yellowCastle = new FichaInicial(1,"amarillo");
	private final FichaInicial redCastle = new FichaInicial(2,"rojo");
	private final FichaInicial blueCastle = new FichaInicial(3,"azul");
	private final FichaInicial greenCastle = new FichaInicial(4,"verde");
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
	private final Terreno P0 = new Terreno(Tipo.PANTANO, 0);
	private final Terreno P1 = new Terreno(Tipo.PANTANO, 1);
	private final Terreno T0 = new Terreno(Tipo.TRIGO, 0);
	private final Terreno T1 = new Terreno(Tipo.TRIGO, 1);
	private final Terreno M0 = new Terreno(Tipo.MINA, 0);
	private final Terreno M1 = new Terreno(Tipo.MINA, 1);

	// @Test
	public void testPrint() {
		Tablero tab = new Tablero(yellowCastle);
		System.out.println(tab.toString());

		tab.setTerreno(B0, new Posicion(0, 0));
		tab.setTerreno(B0, new Posicion(0, 3));
		tab.setTerreno(B0, new Posicion(1, 3));
		tab.setTerreno(B1, new Posicion(0, 1));
		tab.setTerreno(B1, new Posicion(0, 2));
		tab.setTerreno(L1, new Posicion(0, 4));
		tab.setTerreno(L1, new Posicion(1, 4));
		tab.setTerreno(L1, new Posicion(3, 4));
		tab.setTerreno(L0, new Posicion(2, 4));
		System.out.println(tab.toString());
	}

	// @Test
	public void testIngresoTerrenos() {
		Tablero tab = new Tablero(blueCastle);

		tab.setTerreno(B0, new Posicion(0, 0));
		tab.setTerreno(B0, new Posicion(0, 3));
		tab.setTerreno(B0, new Posicion(1, 3));
		tab.setTerreno(B1, new Posicion(0, 1));
		tab.setTerreno(B1, new Posicion(0, 2));
		tab.setTerreno(L1, new Posicion(0, 4));
		tab.setTerreno(L1, new Posicion(1, 4));
		tab.setTerreno(L1, new Posicion(3, 4));
		tab.setTerreno(L0, new Posicion(2, 4));

		assertTrue(tab.rangoValido(new Posicion(2, 3)));
		assertFalse(tab.rangoValido(new Posicion(3, 7)));

		assertTrue(tab.coincidenAdyacentes(new Posicion(0, 0), B0));
		assertTrue(tab.coincidenAdyacentes(new Posicion(1, 1), B0));
		assertFalse(tab.coincidenAdyacentes(new Posicion(0, 7), B0));
		assertTrue(tab.coincidenAdyacentes(new Posicion(5, 0), B1));
		assertTrue(tab.coincidenAdyacentes(new Posicion(3, 1), B1));
		assertFalse(tab.coincidenAdyacentes(new Posicion(0, 7), B1));
		assertTrue(tab.esPosicionValida(new Posicion(1, 1)));
		assertTrue(tab.esPosicionValida(new Posicion(2, 1)));
		assertFalse(tab.esPosicionValida(new Posicion(0, 0)));
		assertFalse(tab.esPosicionValida(new Posicion(1, 1)));
		assertFalse(tab.esPosicionValida(new Posicion(5, 0)));
		assertFalse(tab.esPosicionValida(new Posicion(3, 4)));
	}

	// @Test
	public void testIngresoDominos() {
		Tablero tab = new Tablero(redCastle);
		Domino dominoBosqueHL = new Domino(B0, B1, HI);
		Domino dominoBosqueHR = new Domino(B0, B1, HD);
		Domino A0A1_Var = new Domino(B0, B1, VAr);
		Domino dominoBosqueVD = new Domino(B0, B1, VAb);
		Domino dominoLlanuraHL = new Domino(L0, L1, HI);
		Domino dominoLlanuraHR = new Domino(L0, L1, HD);
		Domino dominoLlanuraVU = new Domino(L0, L1, VAr);
		Domino dominoLlanuraVD = new Domino(L0, L1, VAb);

		// Si (4,4) es la posicion inicial, entonces empiezo ingresando por ahi.
		assertTrue(tab.insertar(new Posicion(4, 6), dominoBosqueHL));
		assertTrue(tab.insertar(new Posicion(5, 5), dominoBosqueHR));
		assertTrue(tab.insertar(new Posicion(6, 4), A0A1_Var));
		assertTrue(tab.insertar(new Posicion(5, 3), dominoBosqueVD));
		assertTrue(tab.insertar(new Posicion(4, 3), dominoBosqueHL));
		assertTrue(tab.insertar(new Posicion(6, 5), dominoBosqueHR));
		assertTrue(tab.insertar(new Posicion(5, 2), dominoBosqueVD));

		assertTrue(tab.insertar(new Posicion(3, 4), dominoLlanuraHL));
		assertTrue(tab.insertar(new Posicion(3, 5), dominoLlanuraHR));
		assertTrue(tab.insertar(new Posicion(3, 2), dominoLlanuraVU));
		assertTrue(tab.insertar(new Posicion(2, 4), dominoLlanuraHL));
		assertTrue(tab.insertar(new Posicion(2, 5), dominoLlanuraHR));

		// Testeo que no se puedan insertar
		// ...por terreno ocupado
		assertFalse(tab.insertar(new Posicion(3, 4), dominoBosqueVD));
		assertFalse(tab.insertar(new Posicion(5, 5), dominoBosqueHL));
		assertFalse(tab.insertar(new Posicion(5, 2), dominoBosqueHR));
		// ...por rango invalido
		assertFalse(tab.insertar(new Posicion(1, 4), dominoLlanuraHL));
		assertFalse(tab.insertar(new Posicion(3, 1), dominoLlanuraVD));
		assertFalse(tab.insertar(new Posicion(3, 7), dominoLlanuraVD));
		assertFalse(tab.insertar(new Posicion(7, 4), dominoBosqueHR));
		System.out.println(tab.toString());
	}

	@Test
	public void fullTest() {
		Tablero tab = new Tablero(greenCastle);
		Domino B0T1_HD = new Domino(B0, T1, HD);
		Domino B0L0_HD = new Domino(B0, L0, HD);
		Domino B1L1_HI = new Domino(B1, L1, HI);
		Domino P0T0_HI = new Domino(P0, T0, HI);
		Domino L0L1_VAr = new Domino(L0, L1, VAr);
		Domino M1A1_VAr = new Domino(M1, A1, VAr);
		Domino M0L0_VAb = new Domino(M0, L0, VAb);
		Domino P1A0_VAb = new Domino(P1, A0, VAb);

		// Si (4,4) es la posicion inicial, entonces empiezo ingresando por ahi.
		assertTrue(tab.insertar(new Posicion(4, 5), B0L0_HD));
		assertTrue(tab.insertar(new Posicion(5, 5), B1L1_HI));
		assertTrue(tab.insertar(new Posicion(3, 5), B0T1_HD));
		// assertTrue(tab.insertar(new Posicion(2, 6), P0T0_HI));
		assertTrue(tab.insertar(new Posicion(6, 6), L0L1_VAr));
		assertTrue(tab.insertar(new Posicion(6, 5), B1L1_HI));
		assertTrue(tab.insertar(new Posicion(2, 4), P1A0_VAb));

		assertTrue(tab.insertar(new Posicion(2, 3), P0T0_HI));
		assertTrue(tab.insertar(new Posicion(5, 3), M0L0_VAb));
		assertTrue(tab.insertar(new Posicion(4, 3), M1A1_VAr));
		assertTrue(tab.insertar(new Posicion(2, 5), B0T1_HD));
		assertTrue(tab.insertar(new Posicion(6, 2), L0L1_VAr));
		assertTrue(tab.insertar(new Posicion(4, 2), M1A1_VAr));

		// Testeo que no se puedan insertar
		// ...por terreno ocupado
		assertFalse(tab.insertar(new Posicion(2, 5), M0L0_VAb));
		assertFalse(tab.insertar(new Posicion(5, 5), B0L0_HD));
		assertFalse(tab.insertar(new Posicion(5, 2), B1L1_HI));
		// ...por rango invalido
		assertFalse(tab.insertar(new Posicion(1, 4), L0L1_VAr));
		assertFalse(tab.insertar(new Posicion(3, 1), P1A0_VAb));
		assertFalse(tab.insertar(new Posicion(3, 7), P1A0_VAb));
		assertFalse(tab.insertar(new Posicion(7, 4), B1L1_HI));
		System.out.println("Puntaje final: " + tab.obtenerPuntaje() + " puntos");
		System.out.println(tab.toString());
	}

}
