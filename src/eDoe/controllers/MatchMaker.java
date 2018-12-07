package eDoe.controllers;

import java.util.ArrayList;

import eDoe.models.Item;

public class MatchMaker {

	public ArrayList<Item> getItensMatchMaker(ArrayList<Item> todosAsDoacoes, Item itemDeMatch) {
		ArrayList<Item> arrayItensMatch = new ArrayList<>();
		if (selecionaItensParaMatch(todosAsDoacoes, itemDeMatch).isEmpty())
			return arrayItensMatch;
		else {
			for (Item i : selecionaItensParaMatch(todosAsDoacoes, itemDeMatch)) {
				putPontuacaoMatchDoItem(i, itemDeMatch);
				if (i.getPontuacao() > 0)
					arrayItensMatch.add(i);
			}
		}
		return arrayItensMatch;
	}

	/**
	 * Metodo que filtra os itens de match pela sua descricao, selecionando apenas
	 * os itens que contem a mesma descricao do item de match
	 * 
	 * @param itensParaAnalise todos os itens cadastrados no sistema
	 * @param itemDeMatch      item o qual quer se dar match
	 * @return ArrayList com os itens que tem a mesma descricao do item de match
	 */
	private ArrayList<Item> selecionaItensParaMatch(ArrayList<Item> itensParaAnalise, Item itemDeMatch) {
		ArrayList<Item> arrayItensMatch = new ArrayList<>();
		String descricaoParaMatch = itemDeMatch.getDescricao();
		for (Item i : itensParaAnalise) {
			i.setPontuacao(0);
			if (i.getDescricao().equals(descricaoParaMatch) && (i.getId() != itemDeMatch.getId()))
				arrayItensMatch.add(i);
		}
		return arrayItensMatch;
	}

	private void putPontuacaoMatchDoItem(Item itemAnalisado, Item itemDeMatch) {
		int pontos = 20;
		for (int i = 0; i < itemDeMatch.getTags().size(); i++) {
			if (itemAnalisado.getTags().size() > i) {
				if (itemDeMatch.getTags().get(i).equals(itemAnalisado.getTags().get(i)))
					pontos += 10;
				else if (itemDeMatch.getTags().contains(itemAnalisado.getTags().get(i)))
					pontos += 5;
			}
		}
		itemAnalisado.setPontuacao(pontos);
	}

}
