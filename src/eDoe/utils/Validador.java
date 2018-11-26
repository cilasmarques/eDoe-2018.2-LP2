package eDoe.utils;

import java.util.Map;
import java.util.Set;

import eDoe.models.Usuario;

public class Validador {

	public static final String ln = System.lineSeparator();

	public static void validadorParametro(String parametro, String saida) {
		if (parametro == null || parametro.trim().equals(""))
			throw new IllegalArgumentException(saida);
	}

	public static void validadorClasse(String classe) {
		// Array[] classesUsuarios = new
		// Array["PESSOA_FISICA","IGREJA","ORGAO_PUBLICO_MUNICIPAL","ORGAO_PUBLICO_ESTADUAL","ORGAO_PUBLICO_FEDERAL","ONG","ASSOCIACAO","SOCIEDADE"];
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

	public static void validadorAdicionaDoador(String id, String nome, String email, String celular, String classe,
			Map<String, Usuario> usuarios) {
		if (usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		validadorParametro(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		validadorParametro(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		validadorParametro(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		validadorParametro(classe, "Entrada invalida: classe nao pode ser vazia ou nula.");
		validadorClasse(classe);
	}

	public static void validadorPesquisaUsuarioPorId(String id, Map<String, Usuario> usuarios) {
		validadorParametro(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");

	}

	public static void validadorPesquisaUsuarioPorNome(String nome, Map<String, Usuario> usuarios) {
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		for (Usuario u : usuarios.values()) {
			if (u.getNome().equals(nome))
				return;
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}

	public static void validadorAtualizaUsuario(String id, String nome, String email, String celular,
			Map<String, Usuario> usuarios) {
		validadorParametro(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		validadorParametro(nome, "Entrada invalida: nome nao pode ser vazio ou nulo.");
		validadorParametro(email, "Entrada invalida: email nao pode ser vazio ou nulo.");
		validadorParametro(celular, "Entrada invalida: celular nao pode ser vazio ou nulo.");
		if (!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao existente: " + id + ".");

	}

	public static void validadorRemoveUsuario(String id, Map<String, Usuario> usuarios) {
		if (usuarios.containsKey(id)) {
			usuarios.remove(id);
			return;
		}
		throw new IllegalArgumentException("Usuario nao existente: " + id + ".");

	}

	public static void validadorAdicionaDescritor(String descricao, Set<String> descritores) {
		validadorParametro(descricao, "Entrada invalida: descricao nao pode ser vazia ou nula.");
		if (descritores.contains(descricao))
			throw new IllegalArgumentException("Usuario ja existente: " + descricao + ".");
	}
}
