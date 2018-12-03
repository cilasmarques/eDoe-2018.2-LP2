package eDoe.models;

import java.util.ArrayList;

public class Item implements Item_eDoe { //, Comparable<Item> {

	private ArrayList<String> tags;
	private String descricao;
	private int quantidade;
	private boolean necessidade;
	private int id = 0;

	public Item(String descricao, int quantidade, String tags, boolean ehNecessario, int id) {
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.necessidade = ehNecessario;
		this.tags = stringToArray(tags);
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		if (quantidade > 0)
			this.quantidade = quantidade;
	}

	public void setTags(String tag) {
		if (tag != null)
			this.tags = stringToArray(tag);
	}

	private ArrayList<String> stringToArray(String str) {
		ArrayList<String> array = new ArrayList<String>();
		for (String s : str.split(",")) {
			array.add(s);
		}
		return array;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", tags: " + this.tags + ", quantidade: " + this.quantidade;
	}
	
	@Override
	public boolean ehNecessario() {
		return this.necessidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

}
