package eDoe.controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import eDoe.comparators.DoacaoComparator;
import eDoe.models.Doador;
import eDoe.models.Item;
import eDoe.models.Receptor;
import eDoe.models.Usuario;
import eDoe.utils.Ferramentas;
import eDoe.utils.Validador;

/**
 * Classe responsável por controlar o sistema eDoe
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 *
 */
public class CrudUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Usuario> usuarios;
	private ArrayList<String> doacoesRealizadas;
	private GestorItem g;

	/**
	 * Construtor da classe CrudUsuario
	 */
	public CrudUsuario() {
		this.usuarios = new LinkedHashMap<String, Usuario>();
		this.g = new GestorItem();
		this.doacoesRealizadas = new ArrayList<>();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método responsável por ler o arquivo que contém os receptores e atualiza-los
	 * ou cadastra-los no sistema (colocando no mapa de usuarios)
	 * 
	 * @param caminho Caminho para o arquivo que contém os dados dos receptores
	 * @throws IOException Exeção lançada caso dê algum erro durante a leitura do
	 *                     arquivo
	 */
	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		while (sc.hasNextLine()) {
			String[] dados = sc.nextLine().split(",");
			if (!this.usuarios.containsKey(dados[0])) {
				Usuario u = new Receptor(dados[0], dados[1], dados[2], dados[3], dados[4], "Receptor");
				this.usuarios.put(dados[0], u);
			} else {
				this.usuarios.get(dados[0]).setNome(dados[1]);
				this.usuarios.get(dados[0]).setEmail(dados[2]);
				this.usuarios.get(dados[0]).setCelular(dados[3]);
			}
		}
		sc.close();
	}

	/**
	 * Método responsável por adicionar um doador no sistema (colocando no mapa de
	 * usuarios), verificando se os dados passados são válidos
	 * 
	 * @param idDoador Documento de identificação do doador
	 * @param nome     Nome do doador
	 * @param email    Email do doador
	 * @param celular  Celular do doador
	 * @param classe   Classe do doador
	 * @return Retorna o documento de identificação (ID) do usuário adicionado
	 */
	public String adicionarDoador(String idDoador, String nome, String email, String celular, String classe) {
		Validador.validadorAdicionaDoador(idDoador, nome, email, celular, classe, this.usuarios);
		this.usuarios.put(idDoador, new Doador(idDoador, nome, email, celular, classe, "doador"));
		return idDoador;
	}

	/**
	 * Método que pesquisa e identifica o usuário que contém seu documento de
	 * identificação (ID) requerido, verificando se o ID passado é válido
	 * 
	 * @param id documento de identificação (ID) do doador
	 * @return String com o usuário correspondente ao ID
	 */
	public String pesquisaUsuarioPorId(String id) {
		Validador.validadorPesquisaUsuarioPorId(id, this.usuarios);
		Usuario u = this.usuarios.get(id);
		return u.toString();
	}

	/**
	 * Método que pesquisa e identifica usuários que contem o nome requerido,
	 * verificando se o nome passado é válido
	 * 
	 * @param nome Nome do doador
	 * @return String com a sequência de usuários que possuem o nome pesquisado
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		Validador.validadorPesquisaUsuarioPorNome(nome, this.usuarios);
		ArrayList<String> suporte = new ArrayList<>();
		for (Usuario u : this.usuarios.values()) {
			if (u.getNome().equals(nome))
				suporte.add(u.toString());
		}
		return Ferramentas.arrayToString(suporte);
	}

	/**
	 * Método responsável por atualizar informações do usuário, verificando se os
	 * dados passados são válidos
	 * 
	 * @param idUsuario Documento de identificação do usuário
	 * @param nome      Nome do doador
	 * @param email     Email do doador
	 * @param celular   Celular do doador
	 * @return String do usuário com as informações atualizadas
	 */
	public String atualizaUsuario(String idUsuario, String nome, String email, String celular) {
		Usuario u = getUsuarioValido(idUsuario, "doador");
		if (nome != null && !nome.trim().equals("")) {
			u.setNome(nome);
		}
		if (email != null && !email.trim().equals("")) {
			u.setEmail(email);
		}
		if (celular != null && !celular.trim().equals("")) {
			u.setCelular(celular);
		}
		return u.toString();
	}

	/**
	 * Método que remove um usuário do sistema, verificando se o ID passado é válido
	 * 
	 * @param id Documento de identificação (ID) do usuário
	 */
	public void removeUsuario(String id) {
		Validador.validadorParametro(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		Validador.validadorRemoveUsuario(id, this.usuarios);
		this.usuarios.remove(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que adiciona um descritor de item no sistema
	 * 
	 * @param descritor Descritor geral dos itens
	 */
	public void adicionarDescritor(String descricao) {
		this.g.adicionarDescritor(descricao);
	}

	/**
	 * Método que adiciona um item para doação, verificando se o doador é válido
	 * 
	 * @param idDoador   Documento de identificação do doador
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @param idItem     código de identificação (ID) do item a ser adicionado
	 * @return int com o código de identificação (ID) do item doado
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags, int idItem) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		return this.g.adicionaItemParaDoacao(u, descricao, quantidade, tags, idItem);
	}

	/**
	 * Método que exibe um item especifico, verificando se o doador é válido
	 * 
	 * @param idItem   Código de identificação (ID) do item
	 * @param idDoador Documento de identificação do doador
	 * @return String com o item correspondente aos parametros passados
	 */
	public String exibeItem(int idItem, String idDoador) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		return this.g.exibeItem(u, idItem);
	}

	/**
	 * Método responsável por atualizar informações de um item para doacao
	 * específico, verificando se o doador é válido
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param idDoador       Documento de identificação do doador
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int novaQuantidade, String novasTags) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		return this.g.atualizaItemParaDoacao(u, idItem, novaQuantidade, novasTags);
	}

	/**
	 * Método que remove um item doado do sistema, verificando se o doador é válido
	 * 
	 * @param idItem   Código de identificação (ID) do item
	 * @param idDoador Documento de identificação do doador
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		this.g.removeItemParaDoacao(u, idItem);
	}

	/**
	 * Método que lista todos os descritores dos itens para doação cadastrados no
	 * sistema
	 * 
	 * @return String com todos os descritores dos itens para doação cadastrados no
	 *         sistema, em ordem alfabética
	 */
	public String listaDescritorDeItensParaDoacao() {
		return this.g.listaDescritorDeItensParaDoacao();
	}

	/**
	 * Método que lista todos os itens para doação cadastrados no sistema
	 * 
	 * @return String com todos os itens para doação cadastrados no sistema,
	 *         ordenado pela quantidade
	 */
	public String listaItensParaDoacao() {
		return this.g.listaTodosOsItensParaDoacao(this.usuarios);
	}

	/**
	 * Método que permite listar todos os itens relacionados a uma dada descrição
	 * 
	 * @param descricao Descrição requerida dos itens
	 * @return String com todos os itens que contém a descrição requerida, em ordem
	 *         alabética da descrição
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		Validador.validadorParametro(descricao, "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		return this.g.pesquisaItemParaDoacaoPorDescricao(descricao, this.usuarios);
	}

	/**
	 * Método que adiciona um item necessário, fornecendo um ID único para o item
	 * adicionado, verificando se o receptor é válido
	 * 
	 * @param idReceptor Documento de identificação do receptor
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @param idItem     código de identificação (ID) do item a ser adicionado
	 * @return int com o código de identificação (ID) do item necessário
	 */
	public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags, int idItem) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		return this.g.adicionaItemNecessario(u, descricao, quantidade, tags, idItem);
	}

	/**
	 * Método responsável por atualizar informações de um item necessário
	 * específico, verificando se o receptor é válido
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param idReceptor     Documento de identificação do receptor
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		return this.g.atualizaNecessario(u, idItem, novaQuantidade, novasTags);
	}

	/**
	 * Método que lista todos os itens necessários cadastrados no sistema
	 * 
	 * @return String com todos os itens para doação cadastrados no sistema,
	 *         ordenados pelo código de identificação (ID) dos itens
	 */
	public String listaItensNecessarios() {
		return this.g.listaItensNecessarios(this.usuarios);
	}

	/**
	 * Método que remove um item necessário do sistema, verificando se o receptor é
	 * válido
	 * 
	 * @param idItem     Código de identificação (ID) do item
	 * @param idReceptor Documento de identificação do receptor
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		this.g.removeItemNecessario(u, idItem);
	}

	/**
	 * Método responsável por encontrar casamentos (matches) entre itens a serem
	 * doados e itens necessários, verificando se o receptor é válido
	 * 
	 * @param idReceptor       Documento de identificação do receptor
	 * @param idItemNecessario Código de identificação (ID) do item necessário
	 * @return String com todos os itens a serem doados que pontuaram nesse processo
	 *         (zero ou mais itens podem ser retornados), ordenados da maior para a
	 *         menor pontuação. Se as pontuações forem iguais ordenar-se pelo código
	 *         de identificação (ID) dos itens
	 */
	public String match(String idReceptor, int idItemNecessario) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		return this.g.match(u, idItemNecessario, this.usuarios);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Doacao ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * 
	 * Método que realiza a doação no sistema, verificando se os itens e as datas
	 * são válidos, atualizando as informações dos itens e colocando o comprovante
	 * da doação na lista de doações
	 * 
	 * @param idItemNecessario Código de identificação (ID) do item necessário
	 * @param idItemDoado      Código de identificação (ID) do item doado
	 * @param data             Data de ocorrência da doação
	 * @return String representando a realização da doação, contendo as informações
	 *         do doador, do receptor, do item doado e a data de ocorrência da
	 *         transação
	 */
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		ArrayList<Item> itens = this.g.getItensParaRealizarDoacao(idItemNecessario, idItemDoado, this.usuarios);

		Validador.verificadorRealizaDoacao(itens, idItemNecessario, idItemDoado, data);
		String saida = makeComprovanteDoacao(itens.get(0), itens.get(1), data);

		this.doacoesRealizadas.add(saida);
		atualizaItensAposDoacao(itens.get(0), itens.get(1));

		return saida;
	}

	/**
	 * Método que lista todas as doações feitas no sitema
	 * 
	 * @return String com todas as doações já feitas no sistema, ordenadas da doação
	 *         da mais antiga para a mais nova. Caso as datas sejam iguais
	 *         ordenar-se pela ordem alfabética das descrições dos itens doados.
	 */
	public String listaDoacoes() {
		Collections.sort(this.doacoesRealizadas, new DoacaoComparator());
		return Ferramentas.arrayToString(this.doacoesRealizadas);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método responsável por atualizar as quantidades dos itens após a realização
	 * de uma doação
	 * 
	 * @param itemNecessario Item requerido para doação
	 * @param itemDoado      Item doado na doação
	 */
	private void atualizaItensAposDoacao(Item itemNecessario, Item itemDoado) {
		String idReceptor = itemNecessario.getDadosDoEmissor().split("/")[1];
		String idDoador = itemDoado.getDadosDoEmissor().split("/")[1];

		if (itemNecessario.getQuantidade() == 0) {
			removeItemNecessario(idReceptor, itemNecessario.getId());
		}
		if (itemDoado.getQuantidade() == 0) {
			removeItemParaDoacao(itemDoado.getId(), idDoador);
		}
	}

	/**
	 * Método responsável por gerar um comprovante textual da doação realizada
	 * 
	 * @param itemNecessario Item requerido para doação
	 * @param itemDoado      Item doado na doação
	 * @param dataData       da transação
	 * @return String com o comprovante da doação, informando o item doado, o
	 *         doador, o receptor e a data da transação
	 */
	private String makeComprovanteDoacao(Item itemNecessario, Item itemDoado, String data) {
		return data + " - " + itemDoado.getDadosDoEmissor() + ", item: " + itemNecessario.getDescricao()
				+ ", quantidade: " + this.g.getNumItensDoados(itemNecessario, itemDoado) + ", "
				+ itemNecessario.getDadosDoEmissor().replaceFirst("R", "r");
	}

	/**
	 * Método que verifica se um usuário é válido ou não
	 * 
	 * @param idUsuario Documento de identificação do usuário
	 * @param status    Status ao qual o usuário pertence (doador ou receptor)
	 * @return Usuário correspondente ao documento de identificação
	 * @throws IllegalArgumentException Exeção lançada caso o ID do usuário nao seja
	 *                                  válido. Ou seja, caso o usuário não exista,
	 *                                  não pertença ao status descrito, ou o
	 *                                  documento de identificação seja negativo ou
	 *                                  inexistente.
	 */
	public Usuario getUsuarioValido(String idUsuario, String status) {
		Validador.validadorParametro(idUsuario, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!this.usuarios.containsKey(idUsuario))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		if (!usuarios.get(idUsuario).getStatus().equals(status))
			throw new IllegalArgumentException(
					"O Usuario deve ser um " + status.toLowerCase() + ": " + idUsuario + ".");
		return this.usuarios.get(idUsuario);
	}

	/**
	 * Método que retorna o mapa de usuarios
	 * 
	 * @return Mapa de usuarios
	 */
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	/**
	 * Método que retorna a lista de doacoes
	 * 
	 * @return ArrayList de doações realizadas
	 */
	public ArrayList<String> getDoacoesRealizadas() {
		return doacoesRealizadas;
	}
	
	/**
	 * Metodo que altera a base de dados de usuarios cadastrados no sistema.
	 *  
	 * @param usuarios o Novo mapa de usuarios. 
	 */
	public void carregaUsuarios(Map<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * Metodo que altera a base de dados das doações cadastradas no sistema. 
	 * 
	 * @param doacoes o Novo ArrayList de doacoes. 
	 */
	public void carregaDoacoes(ArrayList<String> doacoes) {
		this.doacoesRealizadas = doacoes;
	}

	

}