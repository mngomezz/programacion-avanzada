package edu.unlam.taller.client;

import java.util.Date;

public class Mensaje {
	public int id;
	String sala; // nombre de la sala
	String origen; // nombre del Cliente
	String fecha; // '20210630'
	String hora;
	String texto;

	@SuppressWarnings("deprecation")
	public Mensaje(int idmsj, String origin, String room, String text) {
		id = idmsj;
		texto = text;
		origen = origin;
		sala = room;
		Date f = new Date(); // guardo hora de creacion del mensaje.
		fecha = Integer.toString(f.getYear()) + Integer.toString(f.getMonth()) + Integer.toString(f.getDay());
		hora = Integer.toString(f.getHours()) + ":" + Integer.toString(f.getMinutes());
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "[" + origen + " - " + hora + "] " + texto + "\n";
	}
}
