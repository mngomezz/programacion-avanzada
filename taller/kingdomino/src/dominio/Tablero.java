package dominio;

import java.util.ArrayList;

public class Tablero {
	private static final int MAX_RANGE = 5;
	final int DIMENSION = 9;
	private Terreno[][] mapa;
	private boolean visitados[][];
	private ArrayList<Zona> zonas;

	/* Al crear el tablero, instancio matriz y creo el terreno inicial */
	public Tablero(FichaInicial castle) {
		mapa = new Terreno[DIMENSION][DIMENSION];
		Terreno castillo = new Terreno(Tipo.CASTILLO, castle.getColor());
		Posicion posicionInicial = new Posicion(4, 4);
		setTerreno(castillo, posicionInicial);
	}

	/* Ingreso terreno en una posicion determinada de la matriz */
	public void setTerreno(Terreno t1, Posicion posicion) {
		this.mapa[posicion.fila][posicion.columna] = t1;
	}

	/* Inserto, si es posible, un domino en la posicion indicada del tablero */
	public boolean insertar(Posicion posicion1, Domino d) {
		Posicion posicion2 = d.getPosicionTerreno2(posicion1);

		if (!puedoIngresarDomino(d, posicion1, posicion2))
			return false;

		setTerreno(d.getTerreno1(), posicion1);
		setTerreno(d.getTerreno2(), posicion2);
		return true;
	}

	/* Chequeo si es posible ingresar tanto el terreno1 como el terreno2 */
	public boolean puedoIngresarDomino(Domino d, Posicion t1, Posicion t2) {
		if (!esPosicionValida(t1) || !esPosicionValida(t2))
			return false;

		if (!rangoValido(t1) || !rangoValido(t2))
			return false;

		if (coincidenAdyacentes(t1, d.getTerreno1()) || coincidenAdyacentes(t2, d.getTerreno2()))
			return true;

		return false;
	}

	/* Chequeo validez de fila/columna y lugar en la matriz disponible */
	public boolean esPosicionValida(Posicion p) {
		if ((p.fila < 0 || p.fila > 8) || (p.columna < 0 || p.columna > 8))
			return false;

		if (mapa[p.fila][p.columna] != null)
			return false; // es invalido si ya hay un terreno ahi

		return true;
	}

	/* Chequeo si no se genera mas de 5 filas o columnas al ingresar terreno */
	public boolean rangoValido(Posicion posicion) {
		int outOfRangeCol = getOutOfRangeLine(posicion.columna);
		int outOfRangeRow = getOutOfRangeLine(posicion.fila);

		if (outOfRangeCol >= 0) {
			for (int i = 0; i < DIMENSION; i++) {
				if (mapa[i][outOfRangeCol] != null)
					return false;
			}
		}
		if (outOfRangeRow >= 0) {
			for (int i = 0; i < DIMENSION; i++) {
				if (mapa[outOfRangeRow][i] != null)
					return false;
			}
		}

		return true;
	}

	/* Obtengo fila/columna fuera de rango. Solo puede haber 1 o ninguna(-1) */
	private int getOutOfRangeLine(int line) {
		final int NO_OUT_OF_RANGE_LINE = -1;

		if (line < 4)
			return line + MAX_RANGE;
		else if (line > 4)
			return line - MAX_RANGE;

		return NO_OUT_OF_RANGE_LINE;
	}

	/* Chequeo si hay almenos un terreno adyacente que coincida en tipo */
	public boolean coincidenAdyacentes(Posicion posicion, Terreno terreno) {
		// Obtengo adyacentes DENTRO de la matriz
		Posicion anterior = obtenerPosicionAnterior(posicion);
		Posicion siguiente = obtenerPosicionSiguiente(posicion);

		// Orden de chequeo: Sentido horario empezando desde las 9.
		if (terreno.compareTo(mapa[posicion.fila][anterior.columna]))
			return true;

		if (terreno.compareTo(mapa[anterior.fila][posicion.columna]))
			return true;

		if (terreno.compareTo(mapa[siguiente.fila][posicion.columna]))
			return true;

		if (terreno.compareTo(mapa[posicion.fila][siguiente.columna]))
			return true;

		return false;
	}

	/* Obtengo, si es posible, la posicion adyacente mas cerca del (0,0) */
	private Posicion obtenerPosicionAnterior(Posicion p) {
		int filaDentroDeMatriz = Math.max(0, p.fila - 1);
		int columnaDentroDeMatriz = Math.max(0, p.columna - 1);
		return new Posicion(filaDentroDeMatriz, columnaDentroDeMatriz);
	}

