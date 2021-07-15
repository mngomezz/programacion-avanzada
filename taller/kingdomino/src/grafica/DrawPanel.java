package grafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.Domino;
import dominio.FichaInicial;
import dominio.Jugador;
import dominio.Partida;
import dominio.Posicion;
import dominio.Ronda;
import dominio.Tablero;
import runnable.Game;
import soporte.Imagenes;

public class DrawPanel extends JPanel {
	private static final long serialVersionUID = 91574813372177663L;

	private static final int WIDTH = 1080;
	private static final int HEIGHT = 720;
	private BufferedImage gameBackground;
	private BufferedImage mainBackground;
	private BufferedImage interfazTurno;
	private BufferedImage interfazMensaje;
	private BufferedImage fondoUsuarios;
	private BufferedImage salaBackground;
	private Game game;
	Dimension dimension;
	private Jugador currentPlayer; // Se deposita en variable global al jugador al iterar entre ellos

	// Posicion en la cual se dibuja los datos del jugador iterado.
	private int playerX;
	private int playerY;

	// Posicion en la cual se dibuja el tablero del jugador iterado.
	private int tableroX;
	private int tableroY;

	// Posicion y tamaño de la interfaz a dibujar (turno y mensaje).
	static final int INTERFAZ_HEIGHT = 50;
	static final int INTERFAZ_Y = HEIGHT - INTERFAZ_HEIGHT;
	static final int INTERFAZ_OFFSET_X = 40;
	static final int INTERFAZ_TEXT_Y = INTERFAZ_Y + 35;

	// Anchura y posicion en el eje X de la interfaz de turno.
	static final int TURNO_WIDTH = 200;
	static final int TURNO_X = 0;

	// Anchura y posicion en el eje X de la interfaz de mensaje de ronda.
	static final int MSJ_WIDTH = WIDTH - TURNO_WIDTH;
	static final int MSJ_X = TURNO_WIDTH;

	// Posicion donde se desarrolla la ronda (entre los tableros).
	static final int RONDA_X = 400;
	static final int RONDA_Y = 100;

	static final int DIMENSION_TABLERO = 9;// (filas x columnas)
	static final int TABLERO_SIZE = 300; // pixeles del tablero
	static int terrenoSize = TABLERO_SIZE / DIMENSION_TABLERO; // pixeles de terreno

	private final int X1 = 10;
	private final int X2 = 763;
	private final int Y1 = 20;

	private boolean deboMostrarDomino1;
	private boolean deboMostrarDomino2;
	private boolean deboMostrarDomino3;
	private boolean deboMostrarDomino4;

	private boolean deboMostrarFicha1;
	private boolean deboMostrarFicha2;
	private boolean deboMostrarFicha3;
	private boolean deboMostrarFicha4;

