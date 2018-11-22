package eDoe;

import eDoe.controllers.CrudUsuario;

public class Facade {
	
	private CrudUsuario cd = new CrudUsuario();
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void lerReceptores(String caminho) {
		
	} 
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe ) {
		return this.cd.adicionarDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return this.cd.pesquisaUsuarioPorId(id);
	} 
	
	public String pesquisaUsuarioPorNome(String nome) {
		return this.cd.pesquisaUsuarioPorNome(nome);
	}
	
	public void atualizaUsuario(String nome, String email, String celular) {
		this.cd.atualizaUsuario();
	}
	
	public void removerUsuario(String id) {
		this.cd.removerUsuario();
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void adicionarDescritor(String descricao) {
		this.cd.adicionarDescritor();
		}
	
	public void adicionaItem(String idDoador, String descricao, Double quantidade, String tags) {
		this.cd.adicionaItem();
	}
	
	public String exibeItem(String idItem, String idDoador) {
		return this.cd.exibeItem();
	}
	
	public void atualizaItemParaDoacao(String idItem, String idDoador, Double quantidade, String tags) {
		this.cd.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
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
	
}
