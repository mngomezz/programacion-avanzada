package edu.unlam.taller.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import edu.unlam.taller.client.Cliente;
import edu.unlam.taller.client.Mensaje;

public class Sala {
	String nombre;
	ArrayList<Cliente> clientes;
	ArrayList<Mensaje> historial;

	public Sala(String name) {
		clientes = new ArrayList<Cliente>();
		historial = new ArrayList<Mensaje>();
		nombre = name;
	}

	public boolean conectarse(Cliente c) {
		if (clientes.contains(c)) {
			return false;
		}
		clientes.add(c);
		return true;
	}

	public boolean cargarMensaje(Mensaje msj) {
		historial.add(msj);
		return true;
	}

	public void descargarHistorial(Mensaje primerMensaje) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("historial.txt"));
			int indice = -1;
			int size = historial.size();
			for (int i = 0; i < size; i++) {
				if (primerMensaje == historial.get(i)) {
					indice = i; // encuentro indice del primer mensaje que envio el usuario
					i = size; // termino loop
				}
			}

			if (indice < 0)
				return; // si no se encontro el primer mensaje, no retorno nada

			ArrayList<Mensaje> historialPersonal = (ArrayList<Mensaje>) historial.subList(indice, size);

			for (Mensaje msj : historialPersonal) {
				pw.write(msj.toString());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Mensaje> getHistorial() {
		return historial;
	}

}
