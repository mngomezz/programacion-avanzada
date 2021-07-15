package dominio;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import excepciones.NoHayMasDominos;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
// Testeado por Mazo

public class Mazo {

	private List<Domino> dominos;
	private ArrayList<FichaInicial> fichasIniciasles;

	public Mazo() {

		dominos = new ArrayList<Domino>();
		fichasIniciasles = new ArrayList<FichaInicial>();

		Reader reader = null;

		reader = mapearFichasIniciales(reader);
		reader = mapearDominos(reader);

		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Reader mapearDominos(Reader reader) {

		Gson gson = new Gson();

		try {
			reader = Files.newBufferedReader(Paths.get("archivos/dominos.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		dominos = gson.fromJson(reader, new TypeToken<List<Domino>>() {
		}.getType());

		return reader;
	}

	private Reader mapearFichasIniciales(Reader reader) {
		Gson gson = new Gson();
		try {
			reader = Files.newBufferedReader(Paths.get("archivos/fichasIniciales.json"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		fichasIniciasles = gson.fromJson(reader, new TypeToken<List<FichaInicial>>() {
		}.getType());

		return reader;
	}

	public ArrayList<Domino> obtenerDominos() throws NoHayMasDominos {

		ArrayList<Domino> dominosEnJuego = new ArrayList<Domino>();
		int limite = dominos.size() - 1, randomIndex;
		Random rand = new Random();

		if (dominos.isEmpty())
			throw new NoHayMasDominos();

		while (dominosEnJuego.size() < 4) {

			if (limite == 0)
				randomIndex = 0;
			else
				randomIndex = rand.nextInt(limite);

			dominosEnJuego.add(dominos.get(randomIndex));
			dominos.remove(randomIndex);

			limite--;
		}

		dominosEnJuego.sort(new Comparator<Domino>() {
			@Override
			public int compare(Domino o1, Domino o2) {
				return Integer.compare(o1.getNumero(), o2.getNumero());
			}
		});
		return dominosEnJuego;
	}

	public ArrayList<FichaInicial> getFichasIniciales() {
		return fichasIniciasles;
	}

	public List<Domino> getDominos() {
		return dominos;
	}

}