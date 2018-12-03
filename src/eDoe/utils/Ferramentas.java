package eDoe.utils;

import java.util.ArrayList;

public class Ferramentas {

	public static int idUnico = 10000000;
	
	public static final String ln = System.lineSeparator();

	public static String arrayToString(ArrayList<?> array) {
		if (array.isEmpty())
			return "";
		String saida = array.get(0).toString();
		for (int i = 1; i < array.size(); i++) {
			saida += " | " + array.get(i);
		}
		return saida;
	}

}
