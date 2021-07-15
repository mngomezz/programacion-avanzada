package dominio;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Partida {

	public static final int TOTAL_RONDAS = 12;
	public int cantidadRondas = 1;
	private ArrayList<Jugador> jugadores;
	private Ronda ronda;
	private Mazo mazo;

	public Mazo getMazo() {
		return mazo;
	}

	// Constructor
	public Partida(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;

		// preguntar por que color elije cada uno
		this.mazo = new Mazo();

		ronda = new Ronda(this, jugadores);
	}

	// Getters and setters
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	private Jugador definirGanador() {
		String mensaje = "";
		int cantidadDePuntosMaximos = -1;
		int cantidadDePuntos;
		Jugador jugadorGanador = null;

		Iterator<Jugador> jugadores = this.jugadores.iterator();

		while (jugadores.hasNext()) {
			Jugador jugador = jugadores.next();

			cantidadDePuntos = jugador.obtenerPuntaje();
			mensaje += jugador + " obtuvo un total de " + cantidadDePuntos + " puntos. \n";
			if (cantidadDePuntos > cantidadDePuntosMaximos) {
				jugadorGanador = jugador;
				cantidadDePuntosMaximos = cantidadDePuntos;
			}

		}
		mensaje += "El ganador es " + jugadorGanador + ", felicitaciones!";
		JOptionPane.showMessageDialog(null, mensaje);
		return jugadorGanador;
	}

	public Ronda getRonda() {
		return ronda;
	}

	public void siguienteRonda(ArrayList<Jugador> jugadores) {
		cantidadRondas++;
		if (cantidadRondas <= TOTAL_RONDAS) {
			this.ronda = new Ronda(this, jugadores);
		} else {
			ronda.mensaje = "EL GANADOR ES " + definirGanador() + ", FELICITACIONES!";
//			JOptionPane.showMessageDialog(null, ronda.mensaje);
		}
	}
}
