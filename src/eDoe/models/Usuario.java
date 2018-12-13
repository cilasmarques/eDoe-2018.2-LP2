package eDoe.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe que contem os metodos e atributos dos usuarios eDoe
 * 
 * @author Cilas Medeiros, Brenno Harten, Raiff Maia
 * 
 */
public abstract class Usuario implements Usuario_eDoe, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	protected String documento;
	protected String nome;
	protected String email;
	protected String celular;
	protected String classe;

	/**
	 * Constutor do usuario
	 * 
	 * @param documento Documento de identificação do usuário
	 * @param nome      Nome do usuário
	 * @param email     Email do usuário
	 * @param celular   Celular do usuário
	 * @param classe    Classe do usuário
	 */
	public Usuario(String documento, String nome, String email, String celular, String classe) {
		this.documento = documento;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
	}

	/**
	 * Método que retorna o id do usuário
	 * 
	 * @return String com o id do usuário
	 */
	public String getId() {
		return this.documento;
	}

	/**
	 * Método que retorna o nome do usuário
	 * 
	 * @return String com o nome do usuário
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método que retorna a classe do usuário
	 * 
	 * @return String com o classe do usuário
	 */
	public String getClasse() {
		return this.classe;
	}

	/**
	 * Método que retorna os itens do usuário
	 * 
	 * @return String com os itens do usuário
	 */
	public Map<Integer, Item> getItens() {
		return this.itens;
	}

	/**
	 * Método responsável por mudar o nome do usuário
	 * 
	 * @param nome Novo nome do usuário
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Método responsável por mudar o email do usuário
	 * 
	 * @param email Novo email do usuário
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Método responsável por mudar o celular do usuário
	 * 
	 * @param celular Novo celular do usuário
	 */
	public void setCelular(String celular) {
		this.celular = celular;
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

	/**
	 * Método que adiciona um item para doação, verificando se o item já existe ou
	 * não
	 * 
	 * @param idDoador   Documento de identificação do doador
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @param idItem     Código de identificação (ID) do item a ser adicionado
	 * @return int com o Código de identificação (ID) do item doado
	 */
	public int adicionaItemParaDoacao(String descricao, int quantidade, String tags, boolean ehNecessario, int idItem) {
		Item i = new Item(descricao, quantidade, tags, ehNecessario, idItem,
				"doador: " + this.nome + "/" + this.documento);
		if (this.itens.values().contains(i) && !i.ehNecessario()) {
			Item iExistente = getItemPorDescricao(descricao);
			iExistente.setQuantidade(quantidade);
			return iExistente.getId();
		}
		this.itens.put(i.getId(), i);
		return i.getId();
	}

	/**
	 * Método que exibe um item especifico
	 * 
	 * @param idItem Código de identificação (ID) do item
	 * @return String com o item correspondente aos parametros passados
	 */
	public String exibeItem(int idItem) {
		return this.itens.get(idItem).toString();
	}

	/**
	 * Método responsável por atualizar informações de um item específico
	 * 
	 * @param idItem         Código de identificação (ID) do item
	 * @param novaQuantidade Nova quantidade de itens
	 * @param novasTags      Novas tags que caracterizam o item
	 * @return String do item com as informações atualizadas
	 */
	public String atualizaItem(int idItem, int novaQuantidade, String novasTags) {
		Item i = this.itens.get(idItem);
		if (novaQuantidade > 0)
			i.setQuantidade(novaQuantidade);
		if (novasTags != null && !novasTags.trim().equals(""))
			i.setTags(novasTags);
		return i.toString();
	}

	/**
	 * Método que remove um item do sistema
	 * 
	 * @param idItem Código de identificação (ID) do item
	 */
	public void removeItem(int idItem) {
		this.itens.remove(idItem);
	}

	/**
	 * Método que lista todos os itens cadastrados no sistema
	 * 
	 * @return ArrayList com todos os itens cadastrados no sistema
	 */
	public ArrayList<Item> listaItens() {
		return makeListaItens();
	}

	/**
	 * Método que adiciona um item necessário, fornecendo um ID único para o item
	 * adicionado
	 * 
	 * @param descricao  Descricao do item
	 * @param quantidade Quantidade de itens adicionados
	 * @param tags       Tags que caracterizam o item
	 * @param idItem     Código de identificação (ID) do item a ser adicionado
	 * @return int com o Código de identificação (ID) do item necessário
	 */
	public int adicionaItemNecessario(String descricao, int quantidade, String tags, boolean ehNecessario, int idItem) {
		Item i = new Item(descricao, quantidade, tags, ehNecessario, idItem,
				"Receptor: " + this.nome + "/" + this.documento);
		if (this.itens.values().contains(i) && i.ehNecessario()) {
			Item iExistente = getItemPorDescricao(descricao);
			iExistente.setQuantidade(quantidade);
			return iExistente.getId();
		}
		this.itens.put(i.getId(), i);
		return i.getId();
	}

	/**
	 * Método que retorna um item com o ID referente ao ID passado
	 * 
	 * @param idItem Código de identificação (ID) requerido
	 * @return Item referente ao Código de identificação (ID) passado
	 */
	public Item getItemPorId(int idItem) {
		if (itens.containsKey(idItem))
			return this.itens.get(idItem);
		return null;
	}

	/**
	 * Método que retorna um item com a descrição referente à descrição passada
	 * 
	 * @param idItem Descrição requerida
	 * @return Item referente ao Código de identificação (ID) passado
	 */
	public Item getItemPorDescricao(String descricao) {
		for (Item item : this.itens.values()) {
			if (item.getDescricao().equals(descricao))
				return item;
		}
		return null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis Itens ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que faz uma lista com todos os itens do mapa de itens
	 * 
	 * @return ArrayList de todos os itens que o usuário detem
	 */
	private ArrayList<Item> makeListaItens() {
		ArrayList<Item> listaItens = new ArrayList<>();
		for (Item i : this.itens.values()) {
			listaItens.add(i);
		}
		return listaItens;
	}

}
