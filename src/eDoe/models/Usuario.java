package eDoe.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
	public void setClasse(String classe) {
		// TODO Auto-generated method stub
		
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Itens ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private Map<Integer, Item> itens = new LinkedHashMap<Integer, Item>();

	public int adicionaItemParaDoacao(String descricao, int quantidade, String tags, boolean ehNecessario) {
		Item i = new Item(descricao, quantidade, tags, ehNecessario);
		if (this.itens.values().contains(i)) {
			Item iExistente = getItemPorDescricao(descricao);
			iExistente.setQuantidade(quantidade);
			return iExistente.getId();
		}
		geradorIdUnico(i);
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

	public Map<Item, String> listaItens() {
		return makeListaItens();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis Itens ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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

	private void geradorIdUnico(Item i) {
		int id = new Random().nextInt(89999999) + 10000000;
		if (!this.itens.containsKey(id))
			i.setId(id);
	}

	private Map<Item, String> makeListaItens() {
		Map<Item, String> listaItens= new HashMap<>();
		for (Item i : this.itens.values()) {
			String infosItem = (i.toString() + ", doador: " + this.nome + "/" + this.documento);
			listaItens.put(i, infosItem);
		}
		return listaItens;
	}

	private ArrayList<String> arrayPutInfosItens(ArrayList<Item> arrayDescritores) {
		ArrayList<String> arrayDeInfos = new ArrayList<>();
		for (Item i : arrayDescritores) {
			if (i != null)
				arrayDeInfos.add(i.toString() + ", doador: " + this.nome + "/" + this.documento);
		}
		return arrayDeInfos;
	}

}