	public DrawPanel(Game game) {
		gameBackground = Imagenes.gameBackground;
		mainBackground = Imagenes.mainBackground;
		interfazTurno = Imagenes.interfazTurno;
		interfazMensaje = Imagenes.interfazMensaje;
		fondoUsuarios = Imagenes.fondoUsuarios;
		salaBackground = Imagenes.salaBackground;
		this.game = game;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				super.mouseClicked(me);
				Point point = me.getPoint();
				System.out.println(game.enJuego);

				if (game.mostrarInterfazPosicionar) {
					PosicionTablero posTab = new PosicionTablero(point, X1, X2, Y1, DIMENSION_TABLERO);
					System.out.println("(" + posTab.fila + ", " + posTab.columna + ")");
					if (posTab.estaEnRangoTablero(point)) {
						if (currentPlayer.insertarEnTablero(new Posicion(posTab.fila, posTab.columna))) {
							if (game.partida.getRonda().siguientePosicionamiento() == false) {
								game.mostrarInterfazEleccion = true;
								game.mostrarInterfazPosicionar = false;
								deboMostrarDomino1 = true;
								deboMostrarDomino2 = true;
								deboMostrarDomino3 = true;
								deboMostrarDomino4 = true;
								removeAll();
							}
						}
					}
				}
				System.out.print("Click en: [" + (point.x * WIDTH / dimension.getWidth()) + ", ");
				System.out.println(point.y * HEIGHT / dimension.getHeight() + "]");
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		dimension = game.getContentPane().getSize();
		g2.scale(dimension.getWidth() / WIDTH, dimension.getHeight() / HEIGHT);

		try {
			g2.drawImage(Imagenes.resizeImage(mainBackground, (int) dimension.getWidth(), (int) dimension.getHeight()),
					null, 0, 0);

			if (game.mostrarJugadores) {

				g2.drawImage(
						Imagenes.resizeImage(salaBackground, (int) dimension.getWidth(), (int) dimension.getHeight()),
						null, 0, 0);
				fondoUsuarios = Imagenes.fondoUsuarios;
				g2.drawImage(Imagenes.resizeImage(fondoUsuarios, 300, 450), 176, 255, null, null);

//				mostrarMensajeJugador(g2, "Sala de jugadores", new Font("Calibri", 0, 40), Color.WHITE, 220, 55);
				mostrarMensajeJugador(g2, "Sala", new Font("Calibri", 0, 30), Color.WHITE, 300, 300);

				if (game.jugadores.size() != 0) {
					for (int i = 0; i < game.jugadores.size(); i++) {
						mostrarMensajeJugador(g2, game.jugadores.get(i).getNombre(), new Font("Calibri", 0, 20),
								Color.WHITE, 280, 400 + i * 75);

					}
				}
			} else if (game.enJuego) {
				g2.drawImage(gameBackground, null, 0, 0);

				mostrarInterfaz(g2);

				if (game.mostrarInterfazFichasIniciales) {
					mostrarInterfazFichasIniciales(g2);
				}
				if (game.mostrarInterfazEleccion) {
					mostrarInterfazEleccion(g2);
				}
				if (game.mostrarInterfazPosicionar) {
					mostrarInterfazPosicionar(g2);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	public void mostrarMenuPrincipal() {
		JButton btnJugar = new JButton("Jugar");
		btnJugar.setForeground(Color.BLACK);
		btnJugar.setFont(new Font("Dialog", Font.BOLD, 24));
		btnJugar.setBounds(376, 343, 275, 50);
		btnJugar.setBackground(Color.ORANGE);
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMenuCreacionSalas();
				repaint();
			}
		});
		add(btnJugar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setBounds(377, 451, 275, 50);
		btnSalir.setBackground(Color.ORANGE);
		btnSalir.setFont(new Font("Arial", Font.BOLD, 24));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnSalir);

	}

	public void mostrarMenuCreacionSalas() {
		removeAll();

		game.mostrarJugadores = true;

		JTextField inputJugadorName = new JTextField();
		inputJugadorName.setBounds(753, 391, 206, 41);
		inputJugadorName.setBackground(Color.ORANGE);
		inputJugadorName.setFont(new Font("Dialog", Font.BOLD, 22));
		inputJugadorName.setForeground(Color.BLACK);
		add(inputJugadorName);

		// Boton para agregar jugadores
		JButton boton1 = new JButton("Agregar Jugador");
		boton1.setBounds(753, 458, 206, 39);
		boton1.setForeground(Color.BLACK);
		boton1.setBackground(Color.ORANGE);
		boton1.setFont(new Font("Dialog", Font.BOLD, 18));
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputJugadorName.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "El nombre no debe estar vacio.");
				else if (game.jugadores.size() == 4)
					JOptionPane.showMessageDialog(null, "No puede haber mas jugadores.");
				else {
					game.jugadores.add(new Jugador(inputJugadorName.getText()));
					inputJugadorName.setText("");
					repaint();
				}

			}
		});
		add(boton1);
		JButton btnEmpezarPartida = new JButton("Empezar Partida");
		btnEmpezarPartida.setBounds(753, 575, 200, 75);
		btnEmpezarPartida.setForeground(Color.BLACK);
		btnEmpezarPartida.setBackground(Color.ORANGE);
		btnEmpezarPartida.setFont(new Font("Dialog", Font.BOLD, 18));
		btnEmpezarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (game.jugadores.size() < 2)
					JOptionPane.showMessageDialog(null, "Debe haber mas jugadores para empezar.");
				else {
					game.partida = new Partida(game.jugadores);
					setPlayersPositions(game.partida.getJugadores());

					game.enJuego = true;
					game.mostrarInterfazFichasIniciales = true;
					game.mostrarJugadores = false;
					deboMostrarFicha1 = true;
					deboMostrarFicha2 = true;
					deboMostrarFicha3 = true;
					deboMostrarFicha4 = true;
					removeAll();
					repaint();
				}
			}
		});
		add(btnEmpezarPartida);

	}

	/* Indico la posicion que ocupa cada jugador en la pantalla */
	private void setPlayersPositions(ArrayList<Jugador> jugadores) {
		ArrayList<Point> posiciones = getPlayersPositions(jugadores);
		for (Jugador jugador : jugadores) {
			jugador.setScreenPosition(posiciones.remove(0));
		}
	}

	private ArrayList<Point> getPlayersPositions(ArrayList<Jugador> jugadores) {
		final int CANT_JUGADORES = jugadores.size();
		ArrayList<Point> posiciones = new ArrayList<Point>();
		for (int i = 0; i < CANT_JUGADORES; i++) {
			posiciones.add(getPosition(i));
		}
		return posiciones;
	}

	private Point getPosition(int nroJugador) {
		final int MIN_X = 10, MIN_Y = 20;
		int x, y;
		if (nroJugador == 0) {
			x = MIN_X;
			y = MIN_Y;
		} else if (nroJugador == 1) {
			x = WIDTH - TABLERO_SIZE - MIN_X;
			y = MIN_Y;
		} else if (nroJugador == 2) {
			x = MIN_X;
			y = HEIGHT - TABLERO_SIZE - INTERFAZ_HEIGHT - MIN_Y;
		} else {
			x = WIDTH - TABLERO_SIZE - MIN_X;
			y = HEIGHT - TABLERO_SIZE - INTERFAZ_HEIGHT - MIN_Y;
		}
		return new Point(x, y);
	}

	/*
	 * Clase que implementa la logica del posicionamiento del tablero
	 */

	public class PosicionTablero {

		private int x1;
		private int y1;
		private int largoSeccion;
		private int dimension;

		public int fila = -1;
		public int columna = -1;

		public PosicionTablero(Point point, int x1, int x2, int y1, int dimension) {
			this.x1 = tableroX;
			this.y1 = tableroY;
			largoSeccion = terrenoSize * 2;
			this.dimension = dimension;

			evaluarClickEnTablero(point);
		}

		private void evaluarClickEnTablero(Point point) {

			if (!estaEnRangoTablero(point))
				return;

			fila = obtenerFila(point.y);
			columna = obtenerColumna(point.x);

		}

		private int obtenerFila(int y) {
			return obtenerFilaOColumna(y, y1);
		}

		private int obtenerColumna(int x) {
			return obtenerFilaOColumna(x, x1);
		}

		private int obtenerFilaOColumna(int posMouse, int pos1) {

			int comienzoCuadro = 0, finCuadro = 0;

			for (int i = 0; i < dimension; i++) {

				comienzoCuadro = (pos1 + (i) * largoSeccion);
				finCuadro = comienzoCuadro + largoSeccion;

				if (posMouse >= comienzoCuadro && posMouse <= finCuadro)
					return i;
			}

			return -1;
		}

		private boolean estaEnRangoTablero(Point point) {
			return estaEnRangoX(point) && estaEnRangoY(point);

		}

		private boolean estaEnRangoX(Point point) {
			return (point.x >= x1 && point.x <= (x1 + (largoSeccion * DIMENSION_TABLERO)));
		}

		private boolean estaEnRangoY(Point point) {
			return (point.y >= y1 && point.y <= (y1 + (largoSeccion * DIMENSION_TABLERO)));
		}

	}

	/*
	 * Clase que implementa metodos para dibujar(mostrar) los componentes del juego
	 */

	private void mostrarInterfazPosicionar(Graphics2D graphics) {

		Ronda rondaActual = game.partida.getRonda();
		currentPlayer = rondaActual.getJugadorTurno();
		playerX = 10;
		playerY = 10;
		tableroX = playerX;
		tableroY = playerY + 10;
		mostrarNombre(graphics);

		Tablero tablero = currentPlayer.getTablero();
		tableroX = 10;
		tableroY = 30;
		for (int f = 0; f < DIMENSION_TABLERO; f++) {
			for (int c = 0; c < DIMENSION_TABLERO; c++) {
				int terrenoX = tableroX + c * terrenoSize * 2;
				int terrenoY = tableroY + f * terrenoSize * 2;
				try {
					graphics.setColor(tablero.getTerreno(f, c).getColor());
					graphics.fillRect(terrenoX, terrenoY, terrenoSize * 2, terrenoSize * 2);
					Image imagenTerreno = tablero.getTerreno(f, c).getImagen().getScaledInstance(terrenoSize * 2,
							terrenoSize * 2, 0);
					graphics.drawImage(imagenTerreno, terrenoX, terrenoY, null);
				} catch (Exception e) {
					if (tablero.getTerreno(f, c) == null) {
						graphics.setColor(new Color(0, 0, 0, 0.1f));
						graphics.fillRect(terrenoX, terrenoY, terrenoSize * 2, terrenoSize * 2);
					} else {
						// Si no es null el terreno y no consegui la imagen con coronas, es una ficha
						// inicial
						try {
							Image imagenFicha = currentPlayer.getFichaInicial().getImagen()
									.getScaledInstance(terrenoSize * 2, terrenoSize * 2, 0);
							graphics.drawImage(imagenFicha, terrenoX, terrenoY, null);
						} catch (Exception e1) {
							graphics.setColor(tablero.getTerreno(f, c).getColor());
							graphics.fillRect(terrenoX, terrenoY, terrenoSize * 2, terrenoSize * 2);
						}
					}
				} finally {
					graphics.setColor(Color.BLACK);
					graphics.drawRect(terrenoX, terrenoY, terrenoSize * 2, terrenoSize * 2);
				}
			}
		}
		mostrarDomino(graphics, currentPlayer.getDominoElegido(), WIDTH - TABLERO_SIZE, 100);
		mostrarBotonesPosicionar();
	}

	private void mostrarBotonesPosicionar() {
		// Boton para agregar jugadores
		JButton btnRotar = new JButton();

		btnRotar = new JButton("Rotar");
		btnRotar.setBounds(WIDTH - 300, HEIGHT - INTERFAZ_HEIGHT - 400, 150, terrenoSize);
		btnRotar.setForeground(Color.BLACK);
		btnRotar.setBackground(Color.ORANGE);
		btnRotar.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRotar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentPlayer.getDominoElegido().rotar();
			}
		});
		add(btnRotar);

		JButton btnDescartar = new JButton();
		btnDescartar = new JButton("Descartar");
		btnDescartar.setBounds(WIDTH - 300, HEIGHT - INTERFAZ_HEIGHT - 200, 150, terrenoSize);
		btnDescartar.setForeground(Color.BLACK);
		btnDescartar.setBackground(Color.ORANGE);
		btnDescartar.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.partida.getRonda().mensaje = "El domino se descarto!";
				if (game.partida.getRonda().siguientePosicionamiento() == false) {
					game.mostrarInterfazEleccion = true;
					game.mostrarInterfazPosicionar = false;

					if (game.partida.cantidadRondas > Partida.TOTAL_RONDAS) {
						deboMostrarDomino1 = false;
						deboMostrarDomino2 = false;
						deboMostrarDomino3 = false;
						deboMostrarDomino4 = false;
						removeAll();
					} else {
						deboMostrarDomino1 = true;
						deboMostrarDomino2 = true;
						deboMostrarDomino3 = true;
						deboMostrarDomino4 = true;
						removeAll();
					}
				}
			}
		});
		add(btnDescartar);

		repaint();
	}

	private void mostrarInterfazEleccion(Graphics2D graphics) {
		for (Jugador j : game.partida.getJugadores()) {
			setCurrentPlayer(j);
			mostrarNombre(graphics);
			Tablero tablero = currentPlayer.getTablero();

			for (int f = 0; f < DIMENSION_TABLERO; f++) {
				for (int c = 0; c < DIMENSION_TABLERO; c++) {
					int terrenoX = tableroX + c * terrenoSize;
					int terrenoY = tableroY + f * terrenoSize;
					try {
//						graphics.setColor(tablero.getTerreno(f, c).getColor());
//						graphics.fillRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
						Image imagenTerreno = tablero.getTerreno(f, c).getImagen().getScaledInstance(terrenoSize,
								terrenoSize, 0);
						graphics.drawImage(imagenTerreno, terrenoX, terrenoY, null);
					} catch (Exception e) {
						if (tablero.getTerreno(f, c) == null) {
							graphics.setColor(new Color(0, 0, 0, 0.1f));
							graphics.fillRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
						} else {
							// Si no es null el terreno y no consegui la imagen con coronas, es una ficha
							// inicial
							try {
								Image imagenFicha = j.getFichaInicial().getImagen().getScaledInstance(terrenoSize,
										terrenoSize, 0);
								graphics.drawImage(imagenFicha, terrenoX, terrenoY, null);
							} catch (Exception e1) {
								graphics.setColor(tablero.getTerreno(f, c).getColor());
								graphics.fillRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
							}

						}
					} finally {
						graphics.setColor(Color.BLACK);
						graphics.drawRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
					}
				}
			}
		}
		mostrarDominos(graphics);
	}

	private void mostrarBotonesEleccion() {
		Ronda rondaActual = game.partida.getRonda();
		// Boton para agregar jugadores
		JButton btnDomino1;
		JButton btnDomino2;
		JButton btnDomino3;
		JButton btnDomino4;

		btnDomino1 = new JButton("Elegir");
		btnDomino1.setBounds(RONDA_X - 50, RONDA_Y, 100, terrenoSize);
		btnDomino1.setForeground(Color.BLACK);
		btnDomino1.setBackground(Color.ORANGE);
		btnDomino1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDomino1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Sound.buttonSound.play();
				rondaActual.getJugadorTurno().tomarDomino(rondaActual.getDominosEnJuego().get(0));
				System.out.println(rondaActual.getJugadorTurno().getDominoElegido());
				if (rondaActual.siguienteEleccion() == false) {
					game.mostrarInterfazEleccion = false;
					game.mostrarInterfazPosicionar = true;
					removeAll();
				}
				deboMostrarDomino1 = false;
				removeAll();
			}
		});
		if (deboMostrarDomino1)
			add(btnDomino1);

		btnDomino2 = new JButton("Elegir");
		btnDomino2.setBounds(RONDA_X - 50, RONDA_Y + (1 * 50), 100, terrenoSize);
		btnDomino2.setForeground(Color.BLACK);
		btnDomino2.setBackground(Color.ORANGE);
		btnDomino2.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDomino2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondaActual.getJugadorTurno().tomarDomino(rondaActual.getDominosEnJuego().get(1));
				if (rondaActual.siguienteEleccion() == false) {
					game.mostrarInterfazEleccion = false;
					game.mostrarInterfazPosicionar = true;
					removeAll();
				}
				deboMostrarDomino2 = false;
				removeAll();
			}
		});
		if (deboMostrarDomino2)
			add(btnDomino2);

		btnDomino3 = new JButton("Elegir");
		btnDomino3.setBounds(RONDA_X - 50, RONDA_Y + (2 * 50), 100, terrenoSize);
		btnDomino3.setForeground(Color.BLACK);
		btnDomino3.setBackground(Color.ORANGE);
		btnDomino3.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDomino3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondaActual.getJugadorTurno().tomarDomino(rondaActual.getDominosEnJuego().get(2));
				if (rondaActual.siguienteEleccion() == false) {
					game.mostrarInterfazEleccion = false;
					game.mostrarInterfazPosicionar = true;
					removeAll();
				}
				deboMostrarDomino3 = false;
				removeAll();
			}
		});
		if (deboMostrarDomino3)
			add(btnDomino3);

		btnDomino4 = new JButton("Elegir");
		btnDomino4.setBounds(RONDA_X - 50, RONDA_Y + (3 * 50), 100, terrenoSize);
		btnDomino4.setForeground(Color.BLACK);
		btnDomino4.setBackground(Color.ORANGE);
		btnDomino4.setFont(new Font("Dialog", Font.BOLD, 18));
		btnDomino4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondaActual.getJugadorTurno().tomarDomino(rondaActual.getDominosEnJuego().get(3));
				System.out.println(rondaActual.getJugadorTurno().getDominoElegido());
				if (rondaActual.siguienteEleccion() == false) {
					game.mostrarInterfazEleccion = false;
					game.mostrarInterfazPosicionar = true;
					removeAll();
				}
				deboMostrarDomino4 = false;
				removeAll();
			}
		});
		if (deboMostrarDomino4)
			add(btnDomino4);

		repaint();
	}

	private void mostrarMensajeJugador(Graphics g2, String mensaje, Font font, Color color, int x, int y) {
		g2.setFont(font);
		g2.setColor(color);
		g2.drawString(mensaje, x, y);
	}

	// Metodo que se encarga de mostrar interfaz (zocalos de abajo)
	private void mostrarInterfaz(Graphics2D graphics) {
		mostrarMensaje(graphics);
		mostrarRonda(graphics);
	}

	// Metodo encargado de mostrar el mensaje y su interfaz
	private void mostrarMensaje(Graphics2D graphics) {
		Image resized = interfazMensaje.getScaledInstance(MSJ_WIDTH, INTERFAZ_HEIGHT, 0);
		graphics.drawImage(resized, MSJ_X, INTERFAZ_Y, null);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Ubuntu", Font.BOLD, 23));
		graphics.drawString(game.partida.getRonda().getMensaje(), MSJ_X + INTERFAZ_OFFSET_X, INTERFAZ_TEXT_Y);
	}

	// Metodo encargado de mostrar el jugador al cual le toca jugar y su interfaz
	private void mostrarRonda(Graphics2D graphics) {
		Image resized = interfazTurno.getScaledInstance(TURNO_WIDTH, INTERFAZ_HEIGHT, 0);
		graphics.drawImage(resized, TURNO_X, INTERFAZ_Y, null);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Ubuntu", Font.BOLD, 23));
		graphics.drawString("RONDA " + game.partida.cantidadRondas, TURNO_X + INTERFAZ_OFFSET_X, INTERFAZ_TEXT_Y);
	}

	private void mostrarInterfazFichasIniciales(Graphics2D graphics) {
		for (Jugador j : game.partida.getJugadores()) {
			setCurrentPlayer(j);
			mostrarNombre(graphics);
			Tablero tablero = currentPlayer.getTablero();

			for (int f = 0; f < DIMENSION_TABLERO; f++) {
				for (int c = 0; c < DIMENSION_TABLERO; c++) {
					int terrenoX = tableroX + c * terrenoSize;
					int terrenoY = tableroY + f * terrenoSize;
					try {
//						graphics.setColor(tablero.getTerreno(f, c).getColor());
//						graphics.fillRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
						Image imagenTerreno = tablero.getTerreno(f, c).getImagen().getScaledInstance(terrenoSize,
								terrenoSize, 0);
						graphics.drawImage(imagenTerreno, terrenoX, terrenoY, null);
					} catch (Exception e) {
						if (tablero.getTerreno(f, c) == null) {
							graphics.setColor(new Color(0, 0, 0, 0.1f));
							graphics.fillRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
						} else {
							// Si no es null el terreno y no consegui la imagen con coronas, es una ficha
							// inicial
							try {
								Image imagenFicha = j.getFichaInicial().getImagen().getScaledInstance(terrenoSize,
										terrenoSize, 0);
								graphics.drawImage(imagenFicha, terrenoX, terrenoY, null);
							} catch (Exception e1) {
								graphics.setColor(tablero.getTerreno(f, c).getColor());
								graphics.fillRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
							}
						}
					} finally {
						graphics.setColor(Color.BLACK);
						graphics.drawRect(terrenoX, terrenoY, terrenoSize, terrenoSize);
					}
				}
			}
		}
		mostrarFichasIniciales(graphics);
	}

	// Metodo el cual itera por las fichas iniciales y las muestra
	private void mostrarFichasIniciales(Graphics2D graphics) {
		mostrarBotonesFichasIniciales();
		ArrayList<FichaInicial> fichas = game.partida.getMazo().getFichasIniciales();
		int i = 0;
		for (FichaInicial f : fichas) {
			mostrarFichaInicial(graphics, f, RONDA_X + 100, RONDA_Y + (i * 50));
			i++;
		}
	}

	private void mostrarBotonesFichasIniciales() {
		Ronda rondaActual = game.partida.getRonda();
		// Boton para agregar jugadores
		JButton btnFicha1;
		JButton btnFicha2;
		JButton btnFicha3;
		JButton btnFicha4;

		btnFicha1 = new JButton("Elegir");
		btnFicha1.setBounds(RONDA_X - 50, RONDA_Y, 100, terrenoSize);
		btnFicha1.setForeground(Color.BLACK);
		btnFicha1.setBackground(Color.ORANGE);
		btnFicha1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnFicha1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Sound.buttonSound.play();
				rondaActual.getJugadorTurno().tomarFichaInicial(game.partida.getMazo().getFichasIniciales().get(0));
				if (rondaActual.siguienteEleccionFicha() == false) {
					game.mostrarInterfazFichasIniciales = false;
					game.mostrarInterfazEleccion = true;
					deboMostrarDomino1 = true;
					deboMostrarDomino2 = true;
					deboMostrarDomino3 = true;
					deboMostrarDomino4 = true;
					removeAll();
				}
				deboMostrarFicha1 = false;
				removeAll();
			}
		});
		if (deboMostrarFicha1)
			add(btnFicha1);

		btnFicha2 = new JButton("Elegir");
		btnFicha2.setBounds(RONDA_X - 50, RONDA_Y + (1 * 50), 100, terrenoSize);
		btnFicha2.setForeground(Color.BLACK);
		btnFicha2.setBackground(Color.ORANGE);
		btnFicha2.setFont(new Font("Dialog", Font.BOLD, 18));
		btnFicha2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondaActual.getJugadorTurno().tomarFichaInicial(game.partida.getMazo().getFichasIniciales().get(1));
				if (rondaActual.siguienteEleccionFicha() == false) {
					game.mostrarInterfazFichasIniciales = false;
					game.mostrarInterfazEleccion = true;
					deboMostrarDomino1 = true;
					deboMostrarDomino2 = true;
					deboMostrarDomino3 = true;
					deboMostrarDomino4 = true;
					removeAll();
				}
				deboMostrarFicha2 = false;
				removeAll();
			}
		});
		if (deboMostrarFicha2)
			add(btnFicha2);

		btnFicha3 = new JButton("Elegir");
		btnFicha3.setBounds(RONDA_X - 50, RONDA_Y + (2 * 50), 100, terrenoSize);
		btnFicha3.setForeground(Color.BLACK);
		btnFicha3.setBackground(Color.ORANGE);
		btnFicha3.setFont(new Font("Dialog", Font.BOLD, 18));
		btnFicha3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondaActual.getJugadorTurno().tomarFichaInicial(game.partida.getMazo().getFichasIniciales().get(2));
				if (rondaActual.siguienteEleccionFicha() == false) {
					game.mostrarInterfazFichasIniciales = false;
					game.mostrarInterfazEleccion = true;
					deboMostrarDomino1 = true;
					deboMostrarDomino2 = true;
					deboMostrarDomino3 = true;
					deboMostrarDomino4 = true;
					removeAll();
				}
				deboMostrarFicha3 = false;
				removeAll();
			}
		});
		if (deboMostrarFicha3)
			add(btnFicha3);

		btnFicha4 = new JButton("Elegir");
		btnFicha4.setBounds(RONDA_X - 50, RONDA_Y + (3 * 50), 100, terrenoSize);
		btnFicha4.setForeground(Color.BLACK);
		btnFicha4.setBackground(Color.ORANGE);
		btnFicha4.setFont(new Font("Dialog", Font.BOLD, 18));
		btnFicha4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rondaActual.getJugadorTurno().tomarFichaInicial(game.partida.getMazo().getFichasIniciales().get(3));
				if (rondaActual.siguienteEleccionFicha() == false) {
					game.mostrarInterfazFichasIniciales = false;
					game.mostrarInterfazEleccion = true;
					deboMostrarDomino1 = true;
					deboMostrarDomino2 = true;
					deboMostrarDomino3 = true;
					deboMostrarDomino4 = true;
					removeAll();
				}
				deboMostrarFicha4 = false;
				removeAll();
			}
		});
		if (deboMostrarFicha4)
			add(btnFicha4);

		repaint();
	}

	// Metodo que se encarga de cargar las fichas iniciales (colores)
	private void mostrarFichaInicial(Graphics2D graphics, FichaInicial f, int x, int y) {
		graphics.setColor(f.getColorFromFicha());
		// graphics.fillRect(x, y, terrenoSize, terrenoSize);
		graphics.fillOval(x, y, terrenoSize, terrenoSize);
	}

	// Metodo el cual a partir de un jugador iterado, setea variables globales
	private void setCurrentPlayer(Jugador player) {
		currentPlayer = player;
		playerX = player.getScreenPosition().x;
		playerY = player.getScreenPosition().y;
		tableroX = playerX;
		tableroY = playerY + 10;
	}

	// Metodo que muestra el nombre del jugador en la parte superior de su posicion
	public void mostrarNombre(Graphics2D graphics) {
		String nombreCompleto = currentPlayer.getNombre();
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Comic Sans", Font.BOLD, 15));
		graphics.drawString(nombreCompleto, playerX, playerY);
	}

	// Metodo el cual itera por los dominos y muestra cada uno en su posicion
	private void mostrarDominos(Graphics2D graphics) {
		mostrarBotonesEleccion();
		Ronda rondaActual = game.partida.getRonda();
		int i = 0;
		for (Domino d : rondaActual.getDominosEnJuego()) {
			mostrarDomino(graphics, d, RONDA_X + 100, RONDA_Y + (i * 50));
			i++;
		}
	}

	// Metodo encargado de mostrar un domino en juego en pantalla
	private void mostrarDomino(Graphics2D graphics, Domino domino, int x, int y) {
		int dominoSize;
		int dX;
		int dY;
		if (game.mostrarInterfazEleccion == true) {
			dominoSize = terrenoSize;
			dX = 1;
			dY = 0;
		} else {
			dominoSize = terrenoSize * 2;
			Posicion diferenciaSegunTerreno1 = domino.getPosicionTerreno2(new Posicion(0, 0));
			dX = diferenciaSegunTerreno1.columna;
			dY = diferenciaSegunTerreno1.fila;
		}

		Image terreno1 = null;
		Image terreno2 = null;
		try {
			terreno1 = domino.getTerreno1().getImagen().getScaledInstance(dominoSize, dominoSize, 0);
			terreno2 = domino.getTerreno2().getImagen().getScaledInstance(dominoSize, dominoSize, 0);
		} catch (Exception e) {
			// no hay problema
		}
		// Seteo estilo de texto para subindice del domino
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Calibri", Font.ITALIC, dominoSize / 2 - 5));

		// Imprimo terreno 1
		graphics.drawImage(terreno1, x, y, null);
		graphics.drawString("1", x + 2, y + (dominoSize - 2));

		// cargo posicion de terreno 2
		x = x + (dX * dominoSize);
		y = y + (dY * dominoSize);

		// Imprimo terreno 2
		graphics.drawImage(terreno2, x, y, null);
		graphics.drawString("2", x + 2, y + (dominoSize)); // cargo subindice
	}

}
