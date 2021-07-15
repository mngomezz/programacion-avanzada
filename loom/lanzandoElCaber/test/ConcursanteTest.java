package lanzandoElCaber;

import org.junit.Assert;
import org.junit.Test;

class ConcursanteTest {

	@Test
	void obtenerDistanciaTotalTest() {

		Concursante romulo = new Concursante();
		Concursante remo = new Concursante();

		romulo.agregarCaberLanzado(new LanzamientoCaber(2, 30));
		romulo.agregarCaberLanzado(new LanzamientoCaber(1, -30));
		romulo.agregarCaberLanzado(new LanzamientoCaber(3, 0));

		remo.agregarCaberLanzado(new LanzamientoCaber(2, 90));
		remo.agregarCaberLanzado(new LanzamientoCaber(2, 45));
		remo.agregarCaberLanzado(new LanzamientoCaber(2, -45));

		Assert.assertEquals(6.0, romulo.obtenerDistanciaTotal(), 0.1);
		Assert.assertEquals(4.8, remo.obtenerDistanciaTotal(), 0.1);

	}

	@Test
	void obtenerConsistencia() {

		Concursante romulo = new Concursante();
		Concursante remo = new Concursante();

		romulo.agregarCaberLanzado(new LanzamientoCaber(2, 30));
		romulo.agregarCaberLanzado(new LanzamientoCaber(1, -30));
		romulo.agregarCaberLanzado(new LanzamientoCaber(3, 0));

		remo.agregarCaberLanzado(new LanzamientoCaber(2, 90));
		remo.agregarCaberLanzado(new LanzamientoCaber(2, 45));
		remo.agregarCaberLanzado(new LanzamientoCaber(2, -45));

		Assert.assertEquals(1, romulo.obtenerConsistencia());
		Assert.assertEquals(0, remo.obtenerConsistencia());

	}

}
