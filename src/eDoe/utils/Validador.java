package eDoe.utils;

public class Validador {

	public static final String ln = System.lineSeparator();
	
	public static void validador(String saida, String parametro) {
		if (parametro == null || parametro.trim().equals(""))
			throw new IllegalArgumentException(saida);
	}
	
}
