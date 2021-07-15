package dominio;

import java.awt.Point;
import java.util.ArrayList;

// Testeado por Tam

public class Jugador implements Comparable<Jugador> {

	private String nombre;
	private Tablero tablero;
	private Domino dominoElegido;
	private FichaInicial fichaInicial;
	private Point screenPosition; // parte Grafica

	private int turno;

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	// Setters and getters
	public String getNombre() {
		return nombre;
	}

	public Domino getDominoElegido() {
		return dominoElegido;
	}

	public Tablero getTablero() {
		return tablero;
	}

	@Override
	public String toString() {
		return nombre;
	}

	// Constructores
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.tablero = new Tablero(new FichaInicial(1, "amarillo"));
	}

	public void tomarFichaInicial(FichaInicial ficha) {
		this.fichaInicial = ficha;
	}

	public void tomarDomino(Domino domino) {
		this.dominoElegido = domino;
	}

	public FichaInicial elegirFichaInicial(ArrayList<FichaInicial> fichasAElegir) {

		// Usuario elige por pantalla
		FichaInicial nuevaFicha = fichasAElegir.get(0);

		fichasAElegir.remove(nuevaFicha);

		return nuevaFicha;
	}

	public Posicion posicionarDomino() {

		// System.out.println("Por favor debe elegir una posicion para insertar
		// el
		// domino");
		Posicion posicionTerreno1 = new Posicion(3, 1); // Hardcoded

		return posicionTerreno1;
	}

	public boolean insertarEnTablero(Posicion posicionTerreno1) {
		return tablero.insertar(posicionTerreno1, dominoElegido);
	}

	public FichaInicial getFichaInicial() {
		return fichaInicial;
	}

	@Override
	public int compareTo(Jugador o) {
		return this.getNombre().compareTo(o.getNombre());
	}

	public int obtenerPuntaje() {
		return this.tablero.obtenerPuntaje();
	}

	/* Utilizado en parte grafica */
	public Point getScreenPosition() {
		return screenPosition;
	}

	public void setScreenPosition(Point posicion) {
		screenPosition = posicion;
	}

}
