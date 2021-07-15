package edu.unlam.taller.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import edu.unlam.taller.client.Cliente;
import edu.unlam.taller.client.JChatCliente;

public class Lobby extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* LOGICA */
	Servidor servidor;

	/* VISUAL */
	private JPanel contentPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mnOpciones;
	private JMenuItem itemConectar;
	private JTextField textField;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Lobby frame = new Lobby();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Lobby() {
		servidor = new Servidor(50000);

		setTitle("Lobby");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 950, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 231, 324, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setBounds(10, 39, 414, 600);

		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		contentPane.add(textArea);

		menuBar = new JMenuBar();
		menuBar.setBounds(10, 0, 71, 31);
		contentPane.add(menuBar);

		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);

		itemConectar = new JMenuItem("Conectar");
		itemConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUnNuevoCliente();
				// TODO chequear caso en que cancele en vez de ingresar el nombre.
//				itemConectar.setEnabled(false);
			}
		});
		mnOpciones.add(itemConectar);

		setLocationRelativeTo(null);
		setVisible(true);
		servidor.ejecutar();
	}

	public void crearUnNuevoCliente() {
		String usuario = JOptionPane.showInputDialog(this, "Usuario", "Ingrese usuario",
				JOptionPane.INFORMATION_MESSAGE);

		if (usuario == null)
			return;

		Cliente nuevoCliente = new Cliente(50000, "localhost", usuario);

		if (this.servidor.getClientes().size() == 0) {
			this.servidor.getSalas().add(new Sala("sala demo"));
		}
		nuevoCliente.conectarseASala(this.servidor.getSalas().get(0));
		this.servidor.getClientes().add(nuevoCliente);
		nuevoCliente.inicializarHiloCliente(new JChatCliente(nuevoCliente));
		// AL CONECTARSE A UNA SALA
//		Date f = new Date();
//		String year = Integer.toString(f.getYear() + 1900);
//		String mes = Integer.toString(f.getMonth() + 1);
//		String dia = Integer.toString(f.getDate());
//		mes = (mes.length() == 1) ? "0" + mes : mes;
//		dia = (dia.length() == 1) ? "0" + dia : dia;
//
//		nuevoCliente.fechaConexion = year + mes + dia;
//		nuevoCliente.horaConexion = Integer.toString(f.getHours()) + ":" + Integer.toString(f.getMinutes());
//		System.out.println("la fecha de conexion es: " + fechaConexion);
//		System.out.println("la hora de conexion es: " + horaConexion);

	}
}
