package eDoe.comparators;

import java.util.Comparator;

public class DoacaoComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		String data_o1 = o1.substring(0, 10);
		String data_o2 = o2.substring(0, 10);
		if (data_o1.equals(data_o2))
			return o1.compareTo(o2);
		return data_o2.compareTo(data_o1);
	}

}
