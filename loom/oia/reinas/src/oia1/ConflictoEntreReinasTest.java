package oia1;

import org.junit.Test;

public class ConflictoEntreReinasTest {

	@Test
	public void testOffsets() {
		Tablero t = new Tablero(8);
		Reina r1 = new Reina(2, 2);
		Reina r2 = new Reina(6, 4);
		Reina r3 = new Reina(1, 6);

		t.agregarReina(r1);
		t.agregarReina(r2);
		t.agregarReina(r3);
		t.agregarReina(new Reina(3, 5));
		t.agregarReina(new Reina(1, 1));
		t.agregarReina(new Reina(2, 4));
		t.agregarReina(new Reina(2, 3));
		t.agregarReina(new Reina(3, 2));
		t.agregarReina(new Reina(4, 1));
		// t.obtenerReinasEnConflicto(r1);
		System.out.println(t);
	}

	
}