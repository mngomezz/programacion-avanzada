package lanzandoElCaber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

class LanzamientoCaberTest {

	@Test
	void esTiroValidoTest() {

		LanzamientoCaber lanzamiento1 = new LanzamientoCaber(2, 90);
		LanzamientoCaber lanzamiento2 = new LanzamientoCaber(1, 0);
		LanzamientoCaber lanzamiento3 = new LanzamientoCaber(2, 90);
		LanzamientoCaber lanzamiento4 = new LanzamientoCaber(4, 180);

		assertTrue(lanzamiento1.esTiroValido());
		assertTrue(lanzamiento2.esTiroValido());
		assertTrue(lanzamiento3.esTiroValido());
		assertFalse(lanzamiento4.esTiroValido());

	}

	@Test
	void obtenerDistanciaRealTest() {

		LanzamientoCaber lanzamiento1 = new LanzamientoCaber(2, 30);
		LanzamientoCaber lanzamiento2 = new LanzamientoCaber(1, -30);
		LanzamientoCaber lanzamiento3 = new LanzamientoCaber(2, 90);
		LanzamientoCaber lanzamiento4 = new LanzamientoCaber(3, -90);
		LanzamientoCaber lanzamiento5 = new LanzamientoCaber(2, 180);
		LanzamientoCaber lanzamiento6 = new LanzamientoCaber(4, -180);

		Assert.assertEquals(2.0, lanzamiento1.obtenerDistanciaReal(), 0.1);
		Assert.assertEquals(1.0, lanzamiento2.obtenerDistanciaReal(), 0.1);
		Assert.assertEquals(1.6, lanzamiento3.obtenerDistanciaReal(), 0.1);
		Assert.assertEquals(2.4, lanzamiento4.obtenerDistanciaReal(), 0.1);
		Assert.assertEquals(0.0, lanzamiento5.obtenerDistanciaReal(), 0.1);
		Assert.assertEquals(0.0, lanzamiento6.obtenerDistanciaReal(), 0.1);
	}

	@Test
	void esConsistenteTest() {

		LanzamientoCaber lanzamiento1 = new LanzamientoCaber(2, 30);
		LanzamientoCaber lanzamiento2 = new LanzamientoCaber(1, -30);
		LanzamientoCaber lanzamiento3 = new LanzamientoCaber(2, 91);
		LanzamientoCaber lanzamiento4 = new LanzamientoCaber(2, -91);
		LanzamientoCaber lanzamiento5 = new LanzamientoCaber(4, 180);

		assertTrue(lanzamiento1.esConsistente());
		assertTrue(lanzamiento2.esConsistente());
		assertFalse(lanzamiento3.esConsistente());
		assertFalse(lanzamiento4.esConsistente());
		assertFalse(lanzamiento5.esConsistente());

	}

}
