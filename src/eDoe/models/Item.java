package eDoe.models;

public class Item implements Item_eDoe {

	protected String tags;
	protected int quantidade;
	protected String descricao;

	public Item(String descricao, int quantidade, String tags) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
	}

}
