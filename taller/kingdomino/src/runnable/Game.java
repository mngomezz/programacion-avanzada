package runnable;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import dominio.Jugador;
import dominio.Partida;
import grafica.DrawPanel;
import soporte.Imagenes;

public class Game extends JFrame {
	static final long serialVersionUID = 1L;

	public static final int SQUARE = 100;
	public static final int WIDTH = SQUARE * 16;
	public static final int HEIGHT = SQUARE * 9;
	public static Dimension dimension;

	public ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	public Partida partida;
	public DrawPanel drawPanel;

	/* Indicadores de estado de juego */
	public boolean enJuego = false;
	public boolean mostrarInterfazFichasIniciales = false;
	public boolean mostrarJugadores = false;
	public boolean mostrarTableros = false;
	public boolean mostrarDominos = false;
	public boolean puedeClickear = false;

	public boolean mostrarInterfazEleccion = false;
	public boolean mostrarInterfazPosicionar = false;

	public Game() {

	}

	public void init() {

		Imagenes.init();
		drawPanel = new DrawPanel(this);
		add(drawPanel);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
		setTitle("Kingdomino");
		drawPanel.mostrarMenuPrincipal();
	}

	public static void main(String[] args) throws Exception {
		Game game = new Game();
		game.init();
	}
}
