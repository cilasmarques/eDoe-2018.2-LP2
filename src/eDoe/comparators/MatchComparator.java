package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

/**
 * Classe responsável por comparar dois itens do sistema eDoe pela sua pontuação
 * de match
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 */
public class MatchComparator implements Comparator<Item_eDoe> {

	/**
	 * Método que compara dois itens do sistema eDoe pela sua pontuação de match,
	 * comparando-os do maior para o menor match. Caso as pontuações sejam iguais os
	 * itens são comparados pelo seu código de identificação (ID), do maior para o
	 * menor
	 */
	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		if (o1.getPontuacao() == o2.getPontuacao()) {
			if (o1.getId() > o2.getId())
				return 1;
			return -1;
		}
		if (o1.getPontuacao() > o2.getPontuacao())
			return -1;
		return 1;
	}

}
