package eDoe.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import eDoe.comparators.DoacaoComparator;
import eDoe.models.Doador;
import eDoe.models.Item;
import eDoe.models.Receptor;
import eDoe.models.Usuario;
import eDoe.utils.Ferramentas;
import eDoe.utils.Validador;

public class CrudUsuario {

	private Map<String, Usuario> usuarios;
	private ArrayList<String> doacoesRealizadas;
	private GestorItem g;

	public CrudUsuario() {
		this.usuarios = new LinkedHashMap<String, Usuario>();
		this.g = new GestorItem();
		this.doacoesRealizadas = new ArrayList<>();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		while (sc.hasNextLine()) {
			String[] dados = sc.nextLine().split(",");
			if (!this.usuarios.containsKey(dados[0])) {
				Usuario u = new Receptor(dados[0], dados[1], dados[2], dados[3], dados[4], "Receptor");
				this.usuarios.put(dados[0], u);
			} else {
				this.usuarios.get(dados[0]).setNome(dados[1]);
				this.usuarios.get(dados[0]).setEmail(dados[2]);
				this.usuarios.get(dados[0]).setCelular(dados[3]);
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
		ArrayList<String> suporte = new ArrayList<>();
		for (Usuario u : this.usuarios.values()) {
			if (u.getNome().equals(nome))
				suporte.add(u.toString());
		}
		return Ferramentas.arrayToString(suporte);
	}

	public String atualizaUsuario(String idUsuario, String nome, String email, String celular) {
		Usuario u = getUsuarioValido(idUsuario, "doador");
		if (nome != null && !nome.trim().equals("")) {
			u.setNome(nome);
		}
		if (email != null && !email.trim().equals("")) {
			u.setEmail(email);
		}
		if (celular != null && !celular.trim().equals("")) {
			u.setCelular(celular);
		}
		return u.toString();
	}

	public void removeUsuario(String id) {
		Validador.validadorParametro(id, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		Validador.validadorRemoveUsuario(id, this.usuarios);
		this.usuarios.remove(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void adicionarDescritor(String descricao) {
		this.g.adicionarDescritor(descricao);
	}

	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags, int idItem) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		return this.g.adicionaItemParaDoacao(u, descricao, quantidade, tags, idItem);
	}

	public String exibeItem(int idItem, String idDoador) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		return this.g.exibeItem(u, idItem);
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador, int novaQuantidade, String novasTags) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		return this.g.atualizaItemParaDoacao(u, idItem, novaQuantidade, novasTags);
	}

	public void removeItemParaDoacao(int idItem, String idDoador) {
		Usuario u = getUsuarioValido(idDoador, "doador");
		this.g.removeItemParaDoacao(u, idItem);
	}

	public String listaDescritorDeItensParaDoacao() {
		return this.g.listaDescritorDeItensParaDoacao();
	}

	public String listaItensParaDoacao() {
		return this.g.listaTodosOsItensExistentes(this.usuarios);
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		Validador.validadorParametro(descricao, "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		return this.g.pesquisaItemParaDoacaoPorDescricao(descricao, this.usuarios);
	}

	public int adicionaItemNecessario(String idReceptor, String descricao, int quantidade, String tags, int idItem) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		return this.g.adicionaItemNecessario(u, descricao, quantidade, tags, idItem);
	}

	public String atualizaItemNecessario(String idReceptor, int idItem, int novaQuantidade, String novasTags) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		return this.g.atualizaNecessario(u, idItem, novaQuantidade, novasTags);
	}

	public String listaItensNecessarios() {
		return this.g.listaItensNecessarios(this.usuarios);
	}

	public void removeItemNecessario(String idReceptor, int idItem) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		this.g.removeItemNecessario(u, idItem);
	}

	public String match(String idReceptor, int idItemNecessario) {
		Usuario u = getUsuarioValido(idReceptor, "Receptor");
		return this.g.match(u, idItemNecessario, this.usuarios);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Doacao ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		ArrayList<Item> itens = this.g.getItensParaRealizarDoacao(idItemNecessario, idItemDoado, data, this.usuarios);

		Validador.verificadorRealizaDoacao(itens, idItemNecessario, idItemDoado, data);
		String saida = makeComprovanteDoacao(itens.get(0), itens.get(1), data);

		this.doacoesRealizadas.add(saida);
		atualizaItensAposDoacao(itens.get(0), itens.get(1));
		
		return saida;
	}

	public String listaDoacoes() {
		Collections.sort(this.doacoesRealizadas, new DoacaoComparator());
		return Ferramentas.arrayToString(this.doacoesRealizadas);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private void atualizaItensAposDoacao(Item itemNecessario, Item itemDoado) {
		String idReceptor = itemNecessario.getDadosDoEmissor().split("/")[1];
		String idDoador = itemDoado.getDadosDoEmissor().split("/")[1];

		if (itemNecessario.getQuantidade() == 0) {
			removeItemNecessario(idReceptor, itemNecessario.getId());
		}
		if (itemDoado.getQuantidade() == 0) {
			removeItemParaDoacao(itemDoado.getId(), idDoador);
		}
	}

	private String makeComprovanteDoacao(Item itemNecessario, Item itemDoado, String data) {
		return data + " - " + itemDoado.getDadosDoEmissor() + ", item: " + itemNecessario.getDescricao()
				+ ", quantidade: " + this.g.getNumItensDoados(itemNecessario, itemDoado) + ", "
				+ itemNecessario.getDadosDoEmissor().replaceFirst("R", "r");
	}

	public Usuario getUsuarioValido(String idUsuario, String status) {
		Validador.validadorParametro(idUsuario, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!this.usuarios.containsKey(idUsuario))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		if (!usuarios.get(idUsuario).getStatus().equals(status))
			throw new IllegalArgumentException("O Usuario deve ser um " + status.toLowerCase() + ": " + idUsuario + ".");
		return this.usuarios.get(idUsuario);
	}

}