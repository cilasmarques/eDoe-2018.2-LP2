package eDoe.comparators;

import java.util.Comparator;

/**
 * Classe responsável por comparar duas doações registradas no sistema eDoe
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 *
 */
public class DoacaoComparator implements Comparator<String> {

	/**
	 * Método que compara duas doações registradas no sistema eDoe pela sua data de
	 * transação, comparando-as da mais antiga para a mais nova. Caso as datas sejam
	 * iguais, compara-se pela ordem alfabética das descrições dos itens doados
	 */
	@Override
	public int compare(String o1, String o2) {
		String data_o1 = o1.substring(0, 10);
		String data_o2 = o2.substring(0, 10);
		if (data_o1.equals(data_o2))
			return o1.compareTo(o2);
		return data_o2.compareTo(data_o1);
	}

}
