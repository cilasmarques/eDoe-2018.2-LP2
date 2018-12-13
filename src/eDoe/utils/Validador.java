package eDoe.utils;

import java.util.ArrayList;
import java.util.Map;

import eDoe.models.Item;
import eDoe.models.Usuario;

public class Validador {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que valida a adição de um usuário doador no sistema
	 * 
	 * @param idUSer   Documento de identificação do doador
	 * @param nome     Nome do doador
	 * @param email    Email do doador
	 * @param celular  Celular do doador
	 * @param classe   Classe do doador
	 * @param usuarios Mapa com os usuarios cadastrados no sistema
	 */
	public static void validadorAdicionaDoador(String idUser, String nome, String email, String celular, String classe,
			Map<String, Usuario> usuarios) {
		if (usuarios.containsKey(idUser))
			throw new IllegalArgumentException("Usuario ja existente: " + idUser + ".");
		validadorParametro(idUser, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		validadorParametro(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		validadorParametro(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		validadorParametro(classe, "Entrada invalida: classe nao pode ser vazia ou nula.");
		validadorClasse(classe);
	}

	/**
	 * Método que valida a pesquisa de um usuario pelo documento de identificação no
	 * sistema
	 * 
	 * @param idUSer   Documento de identificação do doador
	 * @param usuarios Mapa com os usuarios cadastrados no sistema
	 */
	public static void validadorPesquisaUsuarioPorId(String idUser, Map<String, Usuario> usuarios) {
		validadorParametro(idUser, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!usuarios.containsKey(idUser))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUser + ".");
	}

	/**
	 * Método que valida a pesquisa de um usuario pelo documento de identificação no
	 * sistema
	 * 
	 * @param nome     Nome do usuário
	 * @param usuarios Mapa com os usuarios cadastrados no sistema
	 */

	public static void validadorPesquisaUsuarioPorNome(String nome, Map<String, Usuario> usuarios) {
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		for (Usuario u : usuarios.values()) {
			if (u.getNome().equals(nome))
				return;
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}

	/**
	 * Método que valida a remoção de um usuario no sistema
	 * 
	 * @param idUSer   Documento de identificação do doador
	 * @param usuarios Mapa com todos os usuarios cadastrados no sistema
	 */
	public static void validadorRemoveUsuario(String idUser, Map<String, Usuario> usuarios) {
		if (!usuarios.containsKey(idUser))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUser + ".");
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que valida a adição de um descritor
	 * 
	 * @param descritor   Descritor a ser analisado
	 * @param descritores Mapa com todos os descritores cadastrados no sistema
	 */
	public static void validadorAdicionaDescritor(String descritor, Map<String, Integer> descritores) {
		validadorParametro(descritor, "Entrada invalida: descricao nao pode ser vazia ou nula.");
		String descricaoMin = descritor.toLowerCase();
		if (descritores.containsKey(descricaoMin))
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricaoMin + ".");
	}

	/**
	 * Método que valida a adição de um item
	 * 
	 * @param descricao  Descrição do item
	 * @param quantidade Quantidade do item
	 */
	public static void validadorAdicionaItem(String descricao, int quantidade) {
		validadorParametro(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
		if (quantidade <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
	}

	/**
	 * Método que valida a exibição de um item
	 * 
	 * @param user   Usuário dono do item
	 * @param idItem código de identificação (ID) do item requerido
	 */
	public static void validadorExibeItem(Usuario user, int idItem) {
		if (!user.getItens().containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
	}

	/**
	 * Método que valida a atualização de um item para doação
	 * 
	 * @param user       Usuário dono do item
	 * @param idItem     código de identificação (ID) do item requerido
	 * @param quantidade nova Quantidade do item
	 */
	public static void verificadorAtualizaItemParaDocao(Usuario user, int idItem, int quantidade) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (!user.getItens().containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		if (quantidade < 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior ou igual a zero.");
	}

	/**
	 * Método que valida a atualização de um item necessário
	 * 
	 * @param user   Usuário dono do item
	 * @param idItem código de identificação (ID) do item requerido
	 */
	public static void verificadorAtualizaItemNecessario(Usuario user, int idItem) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (!user.getItens().containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}

	/**
	 * Método que valida a remoção de um item no sistema
	 * 
	 * @param user   Usuário dono do item
	 * @param idItem código de identificação (ID) do item requerido
	 */
	public static void verificadorRemoveItem(Usuario user, int idItem) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (user.getItens().size() == 0)
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		if (!user.getItens().containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}

	/**
	 * Método que valida a o item necessário passado para dar match
	 * 
	 * @param user   Usuário dono do item
	 * @param idItem código de identificação (ID) do item necessário
	 */
	public static void verificadorMatchItem(Usuario user, int idItem) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (user.getItens().size() == 0)
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		if (!user.getItens().containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}

	/**
	 * Método que valida a realização de uma doação
	 * 
	 * @param itens            ArrayList com os dois itens requeridos no processo de
	 *                         doação
	 * @param idItemNecessario Código de identificação (ID) do item necessário
	 * @param idItemDoado      Código de identificação (ID) do item para doar
	 * @param data             data de ocorrencia da transação
	 */
	public static void verificadorRealizaDoacao(ArrayList<Item> itens, int idItemNecessario, int idItemDoado,
			String data) {
		validadorParametro(data, "Entrada invalida: data nao pode ser vazia ou nula.");
		if (idItemNecessario < 0 || idItemDoado < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (!itens.get(0).ehNecessario())
			throw new IllegalArgumentException("Item nao encontrado: " + idItemNecessario + ".");
		if (itens.size() != 2)
			throw new IllegalArgumentException("Item nao encontrado: " + idItemDoado + ".");
		if (!itens.get(0).getDescricao().equals(itens.get(1).getDescricao()))
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * Método que verifica se um parâmetro qualquer é nulo ou vazio
	 * 
	 * @param parametro Parâmetro a ser analisado
	 * @param saida     Mensagem caso o parametro seja vazio ou nulo
	 */
	public static void validadorParametro(String parametro, String saida) {
		if (parametro == null || parametro.trim().equals(""))
			throw new IllegalArgumentException(saida);
	}

	/**
	 * Método que valida a classe a qual o usuário pertence
	 * 
	 * @param classe Classe que o usuario pertence
	 */
	public static void validadorClasse(String classe) {
		switch (classe) {
		case "PESSOA_FISICA":
			return;
		case "IGREJA":
			return;
		case "ORGAO_PUBLICO_MUNICIPAL":
			return;
		case "ORGAO_PUBLICO_ESTADUAL":
			return;
		case "ORGAO_PUBLICO_FEDERAL":
			return;
		case "ONG":
			return;
		case "ASSOCIACAO":
			return;
		case "SOCIEDADE":
			return;
		default:
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
	}

}