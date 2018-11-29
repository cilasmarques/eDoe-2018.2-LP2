package eDoe.utils;

import java.util.Map;

import eDoe.models.Usuario;

public class Validador {

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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

	public static void validadorPesquisaUsuarioPorId(String idUser, Map<String, Usuario> usuarios) {
		validadorParametro(idUser, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!usuarios.containsKey(idUser))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUser + ".");
	}

	public static void validadorPesquisaUsuarioPorNome(String nome, Map<String, Usuario> usuarios) {
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		for (Usuario u : usuarios.values()) {
			if (u.getNome().equals(nome))
				return;
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}

	public static void validadorAtualizaUsuario(String idUser, String nome, String email, String celular,
			Map<String, Usuario> usuarios) {
		validadorParametro(idUser, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		validadorParametro(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		validadorParametro(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		if (!usuarios.containsKey(idUser))
			throw new IllegalArgumentException("Usuario nao existente: " + idUser + ".");
	}

	public static void validadorRemoveUsuario(String idUser, Map<String, Usuario> usuarios) {
		if (usuarios.containsKey(idUser)) {
			usuarios.remove(idUser);
			return;
		}
		throw new IllegalArgumentException("Usuario nao existente: " + idUser + ".");
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static void validadorAdicionaDescritor(String descricao, Map<String, Integer> descritores) {
		validadorParametro(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
		String descricaoMin = descricao.toLowerCase();
		if (descritores.containsKey(descricaoMin))
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricaoMin + ".");
	}

	public static void validadorAdicionaItemDoacao(String descricao, int quantidade) {
		validadorParametro(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
		if (quantidade <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
	}

	public static void validadorExibeItem(Usuario user, int idItem) {
		if (!user.getItens().containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
	}

	public static void verificadorAtualizaItemParaDocao(Usuario user, int idItem, int quantidade) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (!user.getItens().containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		if (quantidade < 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior ou igual a zero.");
	}

	public static void verificadorRemoveItemParaDoacao(Usuario user, int idItem) {
		if (idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (user.getItens().size() == 0)
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		if (!user.getItens().containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static void validadorParametro(String parametro, String saida) {
		if (parametro == null || parametro.trim().equals(""))
			throw new IllegalArgumentException(saida);
	}

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