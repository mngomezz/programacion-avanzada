package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import excepciones.NoHayMasDominos;

public class Ronda {

	private Jugador jugadorTurno;
	private ArrayList<Jugador> jugadores;

	private ArrayList<Domino> dominosEnJuego;
	public String mensaje;
	public Partida partida;

	public ArrayList<Domino> getDominosEnJuego() {
		return dominosEnJuego;
	}

	public Ronda(Partida partida2, ArrayList<Jugador> jugadores) {
		this.partida = partida2;
		this.jugadores = jugadores;

		jugadorTurno = jugadores.get(0);
		String opcion = (partida2.cantidadRondas == 1) ? "color" : "domino";
		mensaje = getTurnoText() + " Seleccione un " + opcion;

		dominosEnJuego = new ArrayList<Domino>();
		// Los dominosEnJuego se van a ir ordenando de manera ordenada para que luego
		// Se acomoden en el tablero de manera ordenada
		try {
			repartirMazo(partida2.getMazo());
		} catch (NoHayMasDominos e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getTurnoText() {
		return "Es el turno de " + jugadorTurno + "!";
	}

	public void repartirMazo(Mazo mazo) throws NoHayMasDominos {
		dominosEnJuego.addAll(mazo.obtenerDominos()); // MODIFICADO POR TENER MAZO DISTINTO
		System.out.println(dominosEnJuego);
	}

	public void iniciarPrimeraRonda(Mazo maso) {

		Iterator<Jugador> jugadores = this.jugadores.iterator();

		ArrayList<FichaInicial> fichasInicialesRecorrer = new ArrayList<FichaInicial>(maso.getFichasIniciales());

		while (jugadores.hasNext()) {
			jugadorTurno = jugadores.next();
			jugadorTurno.tomarFichaInicial(jugadorTurno.elegirFichaInicial(maso.getFichasIniciales()));
		}

		Collections.shuffle(fichasInicialesRecorrer);
		// Eliminamos la ficha iniciales de mas
		maso.getFichasIniciales().clear();

		for (int i = 0; i < 4; i++) {
			jugadores = this.jugadores.iterator();
			while (jugadores.hasNext()) {
				Jugador jugador = jugadores.next();

				if (jugador.getFichaInicial().getNumeroFichaInicial() == fichasInicialesRecorrer.get(i)
						.getNumeroFichaInicial())
					jugador.setTurno(i + 1);

			}

		}

		return;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Jugador getJugadorTurno() {
		return jugadorTurno;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public boolean siguienteEleccionFicha() {
		int indiceJugadorTurno = jugadores.indexOf(jugadorTurno);

		if (indiceJugadorTurno == jugadores.size() - 1) {
			jugadorTurno = jugadores.get(0);
			mensaje = "A jugar! " + getTurnoText() + " Seleccione un domino";
			return false;
		}

		jugadorTurno = jugadores.get(indiceJugadorTurno + 1);
		mensaje = getTurnoText() + " Seleccione su color";
		return true;
	}

	public boolean siguienteEleccion() {
		int indiceJugadorTurno = jugadores.indexOf(jugadorTurno);

		if (indiceJugadorTurno == jugadores.size() - 1) {
			jugadores.sort(new Comparator<Jugador>() {
				@Override
				public int compare(Jugador o1, Jugador o2) {
					return Integer.compare(o1.getDominoElegido().getNumero(), o2.getDominoElegido().getNumero());
				}
			});
			jugadorTurno = jugadores.get(0);
			mensaje = getTurnoText() + " Posicione su domino eligiendo la posicion del terreno 1";
			return false;
		}

		jugadorTurno = jugadores.get(indiceJugadorTurno + 1);
		mensaje = getTurnoText() + " Seleccione su domino";
		return true;
	}

	public boolean siguientePosicionamiento() {
		int indiceJugadorTurno = jugadores.indexOf(jugadorTurno);

		if (indiceJugadorTurno == jugadores.size() - 1) {
			partida.siguienteRonda(jugadores);
			return false;
		}

		jugadorTurno = jugadores.get(indiceJugadorTurno + 1);
		mensaje = getTurnoText() + " Posicione su domino eligiendo la posicion del terreno 1";
		return true;
	}

}
