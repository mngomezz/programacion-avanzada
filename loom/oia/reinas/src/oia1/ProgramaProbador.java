package oia1;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ProgramaProbador {
	static int MAX_REINAS = 50000;
	static int MAX_DIMENSION = 10000;

	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("reinas.in"));
			int dim = (int) Math.floor(Math.sqrt(MAX_REINAS));
			pw.println(MAX_DIMENSION + " " + MAX_REINAS);
			for (int i = 1; i <= dim; i++) {
				for (int j = 1; j <= dim; j++) {
					pw.println(i + " " + j);
				}
			}
			int reinasRestantes = MAX_REINAS - (dim * dim);
			for (int j = 1; j <= reinasRestantes; j++) {
				if (j > dim)
					pw.println((dim + 2) + " " + (j % dim + 1));
				else
					pw.println((dim + 1) + " " + (j % dim + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
		return;
	}

}
