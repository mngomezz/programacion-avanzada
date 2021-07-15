package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeSet;

import org.junit.Test;

import dominio.Domino;
import dominio.DominosEnJuegoComparator;
import dominio.FichaInicial;
import dominio.Jugador;
import dominio.Orientacion;
import dominio.Terreno;
import dominio.Tipo;

public class JugadorTest {

	@Test
	public void testClaseEsLaCorrecta() {

		Jugador unNombreMuyCanchero = new Jugador("unNombreMuyCanchero");

		assertEquals(unNombreMuyCanchero.getClass(), Jugador.class);

	}

	@Test
	public void testNombreSeteado() {

		Jugador unNombreMuyCanchero = new Jugador("unNombreMuyCanchero");

		assertEquals("unNombreMuyCanchero", unNombreMuyCanchero.getNombre());

	}

	@Test
	public void testeaOrdenDeDominosEnManoAscendente() {

		ArrayList<Terreno> terrenos = new ArrayList<Terreno>();

		// Hay 6 terrenos posibles para 3 dominos

		terrenos.add(new Terreno(Tipo.PANTANO, 1));
		terrenos.add(new Terreno(Tipo.BOSQUE, 1));

		terrenos.add(new Terreno(Tipo.TRIGO, 0));
		terrenos.add(new Terreno(Tipo.AGUA, 0));

		terrenos.add(new Terreno(Tipo.BOSQUE, 0));
		terrenos.add(new Terreno(Tipo.BOSQUE, 0));

		TreeSet<Domino> dominosEnJuego = new TreeSet<Domino>();

		dominosEnJuego.add(new Domino(terrenos.get(0), terrenos.get(1), Orientacion.HORIZONTAL_DERECHA));
		dominosEnJuego.add(new Domino(terrenos.get(1), terrenos.get(2), Orientacion.HORIZONTAL_IZQUIERDA));
		dominosEnJuego.add(new Domino(terrenos.get(3), terrenos.get(4), Orientacion.VERTICAL_ARRIBA));

		// Como tiene una variable estatica y hay otro test que tambien crea dominos, el
		// valor que devuelve va a depender de como se ejecuten los test.
		// El primer elemento es el domino numero
//		assertEquals(3, dominosEnJuego.first().getNumero());
//
//		// El ultimo elemento es el domino numero 
//		assertEquals(5, dominosEnJuego.last().getNumero());
	}

	@Test
	public void testseteaElegirDomino() {

		// Jugador unNombreMuyCanchero = new Jugador("unNombreMuyCanchero");

		ArrayList<Terreno> terrenos = new ArrayList<Terreno>();

		// Hay 6 terrenos posibles para 3 dominos

		terrenos.add(new Terreno(Tipo.PANTANO, 1));
		terrenos.add(new Terreno(Tipo.BOSQUE, 1));

		terrenos.add(new Terreno(Tipo.TRIGO, 0));
		terrenos.add(new Terreno(Tipo.AGUA, 0));

		terrenos.add(new Terreno(Tipo.BOSQUE, 0));
		terrenos.add(new Terreno(Tipo.BOSQUE, 0));

		TreeSet<Domino> dominosEnJuego = new TreeSet<Domino>(new DominosEnJuegoComparator());
		Domino dominoAComparar1 = new Domino(terrenos.get(0), terrenos.get(1), Orientacion.VERTICAL_ARRIBA);
		Domino dominoAComparar2 = new Domino(terrenos.get(1), terrenos.get(2), Orientacion.HORIZONTAL_DERECHA);
		Domino dominoAComparar3 = new Domino(terrenos.get(3), terrenos.get(4), Orientacion.VERTICAL_ABAJO);

		dominosEnJuego.add(dominoAComparar1);
		dominosEnJuego.add(dominoAComparar2);
		dominosEnJuego.add(dominoAComparar3);

//		assertEquals(dominoAComparar1.getNumero(),unNombreMuyCanchero.elegirDomino(dominosEnJuego).getNumero());

	}

	@Test
	public void testseteaElegirFichaInicial() {

		Jugador unNombreMuyCanchero = new Jugador("unNombreMuyCanchero");

		ArrayList<FichaInicial> fichasIniciasles = new ArrayList<FichaInicial>();

		// Hay 6 terrenos posibles para 3 dominos

		FichaInicial ficha1 = new FichaInicial(1, "rojo");
		FichaInicial ficha2 = new FichaInicial(2, "amarillo");
		FichaInicial ficha3 = new FichaInicial(3, "verde");
		FichaInicial ficha4 = new FichaInicial(4, "azul");

		fichasIniciasles.add(ficha1);
		fichasIniciasles.add(ficha2);
		fichasIniciasles.add(ficha3);
		fichasIniciasles.add(ficha4);

		assertEquals(ficha1.getNumeroFichaInicial(),
				unNombreMuyCanchero.elegirFichaInicial(fichasIniciasles).getNumeroFichaInicial());

	}
}
