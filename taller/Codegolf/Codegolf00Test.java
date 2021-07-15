package codegolf;

import org.junit.Test;

public class Codegolf00Test {

	@Test
	public void testNumeracionDeTomos() {
		Codegolf00 cg = new Codegolf00();
		String[] d = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		System.out.println(cg.numeracionDeTomos(99, d));
		System.out.println(cg.numeracionDeTomos(198, d));
		System.out.println(cg.numeracionDeTomos(297, d));
		System.out.println(cg.numeracionDeTomos(396, d));
		System.out.println(cg.numeracionDeTomos(495, d));
		System.out.println(cg.numeracionDeTomos(594, d));
		System.out.println(cg.numeracionDeTomos(693, d));
		System.out.println(cg.numeracionDeTomos(792, d));
		System.out.println(cg.numeracionDeTomos(891, d));
		System.out.println(cg.numeracionDeTomos(990, d));
	}

}
