package eDoe.controllers;

import java.io.Serializable;
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

/**
 * Classe responsável por gerenciar os itens do sistema eDoe
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 *
 */
public class GestorItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> descritores;
	private MatchMaker mm;

	/**
	 * Construtor da classe GestorItem
	 */
	public GestorItem() {
		this.descritores = new LinkedHashMap<>();
		this.mm = new MatchMaker();
	}

	/**
	 * Método que adiciona um descritor válido no mapa descritores
	 * 
	 * @param descritor Descritor geral dos itens
	 */
	public void adicionarDescritor(String descricao) {
		Validador.validadorAdicionaDescritor(descricao, this.descritores);
		this.descritores.put(descricao.toLowerCase().trim(), 0);
	}

	/**
	 * Método que adiciona um item para doação, verificando se o item é válido.
	 * Adicionando também, a quantidade de itens contidos no descritor daquele item
	 * 
	 * @param doador     Doador do item
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @param idItem     Código de identificação (ID) do item a ser adicionado
	 * @return int com o código de identificação (ID) do item doado
	 */
	public int adicionaItemParaDoacao(Usuario doador, String descricao, int quantidade, String tags, int idItem) {
		Validador.validadorAdicionaItem(descricao, quantidade);
		String descrMin = descricao.toLowerCase().trim();
		this.descritores.put(descrMin, quantidade);
		return doador.adicionaItemParaDoacao(descricao, quantidade, tags, false, idItem);
	}

	/**
	 * Método que exibe um item especifico, verificando se o item é válido
	 * 
	 * @param idItem Código de identificação (ID) do item
	 * @param user   Usuário válido, dono do item
	 * @return String com o item correspondente aos parametros passados
	 */
	public String exibeItem(Usuario user, int idItem) {
		Validador.validadorExibeItem(user, idItem);
		return user.exibeItem(idItem);
	}

	/**
	 * Método responsável por atualizar informações de um item para doacao
	 * específico, verificando se o item é válido
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param user           Usuário válido, dono do item
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaItemParaDoacao(Usuario user, int idItem, int quantidade, String tags) {
		Validador.verificadorAtualizaItemParaDocao(user, idItem, quantidade);
		String saida = user.atualizaItem(idItem, quantidade, tags);
		atualizaDescritores(user, idItem, "atualizaItem");
		return saida;
	}

	/**
	 * Método que remove um item doado do sistema, verificando se o item é válido
	 * 
	 * @param idItem Código de identificação (ID) do item
	 * @param user   Usuário válido, dono do item
	 */
	public void removeItemParaDoacao(Usuario user, int idItem) {
		Validador.verificadorRemoveItem(user, idItem);
		atualizaDescritores(user, idItem, "removeItem");
		user.removeItem(idItem);
	}

	/**
	 * Método que lista todos os descritores dos itens para doação cadastrados no
	 * sistema
	 * 
	 * @return String com todos os descritores dos itens para doação contidos no
	 *         mapa de descritores, em ordem alfabética
	 */
	public String listaDescritorDeItensParaDoacao() {
		return Ferramentas.arrayToString(makeListaDescritores());
	}

	/**
	 * Método que lista todos os itens para doação cadastrados no sistema, juntando
	 * todos os itens de todos os doadores
	 * 
	 * @param todosUsuarios Todos os usuários cadastrados no sistema
	 * @return String com todos os itens para doação cadastrados no sistema,
	 *         ordenado pela quantidade
	 */
	public String listaTodosOsItensParaDoacao(Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> arrayItensRequeridos = filtraItens(getAllItens(todosUsuarios), "doacao");
		Collections.sort(arrayItensRequeridos, new QuantidadeComparator());
		return makeListaItens(arrayItensRequeridos, "fichaTecnica");
	}

	/**
	 * Método que permite listar todos os itens relacionados a uma dada descrição,
	 * juntando todos os itens de todos os usuarios e selecionando os itens com a
	 * descrição requerida
	 * 
	 * @param descricao     Descrição requerida dos itens
	 * @param todosUsuarios Todos os usuários cadastrados no sistema
	 * @return String com todos os itens que contém a descrição requerida, em ordem
	 *         alabética da descrição
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao, Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> arrayItensRequeridos = new ArrayList<>();
		for (Item i : getAllItens(todosUsuarios)) {
			if (i.toString().contains(descricao))
				arrayItensRequeridos.add(i);
		}
		Collections.sort(arrayItensRequeridos, new DescricaoComparator());
		return makeListaItens(arrayItensRequeridos, "toString");
	}

	/**
	 * Método que adiciona um item necessário, verificando se o item é válido.
	 * 
	 * @param receptor   Receptor do item
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @param idItem     Código de identificação (ID) do item a ser adicionado
	 * @return int com o código de identificação (ID) do item necessário
	 */
	public int adicionaItemNecessario(Usuario receptor, String descricao, int quantidade, String tags, int idItem) {
		Validador.validadorAdicionaItem(descricao, quantidade);
		String descrMin = descricao.toLowerCase().trim();
		return receptor.adicionaItemNecessario(descrMin, quantidade, tags, true, idItem);
	}

	/**
	 * Método responsável por atualizar informações de um item necessário
	 * específico, verificando se o receptor é válido
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param user           Usuário válido, dono do item
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaNecessario(Usuario user, int idItem, int novaQuantidade, String novasTags) {
		Validador.verificadorAtualizaItemNecessario(user, idItem);
		String saida = user.atualizaItem(idItem, novaQuantidade, novasTags);
		atualizaDescritores(user, idItem, "atualizaItem");
		return saida;
	}

	/**
	 * Método que lista todos os itens necessários cadastrados no sistema
	 * 
	 * @return String com todos os itens para doação cadastrados no sistema,
	 *         ordenados pelo código de identificação (ID) dos itens
	 */
	public String listaItensNecessarios(Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> arrayItensRequeridos = filtraItens(getAllItens(todosUsuarios), "necessarios");
		Collections.sort(arrayItensRequeridos, new IdComparator());
		return makeListaItens(arrayItensRequeridos, "fichaTecnica");
	}

	/**
	 * Método que remove um item necessário do sistema, verificando se o receptor é
	 * válido
	 * 
	 * @param idItem Código de identificação (ID) do item
	 * @param user   Usuário válido, dono do item
	 */
	public void removeItemNecessario(Usuario user, int idItem) {
		Validador.verificadorRemoveItem(user, idItem);
		user.removeItem(idItem);
	}

	/**
	 * Método responsável por encontrar casamentos (matches) entre itens a serem
	 * doados e itens necessários, verificando se o item é válido
	 * 
	 * @param user             Usuário válido, dono do item
	 * @param idItemNecessario Código de identificação (ID) do item necessário
	 * @param todosUsuarios    Todos os usuários cadastrados no sistema
	 * @return String com todos os itens a serem doados que pontuaram nesse processo
	 *         (zero ou mais itens podem ser retornados), ordenados da maior para a
	 *         menor pontuação. Se as pontuações forem iguais ordenar-se pelo código
	 *         de identificação (ID) dos itens
	 */
	public String match(Usuario user, int idItemNecessario, Map<String, Usuario> todosUsuarios) {
		Validador.verificadorMatchItem(user, idItemNecessario);
		Item itemDeMatch = user.getItemPorId(idItemNecessario);
		ArrayList<Item> todasAsDoacoes = filtraItens(getAllItens(todosUsuarios), "doacao");
		ArrayList<Item> arrayItensRequeridos = mm.getItensMatchMaker(todasAsDoacoes, itemDeMatch);
		Collections.sort(arrayItensRequeridos, new MatchComparator());
		return makeListaItens(arrayItensRequeridos, "fichaTecnica");
	}

	/**
	 * Método que encontra os itens para a doação, e verifica se eles são válidos
	 * 
	 * @param idItemNecessario Código de indentificação do item necessário
	 * @param idItemDoado      Código de indentificação do item doado
	 * @param todosUsuarios    Todos os usuários cadastrados no sistema
	 * @return ArrayList com os itens que atendem os parametros requeridos. Sendo o
	 *         primeiro item do ArrayList um item necessário, e o segundo um item
	 *         para doação
	 */
	public ArrayList<Item> getItensParaRealizarDoacao(int idItemNecessario, int idItemDoado,
			Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> todosOsItens = filtraItens(getAllItens(todosUsuarios), "todos");
		ArrayList<Item> itensRequeridos = new ArrayList<>();
		for (Item i : todosOsItens) {
			if (i.getId() == idItemNecessario)
				itensRequeridos.add(i);
			if (i.getId() == idItemDoado)
				itensRequeridos.add(i);
		}
		Collections.sort(itensRequeridos);
		return itensRequeridos;
	}

	/**
	 * Método que analisa quantos itens existem no estoque e a quantidade que é
	 * requerida para doação. Organizando a quantidade de itens que se é doado
	 * 
	 * @param itemNecessario Item necessario para doação
	 * @param itemDoado      Item a ser doado
	 * @return int com a quantidade de itens retirados do estoque para a doação
	 */
	public int getNumItensDoados(Item itemNecessario, Item itemDoado) {
		int quantidadeItensRetirados = 0;
		if (itemNecessario.getQuantidade() >= itemDoado.getQuantidade()) {
			quantidadeItensRetirados = itemDoado.getQuantidade();
		} else {
			quantidadeItensRetirados = itemNecessario.getQuantidade();
		}
		atualizaEstoque(itemNecessario, itemDoado);
		return quantidadeItensRetirados;

	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que atualiza a quantidade dos itens necessários e disponíveis.
	 * 
	 * @param itemNecessario Item necessario para doação
	 * @param itemDoado      Item a ser doado
	 */
	private void atualizaEstoque(Item itemNecessario, Item itemDoado) {
		if (itemNecessario.getQuantidade() >= itemDoado.getQuantidade()) {
			itemNecessario.setQuantidade(itemNecessario.getQuantidade() - itemDoado.getQuantidade());
			itemDoado.setQuantidade(0);
		} else {
			itemDoado.setQuantidade(itemDoado.getQuantidade() - itemNecessario.getQuantidade());
			itemNecessario.setQuantidade(0);
		}
	}

	
	/**
	 * Método responsável por listar os itens requeridos com as informações
	 * requeridas. Sendo elas: 
	 *  1- O toString do item, com a descrição, as tags e a quantidade do item
	 *	2- A ficha tecnica do item, com com a descrição, as tags, a quantidade e 
	 *     os dados do dono do item
	 * 
	 * @param arrayItensRequeridos ArrayList com todos os itens a serem listados
	 * @param tipoDeSaida          Tipo de representação do item que se quer ter
	 *                             (toString/FichaTecnica)
	 * @return String com uma lista de itens representados pelo tipo de saida
	 */
	private String makeListaItens(ArrayList<Item> arrayItensRequeridos, String tipoDeSaida) {
		String saida = "";
		if (arrayItensRequeridos.isEmpty())
			return saida;

		else if (tipoDeSaida.equals("toString"))
			saida = Ferramentas.arrayToString(arrayItensRequeridos);

		else if (tipoDeSaida.equals("fichaTecnica")) {
			Item i = arrayItensRequeridos.get(0);
			saida = i.toString() + ", " + i.getDadosDoEmissor();
			for (int j = 1; j < arrayItensRequeridos.size(); j++) {
				i = arrayItensRequeridos.get(j);
				saida += " | " + i.toString() + ", " + i.getDadosDoEmissor();
			}
		}
		return saida;
	}

	/**
	 * Método que permite pegar todos os itens cadastrados no sistema, pegando 
	 * todos os itens de todos os usuarios, e quando-os em um ArrayList
	 * 
	 * @param todosUsuarios    Todos os usuários cadastrados no sistema
	 * @return ArrayList com todos os itens cadastrados no sistema
	 */
	private ArrayList<Item> getAllItens(Map<String, Usuario> todosUsuarios) {
		ArrayList<Item> listaAllItens = new ArrayList<>();
		for (Usuario u : todosUsuarios.values()) {
			if (!u.listaItens().isEmpty() && u != null) {
				listaAllItens.addAll(u.listaItens());
			}
		}
		return listaAllItens;
	}

	/**
	 * Método que seleciona apenas os itens requeridos. Podendo ser eles:
	 * 	1- Os itens necessarios
	 * 	2- Os itens para doacao 
	 * 	3- Todos os itens 
	 *
	 * @param listaItens 			  Lista com todos os itens
	 * @param filtroDeItensRequeridos Filtro referente aos itens que se quer
	 * @return ArrayList apenas com os itens requisitados
	 */
	private ArrayList<Item> filtraItens(ArrayList<Item> listaItens, String filtroDeItensRequeridos) {
		ArrayList<Item> arrayItens = new ArrayList<>();
		for (Item i : listaItens) {
			if (i.ehNecessario() && filtroDeItensRequeridos.equals("necessarios"))
				arrayItens.add(i);
			else if (!i.ehNecessario() && filtroDeItensRequeridos.equals("doacao"))
				arrayItens.add(i);
			else if (filtroDeItensRequeridos.equals("todos"))
				arrayItens.add(i);
		}
		return arrayItens;
	}

	/**
	 * Método que atualiza os descritores após alguma doação ou alteração dos itens
	 * 
	 * @param user 			   Usuário válido, dono do item
	 * @param idItem 		   Código de identificação (ID) do item a ser adicionado
	 * @param modificacaoFeita Tipo da modificação feita no item que contem o descritor,
	 * 						   podendo ser ela: uma atualização ou uma remoção.
	 */
	private void atualizaDescritores(Usuario user, int idItem, String modificacaoFeita) {
		String descrMin = user.getItens().get(idItem).getDescricao();
		int quantItem = user.getItens().get(idItem).getQuantidade();

		if (modificacaoFeita.equals("atualizaItem"))
			this.descritores.put(descrMin, quantItem);

		else if (modificacaoFeita.equals("removeItem")) {
			int quantItensTotal = this.descritores.get(descrMin) - quantItem;
			this.descritores.put(descrMin, quantItensTotal);
		}
	}

	/**
	 * Método responsável por listar os descritores do sistema
	 *
	 * @return ArrayList com todos os descritores existentes
	 */
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

	/**
	 * Método que retorna o mapa de descritores 
	 * 
	 * @return Mapa de descritores
	 */
	public Map<String, Integer> getDescritores() {
		return this.descritores;
	}

	/**
	 * Metodo que altera a base de dados dos descritores cadastradas no sistema.
	 *  
	 * @param descritores Novo mapa de descritores. 
	 */
	public void carregaDescritores(Map<String, Integer> descritores) {
		this.descritores = descritores;
	}
}