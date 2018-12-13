package eDoe.utils;

import java.util.ArrayList;

public class Ferramentas {

	/**
	 * Código de identificação usado para definir o código de cada item 
	 * (é incrementado cada vez que um item novo é adicionado no sistema)
	 */
	public static int idUnico = 10000000;

	/**
	 * Método responsável por organizar uma saida String que contem as informações
	 * requeridas
	 * 
	 * @param array ArrayList com as informações requeridas para saida
	 * @return String com uma saida formatada adequadamente para o sistema
	 */
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
