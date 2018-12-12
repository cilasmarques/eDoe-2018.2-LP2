package eDoe.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Item_eDoe, Comparable<Item>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> tags;
	private String descricao;
	private String dadosEmissor;
	private boolean necessidade;
	private int quantidade;
	private int id = 0;
	private int pontuacaoMatch = 0;

	public Item(String descricao, int quantidade, String tags, boolean ehNecessario, int id, String dadosEmissor) {
		this.id = id;
		this.dadosEmissor = dadosEmissor;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.necessidade = ehNecessario;
		this.tags = stringToArray(tags);
	}

	public void setQuantidade(int quantidade) {
		if (quantidade >= 0)
			this.quantidade = quantidade;
	}

	public void setTags(String tag) {
		if (tag != null)
			this.tags = stringToArray(tag);
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacaoMatch = pontuacao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Metodo que permite pegar os dados de quem emitiu o pedido produto ou o
	 * produto
	 * 
	 * @return String com os dados do emissor
	 */
	public String getDadosDoEmissor() {
		return this.dadosEmissor;
	}

	/**
	 * Método que permite pegar a identificação do item (id)
	 * 
	 * @return int com a identificação do item (id)
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método que permite pegar a quantidade de unidades do item
	 * 
	 * @return int com a quantidade de unidades do item
	 */
	public int getQuantidade() {
		return this.quantidade;
	}

	/**
	 * Metodo que permite pegar a pontuação de match que um produto atingiu
	 * 
	 * @return int com a pontuação de match atingida
	 */
	public int getPontuacao() {
		return this.pontuacaoMatch;
	}

	public ArrayList<String> getTags() {
		return this.tags;
	}

	@Override
	public boolean ehNecessario() {
		return this.necessidade;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", tags: " + this.tags + ", quantidade: " + this.quantidade;
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

	@Override
	public int compareTo(Item o) {
		if (o.ehNecessario())
			return 1;
		return -1;
	}

	private ArrayList<String> stringToArray(String str) {
		ArrayList<String> array = new ArrayList<String>();
		for (String s : str.split(",")) {
			array.add(s.trim());
		}
		return array;
	}
}
