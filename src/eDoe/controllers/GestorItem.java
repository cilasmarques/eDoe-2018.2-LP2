package eDoe.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import eDoe.comparators.DescricaoComparator;
import eDoe.comparators.IdComparator;
import eDoe.comparators.MatchComparator;
import eDoe.comparators.QuantidadeComparator;
import eDoe.models.Item;
import eDoe.models.Usuario;
import eDoe.utils.Ferramentas;
import eDoe.utils.Validador;

public class GestorItem {

	private Map<String, Integer> descritores;
	private MatchMaker mm;

	public GestorItem() {
		this.descritores = new LinkedHashMap<>();
		this.mm = new MatchMaker();
	}

	public void adicionarDescritor(String descricao) {
		Validador.validadorAdicionaDescritor(descricao, this.descritores);
		this.descritores.put(descricao.toLowerCase().trim(), 0);
	}

	public int adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags, int idItem) {
		Validador.validadorAdicionaItem(descricao, quantidade);
		String descrMin = descricao.toLowerCase().trim();
		this.descritores.put(descrMin, quantidade);
		return doador.adicionaItemParaDoacao(descrMin, quantidade, tags, false, idItem);
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
		ArrayList<Item> arrayItensRequeridos = filtraItens(getAllItens(todosUsuarios), "todos");
		Collections.sort(arrayItensRequeridos, new QuantidadeComparator());
		return makeListaItens(arrayItensRequeridos, "fichaTecnica");
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao, Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> arrayItensRequeridos = new ArrayList<>();
		for (Item i : getAllItens(todosUsuarios)) {
			if (i.toString().contains(descricao))
				arrayItensRequeridos.add(i);
		}
		Collections.sort(arrayItensRequeridos, new DescricaoComparator());
		return makeListaItens(arrayItensRequeridos, "toString");
	}

	public int adicionaItemNecessario(Usuario receptor, String descricao, int quantidade, String tags, int idItem) {
		Validador.validadorAdicionaItem(descricao, quantidade);
		String descrMin = descricao.toLowerCase().trim();
		return receptor.adicionaItemNecessario(descrMin, quantidade, tags, true, idItem);
	}

	public String atualizaNecessario(Usuario user, int idItem, int novaQuantidade, String novasTags) {
		Validador.verificadorAtualizaItemNecessario(user, idItem);
		String saida = user.atualizaItemNecessario(idItem, novaQuantidade, novasTags);
		atualizaDescritores(user, idItem, "atualizaItem");
		return saida;
	}

	public String listaItensNecessarios(Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> arrayItensRequeridos = filtraItens(getAllItens(todosUsuarios), "necessarios");
		Collections.sort(arrayItensRequeridos, new IdComparator());
		return makeListaItens(arrayItensRequeridos, "fichaTecnica");
	}

	public void removeItemNecessario(Usuario user, int idItem) {
		Validador.verificadorRemoveItem(user, idItem);
		user.removeItemNecessario(idItem);
	}

	public String match(Usuario user, int idItemNecessario, Map<String, Usuario> todosUsuarios) {
		Validador.verificadorRemoveItem(user, idItemNecessario);
		Item itemDeMatch = user.getItemPorId(idItemNecessario);
		ArrayList<Item> todasAsDoacoes = filtraItens(getAllItens(todosUsuarios), "doacoes");
		ArrayList<Item> arrayItensRequeridos = mm.getItensMatchMaker(todasAsDoacoes, itemDeMatch);
		Collections.sort(arrayItensRequeridos, new MatchComparator());
		return makeListaItens(arrayItensRequeridos, "fichaTecnica");
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private String makeListaItens(ArrayList<Item> arrayItensRequeridos, String tipoDeSaida) {
		String saida = "";
		if (arrayItensRequeridos.isEmpty())
			return saida;
		else if (tipoDeSaida.equals("toString"))
			saida = Ferramentas.arrayToString(arrayItensRequeridos);
		else if (tipoDeSaida.equals("fichaTecnica")) {
			saida = arrayItensRequeridos.get(0).getFichaTecnica();
			for (int i = 1; i < arrayItensRequeridos.size(); i++) {
				saida += " | " + arrayItensRequeridos.get(i).getFichaTecnica();
			}
		}
		return saida;
	}

	private ArrayList<Item> getAllItens(Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> listaAllItens = new ArrayList<>();
		for (Usuario u : todosUsuarios.values()) {
			if (!u.listaItens().isEmpty() && u != null) {
				listaAllItens.addAll(u.listaItens());
			}
		}
		return listaAllItens;
	}

	private ArrayList<Item> filtraItens(ArrayList<Item> listaItens, String filtroDeItensRequeridos) {
		ArrayList<Item> arrayItens = new ArrayList<>();
		for (Item i : listaItens) {
			if (i.ehNecessario() && filtroDeItensRequeridos.equals("necessarios"))
				arrayItens.add(i);
			else if (!i.ehNecessario() && filtroDeItensRequeridos.equals("doacoes"))
				arrayItens.add(i);
			else if (filtroDeItensRequeridos.equals("todos"))
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
		ArrayList<String> listaDescritores = new ArrayList<>();
		for (String key : this.descritores.keySet()) {
			listaDescritores.add(key);
		}
		Collections.sort(listaDescritores);
		for (int i = 0; i < listaDescritores.size(); i++) {
			int quantidade = this.descritores.get(listaDescritores.get(i));
			listaDescritores.set(i, quantidade + " - " + listaDescritores.get(i));
		}
		return listaDescritores;
	}

}