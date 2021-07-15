package oia3;

public class ErroresDeTipeoApp {

	public static void main(String[] args) {
		long start = System.nanoTime();
		System.out.println(procesador("Hola que tal?", "talque aloH ?"));
		long end = System.nanoTime();
		System.out.println("nanosecond: " + (end - start));
		System.out.println("millisecond: " + ((end - start) / Math.pow(10, 6)));
		System.out.println("second: " + ((end - start) / Math.pow(10, 9)));
	}

	public static int procesador(String texto, String prueba) {
		char[] t = texto.toCharArray();
		char[] p = prueba.toCharArray();
		int cantMovs = 0;
		for (int i = 0; i < t.length; i++) {
			int j = i;
			while (t[i] != p[j]) {
				j++;
			}
			cantMovs += (j - i);
			while (j > i) {
				char aux = p[j - 1];
				p[j - 1] = p[j];
				p[j] = aux;
				j--;
			}
		}
		return cantMovs;
	}

}