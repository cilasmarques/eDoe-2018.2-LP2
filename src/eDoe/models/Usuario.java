package eDoe.models;

import java.util.HashMap;
import java.util.Map;

public abstract class Usuario implements Usuario_eDoe{

	protected String documento;
	protected String nome;
	protected String email;
	protected String celular;
	protected String classe;
	private Map<String, Item> itens = new HashMap<>();
	
	public Usuario(String documento, String nome, String email, String celular, String classe) {
		this.documento = documento;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
	}

	public void adicionaItem(String descricao, int quantidade, String tags) {
		this.itens.put(descricao, new Item(descricao,quantidade,tags));
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getClasse() {
		return classe;
	}

}
