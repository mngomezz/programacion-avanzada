package Test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import dominio.*;
import excepciones.NoHayMasDominos;

public class RondaTest {

//	@Test
//	public void test() throws NoHayMasDominos {
//
//		TreeSet<Jugador> jugadores = new TreeSet<Jugador>();
//
//		ArrayList<FichaInicial> fichasDeJugadores = new ArrayList<FichaInicial>();
//
//		jugadores.add(new Jugador("jugador1"));
//		jugadores.add(new Jugador("jugador2"));
//		jugadores.add(new Jugador("jugador3"));
//		jugadores.add(new Jugador("jugador4"));
//
//		Ronda ronda = new Ronda(jugadores);
//		Mazo mazo = new Mazo();
//		ronda.iniciarPrimeraRonda(mazo);
//
//		Iterator<Jugador> jugadoresConFichas = jugadores.iterator();
//
//		while (jugadoresConFichas.hasNext()) {
//			fichasDeJugadores.add(jugadoresConFichas.next().getFichaInicial());
//		}
//
//		assertNotNull(fichasDeJugadores);
//	}

//	@Test
//	public void testOrdenDeJugadores() throws NoHayMasDominos {
//		
//		
//		TreeSet<Jugador> jugadores = new TreeSet<Jugador>();
//
////		ArrayList<FichaInicial> fichasDeJugadores = new ArrayList<FichaInicial>();
//		ArrayList<Terreno> terrenos = new ArrayList<Terreno>();
//
//		jugadores.add(new Jugador("jugador1"));
//		jugadores.add(new Jugador("jugador2"));
//		jugadores.add(new Jugador("jugador3"));
//		jugadores.add(new Jugador("jugador4"));
//		
//		Ronda ronda = new Ronda(jugadores);
//		
//		terrenos.add(new Terreno(Tipo.PANTANO, 1));
//		terrenos.add(new Terreno(Tipo.BOSQUE, 1));
//
//		terrenos.add(new Terreno(Tipo.TRIGO, 0));
//		terrenos.add(new Terreno(Tipo.AGUA, 0));
//
//		terrenos.add(new Terreno(Tipo.BOSQUE, 0));
//		terrenos.add(new Terreno(Tipo.BOSQUE, 0));
//
//		TreeSet<Domino> dominosEnJuego = new TreeSet<Domino>();
//
//		dominosEnJuego.add(new Domino(terrenos.get(0), terrenos.get(1), Orientacion.HORIZONTAL_DERECHA));
//		dominosEnJuego.add(new Domino(terrenos.get(1), terrenos.get(2), Orientacion.HORIZONTAL_IZQUIERDA));
//		dominosEnJuego.add(new Domino(terrenos.get(3), terrenos.get(4), Orientacion.VERTICAL_ARRIBA));
//		dominosEnJuego.add(new Domino(terrenos.get(3), terrenos.get(4), Orientacion.VERTICAL_ARRIBA));
//		
//
//		
//		for (Iterator iterator = jugadores.iterator(); iterator.hasNext();) {
//			Jugador jugador = (Jugador) iterator.next();
//			System.out.println(jugador);
//		}
//
////		Ronda ronda = new Ronda(jugadores);
////		Mazo mazo = new Mazo();
////		ronda.iniciarPrimeraRonda(mazo);
////
////		Iterator<Jugador> jugadoresConFichas = jugadores.iterator();
////
////		while (jugadoresConFichas.hasNext()) {
////			fichasDeJugadores.add(jugadoresConFichas.next().getFichaInicial());
////		}
////
////		assertNotNull(fichasDeJugadores);
//	}

	@Test
	public void testIniciarRonda() throws NoHayMasDominos {

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

//		ArrayList<FichaInicial> fichasDeJugadores = new ArrayList<FichaInicial>();

		jugadores.add(new Jugador("LARA"));
		jugadores.add(new Jugador("martin"));
		jugadores.add(new Jugador("juli"));
		jugadores.add(new Jugador("tam"));

//		Ronda ronda = new Ronda(jugadores);
		Mazo mazo = new Mazo();

//		ronda.iniciarPrimeraRonda(mazo);

		Iterator<Jugador> jugadoresConFichas = jugadores.iterator();

		while (jugadoresConFichas.hasNext()) {
			Jugador jugador = jugadoresConFichas.next();
			System.out.println(jugador.getNombre());
			System.out.println(jugador.getTurno());
			System.out.println(jugador.getFichaInicial().getNumeroFichaInicial());
//			fichasDeJugadores.add(jugadoresConFichas.next().getFichaInicial());
		}

//		assertNotNull(fichasDeJugadores);
	}

}
