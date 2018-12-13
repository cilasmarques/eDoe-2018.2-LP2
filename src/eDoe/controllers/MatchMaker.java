package eDoe.controllers;

import java.util.ArrayList;

import eDoe.models.Item;

/**
 * Classe responsavel por fazer os matches entre os itens
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 * 
 */
public class MatchMaker {

	/**
	 * Método que pega os itens que tiveram a pontuação de match, com o item
	 * requerido, maior que 0
	 * 
	 * @param todosAsDoacoes Todos os itens para doação cadastrados no sistema
	 * @param itemDeMatch    Item necessário requerido para match
	 * @return ArrayList com todos os itens para doação que tiveram match com o item
	 *         requerido
	 */
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
	 * Método que filtra os itens de match pela sua descricao, selecionando apenas
	 * os itens que contém a mesma descricao do item de match
	 * 
	 * @param itensParaAnalise todos os itens cadastrados no sistema
	 * @param itemDeMatch      Item necessário requerido para match
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

	/**
	 * Método que coloca a pontuação de match que o item analisado obteve. Começando
	 * com 20 pontos, pois o item analisado já foi selecionado por sua descrição. É
	 * passado como parâmetro o item com mais tags, para não
	 * 
	 * @param itemAnalisado   Item com a mesma descrição do item de match
	 * @param itemDeMatch     Item necessário requerido para match
	 * @param itemComMaisTags Item que contem mais tags
	 */
	private void putPontuacaoMatchDoItem(Item itemAnalisado, Item itemDeMatch) {
		int pontos = 20;
		if (itemDeMatch.getTags().size() >= itemAnalisado.getTags().size()) {
			pontos += calculaPontuacao(itemAnalisado, itemDeMatch);
		} else if (itemDeMatch.getTags().size() < itemAnalisado.getTags().size()) {
			pontos += calculaPontuacao(itemDeMatch, itemAnalisado);
		}
		itemAnalisado.setPontuacao(pontos);
	}

	/**
	 * Método que calcula a pontuação de match baseado nas tags dos dois itens
	 * 
	 * @param itemComMenosTags Item que contem menor numero de tags para comparação
	 * @param itemComMaisTags  Item que contem maior numero de tags para comparação
	 * @return int com a quantidade de pontos que o item para doação conseguiu
	 *         atingir
	 */
	private int calculaPontuacao(Item itemComMenosTags, Item itemComMaisTags) {
		int pontos = 0;
		int i = 0;
		while (itemComMaisTags.getTags().size() > i) {
			if (itemComMenosTags.getTags().size() > i) {
				if (itemComMaisTags.getTags().get(i).equals(itemComMenosTags.getTags().get(i)))
					pontos += 10;
				else if (itemComMaisTags.getTags().contains(itemComMenosTags.getTags().get(i)))
					pontos += 5;
			} else if (itemComMenosTags.getTags().size() < i) {
				pontos += 5;
			}
			i++;
		}
		return pontos;
	}

}
