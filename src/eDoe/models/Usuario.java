package eDoe.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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

	public String getId() {
		return this.documento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getClasse() {
		return this.classe;
	}

	public Map<Integer, Item> getItens() {
		return this.itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Itens ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private Map<Integer, Item> itens = new LinkedHashMap<Integer, Item>();

	public int adicionaItemParaDoacao(String descricao, int quantidade, String tags, boolean ehNecessario, int idItem) {
		Item i = new Item(descricao, quantidade, tags, ehNecessario, idItem, "doador: " + this.nome + "/" + this.documento);
		if (this.itens.values().contains(i) && !i.ehNecessario()) {
			Item iExistente = getItemPorDescricao(descricao);
			iExistente.setQuantidade(quantidade);
			return iExistente.getId();
		}
		this.itens.put(i.getId(), i);
		return i.getId();
	}

	public String exibeItem(int idItem) {
		return this.itens.get(idItem).toString();
	}

	public String atualizaItem(int idItem, int novaQuantidade, String novasTags) {
		Item i = this.itens.get(idItem);
		if (novaQuantidade > 0)
			i.setQuantidade(novaQuantidade);
		if (novasTags != null && !novasTags.trim().equals(""))
			i.setTags(novasTags);
		return i.toString();
	}

	public void removeItem(int idItem) {
		this.itens.remove(idItem);
	}

	public ArrayList<Item> listaItens() {
		return makeListaItens();
	}

	public int adicionaItemNecessario(String descricao, int quantidade, String tags, boolean ehNecessario, int idItem) {
		Item i = new Item(descricao, quantidade, tags, ehNecessario, idItem, "Receptor: " + this.nome + "/" + this.documento);
		if (this.itens.values().contains(i) && i.ehNecessario()) {
			Item iExistente = getItemPorDescricao(descricao);
			iExistente.setQuantidade(quantidade);
			return iExistente.getId();
		}
		this.itens.put(i.getId(), i);
		return i.getId();
	}

	public Item getItemPorId(int idItem) {
		if (itens.containsKey(idItem))
			return this.itens.get(idItem);
		return null;
	}

	public Item getItemPorDescricao(String descricao) {
		for (Item item : this.itens.values()) {
			if (item.getDescricao().equals(descricao))
				return item;
		}
		return null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis Itens ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private ArrayList<Item> makeListaItens() {
		ArrayList<Item> listaItens = new ArrayList<>();
		for (Item i : this.itens.values()) {
			listaItens.add(i);
		}
		return listaItens;
	}

}
