package edu.unlam.taller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import edu.unlam.taller.client.Cliente;

public class Servidor {

	private int puerto;
	private ServerSocket server;
	private ArrayList<Socket> sockets;
	private ArrayList<Sala> salas;
	private ArrayList<Cliente> clientes;

	public Servidor(int puerto) {
		this.clientes = new ArrayList<Cliente>();
		this.salas = new ArrayList<Sala>();
		this.puerto = puerto;
		try {
			server = new ServerSocket(this.puerto);
			sockets = new ArrayList<Socket>();

			System.out.println("Server inicializando...");

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Server inicializado!");
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	public void setSockets(ArrayList<Socket> sockets) {
		this.sockets = sockets;
	}

	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void ejecutar() {
		Socket socket;
		try {
			while (true) {
				socket = server.accept();
				sockets.add(socket);
				new HiloServidor(this, socket, sockets).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		new Servidor(50000).ejecutar();
//	}

}