	/* Obtengo, si es posible, la posicion adyacente mas cerca del (8,8) */
	private Posicion obtenerPosicionSiguiente(Posicion p) {
		int filaDentroDeMatriz = Math.min(p.fila + 1, DIMENSION - 1);
		int columnaDentroDeMatriz = Math.min(p.columna + 1, DIMENSION - 1);
		return new Posicion(filaDentroDeMatriz, columnaDentroDeMatriz);
	}

	/* Obtengo el puntaje respecto al tablero de un jugador en particular */
	public int obtenerPuntaje() {
		int puntaje = 0;
		// Creo matriz para chequear si un terreno fue ya visitado
		visitados = new boolean[DIMENSION][DIMENSION];

		// Por conveniencia, marco como visitado el terreno INICIAL
		visitados[4][4] = true;

		// Instancio el array de zonas
		zonas = new ArrayList<Zona>();

		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				procesarTerreno(new Posicion(row, col));
			}
		}

		for (Zona zona : zonas) {
			puntaje += zona.obtenerPuntaje();
			System.out.println(zona.toString());
		}

		if (reinoCompleto())
			puntaje += 5;

		if (reinoMedio())
			puntaje += 10;

		return puntaje;
	}

	/* Cuento terreno y adyacentes para obtener el total del mismo tipo */
	public void procesarTerreno(Posicion p) {

		if (mapa[p.fila][p.columna] == null || visitados[p.fila][p.columna])
			return; // Si no hay terreno en matriz o este ya fue visitado

		visitados[p.fila][p.columna] = true; // marco terreno como visitado

		// Intento de insertarlo en una zona ya existente
		boolean fueInsertado = false;
		for (Zona zona : zonas) {
			fueInsertado = zona.insertarTerreno(mapa[p.fila][p.columna], p);

			if (fueInsertado)
				break;
		}

		// Si no inserte en alguna zona existente, entonces creo nueva
		if (!fueInsertado)
			zonas.add(new Zona(mapa[p.fila][p.columna], p));

		// Consigo adyacentes que SI O SI pertenezcan a la matriz.
		Posicion anterior = obtenerPosicionAnterior(p);
		Posicion siguiente = obtenerPosicionSiguiente(p);

		if (mapa[p.fila][p.columna].compareTo(mapa[p.fila][anterior.columna]))
			procesarTerreno(new Posicion(p.fila, anterior.columna));

		if (mapa[p.fila][p.columna].compareTo(mapa[anterior.fila][p.columna]))
			procesarTerreno(new Posicion(anterior.fila, p.columna));

		if (mapa[p.fila][p.columna].compareTo(mapa[siguiente.fila][p.columna]))
			procesarTerreno(new Posicion(siguiente.fila, p.columna));

		if (mapa[p.fila][p.columna].compareTo(mapa[p.fila][siguiente.columna]))
			procesarTerreno(new Posicion(p.fila, siguiente.columna));
	}

	/* Chequeo solo los terrenos con una distancia > 2 al centro del programa */
	public boolean reinoMedio() {
		for (int fila = 1; fila < 8; fila++)
			if (mapa[fila][1] != null || mapa[fila][7] != null)
				return false;

		for (int columna = 2; columna < 7; columna++)
			if (mapa[1][columna] != null || mapa[7][columna] != null)
				return false;

		return true;
	}

	/* Cuento que haya 25 terrenos en juego */
	public boolean reinoCompleto() {
		int acumulador = 0;
		int filMax = DIMENSION;
		int colMax = DIMENSION;
		for (int f = 0; f < filMax; f++)
			for (int c = 0; c < colMax; c++)
				if (mapa[f][c] != null) {
					if (acumulador == 0) {
						if (filMax > (f + MAX_RANGE))
							filMax = f + MAX_RANGE;

						if (colMax > (c + MAX_RANGE))
							colMax = c + MAX_RANGE;
					}
					acumulador++;
				}
		return acumulador == 25;
	}

	/* Solo implementado como herramienta visual para testear */
	@Override
	public String toString() {
		String tablero = "";
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				if (mapa[row][col] == null) {
					tablero += "[  ]";
				} else {
					tablero += mapa[row][col].toString();
				}
			}
			tablero += "\n";
		}
		return tablero;
	}

	public Terreno getTerreno(int fila, int columna) {
		return mapa[fila][columna];
	}

}
