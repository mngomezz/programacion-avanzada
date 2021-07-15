package edu.unlam.taller.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import com.google.gson.Gson;

import edu.unlam.taller.server.Sala;

public class Cliente {

	private static int mensajesEnviados;
	private String fechaConexion;
	private String horaConexion;
	private Sala sala;
	private Socket socket;
	private int puerto;
	private String ip;
	private String usuario;
	private Mensaje primerMensajeEnSala;
	DataInputStream entrada;
	DataOutputStream salida;

	public Cliente(int puerto, String ip, String usuario) {
		this.usuario = usuario;
		this.puerto = puerto;
		this.ip = ip;

		try {
			socket = new Socket(this.ip, this.puerto);
			salida = new DataOutputStream(socket.getOutputStream());
			entrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean conectarseASala(Sala sala) {
		if (sala.conectarse(this)) {
			Date date = new Date();
			String y = Integer.toString(date.getYear() + 1900);
			String m = Integer.toString(date.getMonth());
			String d = Integer.toString(date.getDate());
			fechaConexion = y + (m.length() == 1 ? "0" + m : m) + (d.length() == 1 ? "0" + d : d);
			horaConexion = date.getHours() + ":" + date.getMinutes();
			this.sala = sala;
			return true;
		}
		return false;
	}

	public void enviarMensaje(String mensaje) {
		Gson gson = new Gson();
		Mensaje msj = new Mensaje(++mensajesEnviados, usuario, sala.getNombre(), mensaje);
		String data = gson.toJson(msj);
		System.out.println(data);
		System.out.println(msj);
		try {
			salida.writeUTF(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void inicializarHiloCliente(JChatCliente ventana) {
		new HiloCliente(this, this.entrada, ventana).start();
	}

	public Sala getSala() {
		return sala;
	}

	public String getHoraConexion() {
		return horaConexion;
	}

	public String getFechaConexion() {
		return fechaConexion;
	}

	public String getUsuario() {
		return usuario;
	}

	public Mensaje getPrimerMensaje() {
		return primerMensajeEnSala;
	}
}