package eDoe.controllers;

import java.util.ArrayList;
import java.util.Map;

import eDoe.models.Item;

public class MatchMaker {

	public String matchMaker(Map<Item, String> todosOsItens, Item itemDeMatch) {
		ArrayList<Item> arrayItensMatch = selecionaItensMatch(todosOsItens, itemDeMatch);
		for (Item i: arrayItensMatch) {
			calculaMatchDoItem(i, itemDeMatch);
		}
		return "";
	}
	
	
	private ArrayList<Item> selecionaItensMatch(Map<Item, String> todosOsItens, Item itemDeMatch) {
		ArrayList<Item> arrayItensMatch = new ArrayList<>();
		String descricaoParaMatch = itemDeMatch.getDescricao();
		for (Item i : todosOsItens.keySet()) {
			if (i.getDescricao().equals(descricaoParaMatch))
				arrayItensMatch.add(i);
		}
		return arrayItensMatch;
	}

	private int calculaMatchDoItem(Item itemAnalisado, Item itemDeMatch) {
		int pontos = 20;
		for (int i = 0; i < itemDeMatch.getTags().size(); i++) {
			if(itemDeMatch.getTags().get(i).equals(itemAnalisado.getTags().get(i)))
				pontos += 10;
			else if (itemDeMatch.getTags().contains(itemAnalisado.getTags().get(i)))
				pontos += 5;
		}
		return pontos;
	}


}
