package edu.unlam.taller.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class JChatCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	private JPanel contentPane;
	private JButton btnEnviar;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mnOpciones;
	private JMenuItem itemHistorial;
	private JTextField textField;
	private JScrollPane scrollPane;

	public JChatCliente(Cliente cliente) {
		this.cliente = cliente;
		setTitle("Chat - " + cliente.getUsuario());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 231, 324, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				enviarMensajeAServidor();
				borrarContenidoTextField();
			}
		});
		btnEnviar.setBounds(335, 230, 89, 23);
		contentPane.add(btnEnviar);

		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setBounds(10, 39, 414, 181);

		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		contentPane.add(textArea);

		menuBar = new JMenuBar();
		menuBar.setBounds(10, 0, 71, 31);
		contentPane.add(menuBar);

		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);

		itemHistorial = new JMenuItem("Descargar historial");
		itemHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.getSala().descargarHistorial(cliente.getPrimerMensaje());
			}
		});
		mnOpciones.add(itemHistorial);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void escribirMensajeEnTextArea(String mensaje) {
		textArea.append(mensaje);
	}

	public void enviarMensajeAServidor() {
		String texto = textField.getText();
		cliente.enviarMensaje(texto);
	}

	public void borrarContenidoTextField() {
		textField.setText("");
	}

}
