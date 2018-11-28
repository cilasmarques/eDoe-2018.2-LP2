package eDoe.models;

import java.util.ArrayList;

public class Item implements Item_eDoe {

	protected ArrayList<String> tags;
	protected String descricao;
	protected int quantidade;
	private boolean necessidade;

	public Item(String descricao, int quantidade, String tags, boolean necessidade) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = stringToArray(tags);
		this.necessidade = necessidade;
	}

	@Override
	public String toString() {
		return this.descricao + ", tags: " + this.tags + ", quantidade: " + this.quantidade;
	}

	@Override
	public boolean ehNecessario() {
		return this.necessidade;
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

}
