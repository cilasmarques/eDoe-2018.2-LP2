package eDoe.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import eDoe.models.Doador;
import eDoe.models.Item;
import eDoe.models.Receptor;
import eDoe.models.Usuario;
import eDoe.utils.Ferramentas;
import eDoe.utils.Validador;

public class CrudUsuario {

	private Map<String, Usuario> usuarios = new LinkedHashMap<String, Usuario>();
	private Map<String, Integer> descritores = new LinkedHashMap<>();

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Usuario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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
		ArrayList<String> suporte = new ArrayList<>();
		String saida = "";
		for (Usuario u : this.usuarios.values()) {
			if (u.getNome().equals(nome))
				suporte.add(u.toString()) ;
		}
		for (int i = 0 ; i < suporte.size()-1; i++ ) {
			saida += suporte.get(i) + " | ";
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
	
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Item ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void adicionarDescritor(String descricao) {
		Validador.validadorAdicionaDescritor(descricao, this.descritores);
		this.descritores.put(descricao.toLowerCase().trim(), 0);
	}

	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		Validador.validadorAdicionaItem(idDoador, descricao, quantidade, this.usuarios);

		String descrMin = descricao.toLowerCase().trim();
		Usuario u = this.usuarios.get(idDoador);

		if (u.getStatus().equals("doador")) {
			this.descritores.put(descrMin, quantidade);
			return u.adicionaItemParaDoacao(descricao, quantidade, tags, false);

		} else if (u.getStatus().equals("receptor")) {
			return u.adicionaItemParaDoacao(descrMin, quantidade, tags, true);
		}
		return -1;
	}

	public String exibeItem(int idItem, String idDoador) {
		Validador.validadorExibeItem(idItem, idDoador, this.usuarios);
		return this.usuarios.get(idDoador).exibeItem(idItem);
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		Validador.verificadorAtualizaItemParaDocao(idItem, idDoador, quantidade, this.usuarios);
		String saida = this.usuarios.get(idDoador).atualizaItemParaDoacao(idItem, quantidade, tags);
		atualizaDescritores(idItem, idDoador, "atualizaItem");
		return saida;
	}

	public void removeItemParaDoacao(int idItem, String idDoador) {
		Validador.verificadorRemoveItemParaDoacao(idItem, idDoador, this.usuarios);
		atualizaDescritores(idItem, idDoador, "removeItem");
		this.usuarios.get(idDoador).removeItemParaDoacao(idItem);
	}

	public String listaDescritorDeItensParaDoacao() {
		return Ferramentas.arrayToString(makeListaDescritores());
	}

	public String listaItensParaDoacao() {
		ArrayList<Item> arrayItens = new ArrayList<>();
		for (Usuario u : this.usuarios.values()) {
			if (!u.listaItens().isEmpty() && u != null)
				arrayItens.addAll(u.listaItens());
		}
		Collections.sort(arrayItens);
		return Ferramentas.arrayToString(arrayPutInfosDoador(arrayItens));
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		// TODO Auto-generated method stub
		return null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Uteis ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	private void atualizaDescritores(int idItem, String idDoador, String string) {
		String descrMin = this.usuarios.get(idDoador).getItens().get(idItem).getDescricao();
		int quantItem = this.usuarios.get(idDoador).getItens().get(idItem).getQuantidade();

		if (string.equals("atualizaItem"))
			this.descritores.put(descrMin, quantItem);

		else if (string.equals("removeItem")) {
			int quantItensTotal = this.descritores.get(descrMin) - quantItem;
			this.descritores.put(descrMin, quantItensTotal);
		}
	}

	private ArrayList<String> makeListaDescritores() {
		ArrayList<String> array = new ArrayList<>();
		for (String key : this.descritores.keySet()) {
			array.add(key);
		}
		Collections.sort(array);
		return arrayPutInfosDescritor(array);
	}

	private ArrayList<String> arrayPutInfosDescritor(ArrayList<String> arrayDescritores) {
		ArrayList<String> arrayDeInfos = new ArrayList<>();
		for (String key : arrayDescritores) {
			int quantidade = this.descritores.get(key);
			arrayDeInfos.add(quantidade + " - " + key);
		}
		return arrayDeInfos;
	}

	private ArrayList<String> arrayPutInfosDoador(ArrayList<Item> arrayItens) {
		ArrayList<String> arrayDeInfos = new ArrayList<>();
		for (Item i : arrayItens) {
			for (Usuario u : this.usuarios.values()) {
				if (u.getItens().containsKey(i.getId()))
					arrayDeInfos.add(i.toString() + ", doador: " + u.getNome() + "/" + u.getId());
			}
		}
		return arrayDeInfos;
	}
	
}
