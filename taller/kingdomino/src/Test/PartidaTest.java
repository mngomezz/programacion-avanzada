package Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import dominio.*;
import excepciones.NoHayMasDominos;

public class PartidaTest {

	@Test
	public void test() throws NoHayMasDominos {

		Jugador jugadorGanador = new Jugador("Paco");
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

		jugadores.add(new Jugador("Lara"));
		jugadores.add(new Jugador("Tam"));
		jugadores.add(new Jugador("Pepito"));
		jugadores.add(new Jugador("Josesito"));

		Partida partidaNueva = new Partida(jugadores);

//		jugadorGanador = partidaNueva.jugarPartida();

//		assertEquals(jugadorGanador, jugadores.get(0));
	}

}
