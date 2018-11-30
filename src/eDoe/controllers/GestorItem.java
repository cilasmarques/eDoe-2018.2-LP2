package eDoe.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import eDoe.models.Item;
import eDoe.models.Usuario;
import eDoe.utils.DescricaoComparator;
import eDoe.utils.Ferramentas;
import eDoe.utils.IdComparator;
import eDoe.utils.QuantidadeComparator;
import eDoe.utils.Validador;

public class GestorItem {

	private Map<String, Integer> descritores = new LinkedHashMap<>();
	private QuantidadeComparator quantComp = new QuantidadeComparator();
	private DescricaoComparator descrComp = new DescricaoComparator();
	private IdComparator idComp = new IdComparator();

	public void adicionarDescritor(String descricao) {
		Validador.validadorAdicionaDescritor(descricao, this.descritores);
		this.descritores.put(descricao.toLowerCase().trim(), 0);
	}

	public int adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags) {
		Validador.validadorAdicionaItem(descricao, quantidade);
		String descrMin = descricao.toLowerCase().trim();
		this.descritores.put(descrMin, quantidade);
		return doador.adicionaItemParaDoacao(descricao, quantidade, tags, false);
	}

	public String exibeItem(Usuario user, int idItem) {
		Validador.validadorExibeItem(user, idItem);
		return user.exibeItem(idItem);
	}

	public String atualizaItemParaDoacao(Usuario user, int idItem, int quantidade, String tags) {
		Validador.verificadorAtualizaItemParaDocao(user, idItem, quantidade);
		String saida = user.atualizaItemParaDoacao(idItem, quantidade, tags);
		atualizaDescritores(user, idItem, "atualizaItem");
		return saida;
	}

	public void removeItemParaDoacao(Usuario user, int idItem) {
		Validador.verificadorRemoveItem(user, idItem);
		atualizaDescritores(user, idItem, "removeItem");
		user.removeItemParaDoacao(idItem);
	}

	public String listaDescritorDeItensParaDoacao() {
		return Ferramentas.arrayToString(makeListaDescritores());
	}

	public String listaTodosOsItensExistentes(Map<String, Usuario> todosUsuarios) {
		Map<Item, String> todosOsItens = getAllItens(todosUsuarios);
		ArrayList<Item> arrayItensRequeridos = makeArrayItens(todosOsItens);
		Collections.sort(arrayItensRequeridos, quantComp);
		return listaItens(todosOsItens, arrayItensRequeridos);
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao, Map<String, Usuario> todosUsuarios) {
		Map<Item, String> itensComDescricao = new HashMap<>();
		for (Item i : getAllItens(todosUsuarios).keySet()) {
			if (i.toString().contains(descricao))
				itensComDescricao.put(i, i.toString());
		}
		ArrayList<Item> arrayItensRequeridos = makeArrayItens(itensComDescricao);
		Collections.sort(arrayItensRequeridos, descrComp);
		return listaItens(itensComDescricao, arrayItensRequeridos);
	}

	public int adicionaItemNecessario(Usuario receptor, String descricao, int quantidade, String tags) {
		Validador.validadorAdicionaItem(descricao, quantidade);
		String descrMin = descricao.toLowerCase().trim();
		// this.descritores.put(descrMin, quantidade);
		return receptor.adicionaItemNecessario(descrMin, quantidade, tags, true);
	}

	public String atualizaNecessario(Usuario user, int idItem, int novaQuantidade, String novasTags) {
		Validador.verificadorAtualizaItemNecessario(user, idItem);
		String saida = user.atualizaItemNecessario(idItem, novaQuantidade, novasTags);
		atualizaDescritores(user, idItem, "atualizaItem");
		return saida;
	}

	public String listaItensNecessarios(Map<String, Usuario> todosUsuarios) {
		Map<Item, String> todosOsItens = getAllItens(todosUsuarios);
		ArrayList<Item> arrayItensRequeridos = makeArrayItensNecessario(todosOsItens);
		Collections.sort(arrayItensRequeridos, idComp);
		return listaItens(todosOsItens, arrayItensRequeridos);
	}

	public void removeItemNecessario(Usuario user, int idItem) {
		Validador.verificadorRemoveItem(user, idItem);
		// atualizaDescritores(user, idItem, "removeItem");
		user.removeItemNecessario(idItem);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private String listaItens(Map<?, String> listaItens, ArrayList<Item> arrayItensRequeridos) {
		String saida = listaItens.get(arrayItensRequeridos.get(0));
		for (int i = 1; i < arrayItensRequeridos.size(); i++) {
			saida += " | " + listaItens.get(arrayItensRequeridos.get(i));
		}
		return saida;
	}

	private Map<Item, String> getAllItens(Map<String, Usuario> todosUsuarios) {
		Map<Item, String> listaAllItens = new HashMap<>();
		for (Usuario u : todosUsuarios.values()) {
			if (!u.listaItens().isEmpty() && u != null)
				listaAllItens.putAll(u.listaItens());
		}
		return listaAllItens;
	}

	private ArrayList<Item> makeArrayItens(Map<Item, String> listaItens) {
		ArrayList<Item> arrayItens = new ArrayList<>();
		for (Item i : listaItens.keySet()) {
			arrayItens.add(i);
		}
		return arrayItens;
	}

	private ArrayList<Item> makeArrayItensNecessario(Map<Item, String> listaItens) {
		ArrayList<Item> arrayItens = new ArrayList<>();
		for (Item i : listaItens.keySet()) {
			if (i.ehNecessario())
				arrayItens.add(i);
		}
		return arrayItens;
	}

	private void atualizaDescritores(Usuario u, int idItem, String string) {
		String descrMin = u.getItens().get(idItem).getDescricao();
		int quantItem = u.getItens().get(idItem).getQuantidade();

		if (string.equals("atualizaItem"))
			this.descritores.put(descrMin, quantItem);

		else if (string.equals("removeItem")) {
			int quantItensTotal = this.descritores.get(descrMin) - quantItem;
			this.descritores.put(descrMin, quantItensTotal);
		}
	}

	private ArrayList<String> makeListaDescritores() {
		ArrayList<String> array = new ArrayList<>();
		for (String key : this.descritores.keySet()) {
			array.add(key);
		}
		Collections.sort(array);
		return arrayPutInfosDescritor(array);
	}

	private ArrayList<String> arrayPutInfosDescritor(ArrayList<String> arrayDescritores) {
		ArrayList<String> arrayDeInfos = new ArrayList<>();
		for (String key : arrayDescritores) {
			int quantidade = this.descritores.get(key);
			arrayDeInfos.add(quantidade + " - " + key);
		}
		return arrayDeInfos;
	}

}