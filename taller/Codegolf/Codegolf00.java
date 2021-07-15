package codegolf;

public class Codegolf00 {
	String numeracionDeTomos(int t, String[] d) {
		int n = --t % 99 + 1;
		return d[t / 99] + (n > 9 ? "-" : "-0") + n;
	}
}
