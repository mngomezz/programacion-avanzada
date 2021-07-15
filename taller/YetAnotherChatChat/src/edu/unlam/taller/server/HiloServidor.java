package edu.unlam.taller.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HiloServidor extends Thread {

	private Servidor servidor;
	private ArrayList<Socket> sockets;
	private Socket socket;

	public HiloServidor(Servidor servidor, Socket socket, ArrayList<Socket> sockets) {
		this.servidor = servidor;
		this.socket = socket;
		this.sockets = sockets;
	}

	public void run() {

		DataInputStream entrada;
		DataOutputStream salida;
		String dataRecibida;
		try {
			entrada = new DataInputStream(this.socket.getInputStream());

			while (true) {
				dataRecibida = entrada.readUTF();
				System.out.println("[SERVER] " + dataRecibida);
				for (Socket envio : sockets) {
					salida = new DataOutputStream(envio.getOutputStream());
					salida.writeUTF(dataRecibida);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
