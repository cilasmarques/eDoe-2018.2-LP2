package eDoe;

import java.io.IOException;

import eDoe.controllers.CrudUsuario;
import eDoe.utils.Ferramentas;
import easyaccept.EasyAccept;

/**
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 *
 */
public class Facade {

	private CrudUsuario cd = new CrudUsuario();

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método responsável por ler receptores
	 * 
	 * @param caminho Caminho que indica a localização do arquivo dos receptores
	 * @throws IOException Excessão lançada caso haja falha na leitura
	 */
	public void lerReceptores(String caminho) throws IOException {
		this.cd.lerReceptores(caminho);
	}

	/**
	 * Método responsável por adicionar o doador no sistema
	 * 
	 * @param id      Documento de identificação do doador
	 * @param nome    Nome do doador
	 * @param email   Email do doador
	 * @param celular Celular do doador
	 * @param classe  Classe do doador
	 * @return Retorna o documento de identificação (ID) do usuário adicionado
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.cd.adicionarDoador(id, nome, email, celular, classe);
	}

	/**
	 * Método que pesquisa e identifica o usuário que contém seu documento de
	 * identificação (ID) requerido
	 * 
	 * @param id Documento de identificação do usuário
	 * @return String com o usuário correspondente ao ID
	 */
	public String pesquisaUsuarioPorId(String id) {
		return this.cd.pesquisaUsuarioPorId(id);
	}

	/**
	 * Método que pesquisa e identifica usuários que contem o nome requerido
	 * 
	 * @param nome Nome do usuário
	 * @return String com a sequência de usuários que possuem o nome pesquisado
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return this.cd.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Método responsável por atualizar informações do usuário
	 * 
	 * @param id      Documento de identificação do usuário
	 * @param nome    Nome do doador
	 * @param email   Email do doador
	 * @param celular Celular do doador
	 * @return String do usuário com as informações atualizadas
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.cd.atualizaUsuario(id, nome, email, celular);
	}

	/**
	 * Método que remove um usuário do sistema
	 * 
	 * @param id Documento de identificação (ID) do usuário
	 */
	public void removeUsuario(String id) {
		this.cd.removeUsuario(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que adiciona um descritor de item no sistema
	 * 
	 * @param descritor Descritor geral dos itens
	 */
	public void adicionaDescritor(String descritor) {
		this.cd.adicionarDescritor(descritor);
	}

	/**
	 * Método que adiciona um item para doação, fornecendo um ID único para o item
	 * adicionado
	 * 
	 * @param idDoador   Documento de identificação do doador
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @return int com o código de identificação (ID) do item doado
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return this.cd.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags, Ferramentas.idUnico += 1);
	}

	/**
	 * Método que exibe um item especifico
	 * 
	 * @param idItem   Código de identificação (ID) do item
	 * @param idDoador Documento de identificação do doador
	 * @return
	 */
	public String exibeItem(int idItem, String idDoador) {
		return this.cd.exibeItem(idItem, idDoador);
	}

	/**
	 * Método responsável por atualizar informações de um item para doacao
	 * específico
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param idDoador       Documento de identificação do doador
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int novaQuantidade, String novasTags) {
		return this.cd.atualizaItemParaDoacao(idItem, idDoador, novaQuantidade, novasTags);
	}

	/**
	 * Método que remove um item doado do sistema
	 * 
	 * @param idItem   Código de identificação (ID) do item
	 * @param idDoador Documento de identificação do doador
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		this.cd.removeItemParaDoacao(idItem, idDoador);
	}

	/**
	 * Método que lista todos os descritores dos itens para doação cadastrados no
	 * sistema
	 * 
	 * @return String com todos os descritores dos itens para doação cadastrados no
	 *         sistema, em ordem alfabética
	 */
	public String listaDescritorDeItensParaDoacao() {
		return this.cd.listaDescritorDeItensParaDoacao();
	}

	/**
	 * Método que lista todos os itens para doação cadastrados no sistema
	 * 
	 * @return String com todos os itens para doação cadastrados no sistema,
	 *         ordenado pela quantidade
	 */
	public String listaItensParaDoacao() {
		return this.cd.listaItensParaDoacao();
	}

	/**
	 * Método que permite listar todos os itens relacionados a uma dada descrição
	 * 
	 * @param descricao Descrição requerida dos itens
	 * @return String com todos os itens que contém a descrição requerida, em ordem
	 *         alabética
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return this.cd.pesquisaItemParaDoacaoPorDescricao(descricao);
	}

	/**
	 * Método que adiciona um item necessário, fornecendo um ID único para o item
	 * adicionado
	 * 
	 * @param idReceptor Documento de identificação do receptor
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @return int com o código de identificação (ID) do item necessário
	 */
	public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags) {
		return this.cd.adicionaItemNecessario(idReceptor, descricao, quantidade, tags, Ferramentas.idUnico += 1);
	}

	/**
	 * Método responsável por atualizar informações de um item necessário específico
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param idReceptor     Documento de identificação do receptor
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		return this.cd.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
	}

	/**
	 * Método que lista todos os itens necessários cadastrados no sistema
	 * 
	 * @return String com todos os itens para doação cadastrados no sistema,
	 *         ordenados pelo código de identificação (ID) dos itens
	 */
	public String listaItensNecessarios() {
		return this.cd.listaItensNecessarios();
	}

	/**
	 * Método que remove um item necessário do sistema
	 * 
	 * @param idItem     Código de identificação (ID) do item
	 * @param idReceptor Documento de identificação do receptor
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		this.cd.removeItemNecessario(idReceptor, idItem);
	}

	/**
	 * Método responsável por encontrar casamentos (matches) entre itens a serem
	 * doados e itens necessários.
	 * 
	 * @param idReceptor       Documento de identificação do receptor
	 * @param idItemNecessario Código de identificação (ID) do item necessário
	 * @return String com todos os itens a serem doados que pontuaram nesse processo
	 *         (zero ou mais itens podem ser retornados), ordenados da maior para a
	 *         menor pontuação. Se as pontuações forem iguais ordenar-se pelo código
	 *         de identificação (ID) dos itens
	 */
	public String match(String idReceptor, int idItemNecessario) {
		return this.cd.match(idReceptor, idItemNecessario);
	}

	/**
	 * 
	 * Método que valida o pedido de doação, e realiza a doação no sistema.
	 * 
	 * @param idItemNecessario Código de identificação (ID) do item necessário
	 * @param idItemDoado      Código de identificação (ID) do item doado
	 * @param data             Data de ocorrência da doação
	 * @return String representando a realização da doação, contendo as informações
	 *         do doador, do receptor, do item doado e a data de ocorrência da
	 *         transação
	 */
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		return this.cd.realizaDoacao(idItemNecessario, idItemDoado, data);
	}

	/**
	 * Método que lista todas as doações feitas no sitema
	 * 
	 * @return String com todas as doações já feitas no sistema, ordenadas da doação
	 *         da mais antiga para a mais nova. Caso as datas sejam iguais
	 *         ordenar-se pela ordem alfabética das descrições dos itens doados.
	 */
	public String listaDoacoes() {
		return this.cd.listaDoacoes();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ EASYACCEPT

	public static void main(String[] args) {
		args = new String[] { "eDoe.Facade", "arquivos_sistema/use_case_1.txt", "arquivos_sistema/use_case_2.txt",
				"arquivos_sistema/use_case_3.txt", "arquivos_sistema/use_case_4.txt", "arquivos_sistema/use_case_5.txt",
				"arquivos_sistema/use_case_6.txt", "arquivos_sistema/use_case_7.txt" };
		EasyAccept.main(args);
	}

}
