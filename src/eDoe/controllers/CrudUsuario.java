package eDoe.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import eDoe.models.Doador;
import eDoe.models.Receptor;
import eDoe.models.Usuario;
import eDoe.utils.Validador;

public class CrudUsuario {

	private Map<String, Usuario> usuarios = new HashMap<>();
	private Set<String> descritores = new HashSet<>();

	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		while (sc.hasNextLine()) {
			String[] dados = sc.nextLine().split(",");
			if (this.usuarios.containsKey(dados[0])) {
				Usuario u = new Receptor(dados[0], dados[1], dados[2], dados[3], dados[4], "receptor");
				this.usuarios.put(dados[0], u);
			}
		}
		sc.close();
	}

	public String adicionarDoador(String id, String nome, String email, String celular, String classe) {
		Validador.validadorAdicionaDoador(id, nome, email, celular, classe, this.usuarios);
		this.usuarios.put(id, new Doador(id, nome, email, celular, classe, "doador"));
		return id;
	}

	public String pesquisaUsuarioPorId(String id) {
		Validador.validadorPesquisaUsuarioPorId(id, this.usuarios);
		Usuario u = this.usuarios.get(id);
		return u.toString();
	}

	public String pesquisaUsuarioPorNome(String nome) {
		Validador.validadorPesquisaUsuarioPorNome(nome, this.usuarios);
		String saida = "";
		for (Usuario u : this.usuarios.values()) {
			if (u.getNome().equals(nome))
				saida = u.toString();
		}
		return saida;
	}

	public void atualizaUsuario(String id, String nome, String email, String celular) {
		Validador.validadorAtualizaUsuario(id, nome, email, celular, this.usuarios);
		Usuario u = this.usuarios.get(id);
		u.setNome(nome);
		u.setEmail(email);
		u.setCelular(celular);
	}

	public void removeUsuario(String id) {
		Validador.validadorRemoveUsuario(id, this.usuarios);
		this.usuarios.remove(id);
	}

	public void adicionarDescritor(String descricao) {
		Validador.validadorAdicionaDescritor(descricao, this.descritores);
		this.descritores.add(descricao);
	}

	public void adicionaItem(String idDoador, String descricao, int quantidade, String tags) {
		Validador.validadorAdicionaItem(idDoador, descricao, quantidade, tags, this.descritores);
		this.usuarios.get(idDoador).adicionaItem(descricao, quantidade, tags);
	}

	public String exibeItem(String idItem, String idDoador) {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizaItemParaDoacao(String idItem, String idDoador, Double quantidade, String tags) {
		// TODO Auto-generated method stub

	}

	public void removeItemParaDoacao(String idItem, String idDoador) {
		// TODO Auto-generated method stub

	}

	public String listaDescritorDeItensParaDoacao() {
		// TODO Auto-generated method stub
		return null;
	}

	public String listaItensParaDoacao() {
		// TODO Auto-generated method stub
		return null;
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}
}
