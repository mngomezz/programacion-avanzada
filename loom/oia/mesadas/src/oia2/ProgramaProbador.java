package oia2;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ProgramaProbador {
	static int MAX_MESADAS = 200000;
	static int MAX_DIMENSION = 1000000;

	public static void main(String[] args) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("mesadas.in"));
			pw.println(MAX_MESADAS);
			for (int i = 1; i <= MAX_MESADAS; i++) {
				int random1 = (int) (Math.random() * MAX_DIMENSION);
				int random2 = (int) (Math.random() * MAX_DIMENSION);
				pw.println(random1 + " " + random2);
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
