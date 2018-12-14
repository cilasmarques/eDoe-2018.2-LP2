package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

/**
 * Classe responsável por comparar dois itens do sistema eDoe pelo seu código de
 * identidicação (ID)
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 */
public class IdComparator implements Comparator<Item_eDoe> {

	/**
	 * Método que compara dois itens do sistema eDoe pelo seu código de
	 * identidicação (ID), comparando-os do maior para o menor ID
	 */
	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		if (o2.getId() < o1.getId())
			return 1;
		return -1;
	}

}
