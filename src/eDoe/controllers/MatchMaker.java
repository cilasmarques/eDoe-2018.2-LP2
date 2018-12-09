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
				if (itemDeMatch.getTags().size() >= i.getTags().size())
					pontuacaoMatchDoItem(i, itemDeMatch, itemDeMatch);
				else if (itemDeMatch.getTags().size() < i.getTags().size()) {
					pontuacaoMatchDoItem(i, itemDeMatch, i);
				}
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

//jaqueta de couro" quantidade=3 tags="outfit,couro de bode"

	private void pontuacaoMatchDoItem(Item itemAnalisado, Item itemDeMatch, Item itemComMaisTags) {
		int pontos = 20;
		if (itemComMaisTags.equals(itemDeMatch)) {
			pontos += calculaPontuacao(itemAnalisado, itemDeMatch);
		} else if (itemComMaisTags.equals(itemAnalisado)) {
			pontos += calculaPontuacao(itemDeMatch, itemAnalisado);
		}
		itemAnalisado.setPontuacao(pontos);
	}

	private int calculaPontuacao(Item itemComMenosTags, Item itemComMaisTags) {
		int pontos = 0;
		int i = 0;
		while (itemComMaisTags.getTags().size() > i) {
			if (itemComMenosTags.getTags().size() > i) {
				if (itemComMaisTags.getTags().get(i).equals(itemComMenosTags.getTags().get(i)))
					pontos += 10;
				else if (itemComMaisTags.getTags().contains(itemComMenosTags.getTags().get(i)))
					pontos += 5;
			} else if (itemComMenosTags.getTags().size() < i){
				pontos += 5;
			}
			i++;
		}
		return pontos;
	}
	
}
