package oia1;

import java.util.Comparator;

public class ReinaComparator<T> implements Comparator<Reina> {

	@Override
	public int compare(Reina o1, Reina o2) {
		return o1.getNumber() - o2.getNumber();
	}

}
