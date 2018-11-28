package eDoe;

import java.io.IOException;

import eDoe.controllers.CrudUsuario;
import easyaccept.EasyAccept;

public class Facade {

	private CrudUsuario cd = new CrudUsuario();

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void lerReceptores(String caminho) throws IOException {
		this.cd.lerReceptores(caminho);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.cd.adicionarDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return this.cd.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return this.cd.pesquisaUsuarioPorNome(nome);
	}

	public void atualizaUsuario(String nome, String email, String celular) {
		this.cd.atualizaUsuario(nome, email, celular, celular);
	}

	public void removeUsuario(String id) {
		this.cd.removeUsuario(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void adicionaDescritor(String descricao) {
		this.cd.adicionarDescritor(descricao);
	}

	public String adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		return this.cd.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags);
	}

	public String exibeItem(String idItem, String idDoador) {
		return this.cd.exibeItem(idItem, idDoador);
	}

	public String atualizaItemParaDoacao(String idItem, String idDoador, int quantidade, String tags) {
		return this.cd.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	public void removeItemParaDoacao(String idItem, String idDoador) {
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

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ EASYACCEPT
	public static void main(String[] args) {
		args = new String[] { "eDoe.Facade", "arquivos_sistema/use_case_1.txt" ,"arquivos_sistema/use_case_2.txt"};
		EasyAccept.main(args);
	}
	
}
