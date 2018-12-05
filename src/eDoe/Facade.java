package eDoe;

import java.io.IOException;

import eDoe.controllers.CrudUsuario;
import eDoe.utils.Ferramentas;
import easyaccept.EasyAccept;

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
	 * Método responsável por adicionar o doador
	 * 
	 * @param id      Documento de identificação do doador
	 * @param nome    Nome do doador
	 * @param email   Email do doador
	 * @param celular Celular do doador
	 * @param classe  Classe do doador
	 * @return Retorna o ID do usuário adicionado
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.cd.adicionarDoador(id, nome, email, celular, classe);
	}

	/**
	 * Método que pesquisa e identifica o usuário pelo o seu ID
	 * 
	 * @param id Documento de identificação do doador
	 * @return String com o usuário correspondente ao ID
	 */
	public String pesquisaUsuarioPorId(String id) {
		return this.cd.pesquisaUsuarioPorId(id);
	}

	/**
	 * Método que pesquisa e identifica o usuário pelo o seu nome
	 * 
	 * @param nome Nome do doador
	 * @return String com a sequência de usuários que possuem o nome pesquisado
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return this.cd.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Método responsável por editar informações do usuário
	 * 
	 * @param id      Documento de identificação do doador atualizado
	 * @param nome    Nome do doador atualizado
	 * @param email   Email do doador atualizado
	 * @param celular Celular do doador atualizado
	 * @return String do usuário com as informações atualizadas
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.cd.atualizaUsuario(id, nome, email, celular);
	}

	public void removeUsuario(String id) {
		this.cd.removeUsuario(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void adicionaDescritor(String descricao) {
		this.cd.adicionarDescritor(descricao);
	}

	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return this.cd.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags, Ferramentas.idUnico += 1);
	}

	public String exibeItem(int idItem, String idDoador) {
		return this.cd.exibeItem(idItem, idDoador);
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return this.cd.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	public void removeItemParaDoacao(int idItem, String idDoador) {
		this.cd.removeItemParaDoacao(idItem, idDoador);
	}

	public String listaDescritorDeItensParaDoacao() {
		return this.cd.listaDescritorDeItensParaDoacao();
	}

	public String listaItensParaDoacao() {
		return this.cd.listaItensParaDoacao();
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return this.cd.pesquisaItemParaDoacaoPorDescricao(descricao);
	}

	public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags) {
		return this.cd.adicionaItemNecessario(idReceptor, descricao, quantidade, tags);
	}

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		return this.cd.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
	}

	public String listaItensNecessarios() {
		return this.cd.listaItensNecessarios();
	}

	public void removeItemNecessario(String idReceptor, int idItem) {
		this.cd.removeItemNecessario(idReceptor, idItem);
	}

	public String match(String idReceptor, int idItemNecessario) {
		return this.cd.match(idReceptor, idItemNecessario);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ EASYACCEPT
	public static void main(String[] args) {
		args = new String[] { "eDoe.Facade", "arquivos_sistema/use_case_1.txt", "arquivos_sistema/use_case_2.txt",
				"arquivos_sistema/use_case_3.txt", "arquivos_sistema/use_case_4.txt",
				"arquivos_sistema/use_case_5.txt" };
		EasyAccept.main(args);
	}

}
