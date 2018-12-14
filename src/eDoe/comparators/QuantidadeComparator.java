package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

/**
 * Classe responsável por comparar dois itens do sistema eDoe pela sua
 * quantidade
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 */
public class QuantidadeComparator implements Comparator<Item_eDoe> {

	/**
	 * Método que compara dois itens do sistema eDoe pela sua quantidade,
	 * comparando-os da maior para a menor quantidade. Caso as quantidades sejam
	 * iguais os itens são comparados pela sua descrição, em ordem alfabética
	 */
	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		if (o1.getQuantidade() == o2.getQuantidade()) {
			return o1.getDescricao().compareTo(o2.getDescricao());
		}
		if (o1.getQuantidade() > o2.getQuantidade())
			return -1;
		return 1;
	}

}
