package eDoe.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Usuario implements Usuario_eDoe {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	protected String documento;
	protected String nome;
	protected String email;
	protected String celular;
	protected String classe;

	public Usuario(String documento, String nome, String email, String celular, String classe) {
		this.documento = documento;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
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

	public Map<Integer, Item> getItens() {
		return this.itens;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Itens ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private Map<Integer, Item> itens = new HashMap<>();

	public int adicionaItemParaDoacao(String descricao, int quantidade, String tags, boolean ehNecessario) {
		if (itemJaExiste(descricao)) {
			Item item = getItemPorDescricao(descricao);
			item.setQuantidade(quantidade);
			return item.getId();
		}
		Item i = new Item(descricao, quantidade, tags, ehNecessario);
		geradorId(i);
		this.itens.put(i.getId(), i);
		return i.getId();
	}

	public String exibeItem(int idItem) {
		return this.itens.get(idItem).toString();
	}

	public String atualizaItemParaDoacao(int idItem, int quantidade, String tags) {
		Item i = this.itens.get(idItem);
		i.setQuantidade(quantidade);
		i.setTags(tags);
		return i.toString();
	}

	public void removeItemParaDoacao(int idItem) {
		this.itens.remove(idItem);
	}

	public Item getItemPorDescricao(String descricao) {
		for (Item item : this.itens.values()) {
			if (item.getDescricao().equals(descricao))
				return item;
		}
		return null;
	}

	private void geradorId(Item i) {
		int id = new Random().nextInt(1000);
		i.setId(id);
	}

	private boolean itemJaExiste(String descricao) {
		for (Item item : this.itens.values()) {
			if (item.getDescricao().equals(descricao))
				return true;
		}
		return false;
	}

}
