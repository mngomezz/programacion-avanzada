package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import dominio.Domino;
import dominio.FichaInicial;
import dominio.Orientacion;
import dominio.Posicion;
import dominio.Terreno;
import dominio.Tipo;
import dominio.Tablero;

public class TerrenoTest {

	@Test
	public void test() {
		Terreno t1 = new Terreno(Tipo.BOSQUE, 0);
		Terreno t2 = new Terreno(Tipo.BOSQUE, 1);
		Terreno t3 = new Terreno(Tipo.LLANURA, 0);
		// Terreno t4 = new Terreno(Tipo.LLANURA, 0);
		assertTrue(t1.compareTo(t2));
		assertFalse(t1.compareTo(t3));
	}

	// @Test
	public void testInsertar() {
		Terreno t1 = new Terreno(Tipo.BOSQUE, 0);
		Terreno t2 = new Terreno(Tipo.BOSQUE, 1);
		Terreno t3 = new Terreno(Tipo.BOSQUE, 0);
		Terreno t4 = new Terreno(Tipo.BOSQUE, 1);
		Terreno t5 = new Terreno(Tipo.BOSQUE, 1);
		Terreno t6 = new Terreno(Tipo.LLANURA, 1);
		Terreno t7 = new Terreno(Tipo.LLANURA, 1);
		Terreno t8 = new Terreno(Tipo.LLANURA, 1);
		Terreno t9 = new Terreno(Tipo.LLANURA, 1);
		Terreno t10 = new Terreno(Tipo.BOSQUE, 1);
		Terreno t11 = new Terreno(Tipo.BOSQUE, 1);
		Terreno t12 = new Terreno(Tipo.LLANURA, 1);
		// Terreno t13 = new Terreno(Tipo.BOSQUE, 1);

		Domino d1 = new Domino(t9, t10, Orientacion.HORIZONTAL_IZQUIERDA);
		Domino d2 = new Domino(t11, t12, Orientacion.HORIZONTAL_DERECHA);
		Tablero tab = new Tablero(new FichaInicial(1,"amarillo"));
		tab.setTerreno(t1, new Posicion(0, 0));
		tab.setTerreno(t2, new Posicion(0, 1));
		tab.setTerreno(t3, new Posicion(0, 2));
		tab.setTerreno(t4, new Posicion(0, 3));
		tab.setTerreno(t5, new Posicion(1, 3));
		tab.setTerreno(t6, new Posicion(0, 4));
		tab.setTerreno(t7, new Posicion(1, 4));
		tab.setTerreno(t8, new Posicion(2, 4));
		assertTrue(tab.insertar(new Posicion(3, 4), d1));
		assertTrue(tab.insertar(new Posicion(2, 2), d2));

		System.out.println(tab.toString());
		assertEquals(5 * 3 + 5 * 5 + 1 * 1 + 1 * 1, tab.obtenerPuntaje());
	}
}
