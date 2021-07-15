package edu.unlam.taller.client;

import java.io.DataInputStream;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HiloCliente extends Thread {

	private Cliente cliente;
	private DataInputStream entrada;
	private JChatCliente ventana;

	public HiloCliente(Cliente cliente, DataInputStream entrada, JChatCliente ventana) {
		this.cliente = cliente;
		this.entrada = entrada;
		this.ventana = ventana;
	}

	public void run() {
		Gson gson = new Gson();
		String dataRecibida;
		Mensaje mensaje;
		while (true) {
			try {
				dataRecibida = entrada.readUTF();
//				System.out.println("[CLIENT] " + dataRecibida);
				mensaje = gson.fromJson(dataRecibida, new TypeToken<Mensaje>() {
				}.getType());

				cliente.getSala().cargarMensaje(mensaje);
				ventana.escribirMensajeEnTextArea(mensaje.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
