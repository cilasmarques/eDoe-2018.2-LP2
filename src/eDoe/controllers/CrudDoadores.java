package eDoe.controllers;

import java.util.HashMap;
import java.util.Map;

import eDoe.utils.Validador;

public class CrudDoadores {

	private Validador v = new Validador();
	private Map<?,? > mapa = new HashMap();
	
	public String adicionarDoador(String id, String nome, String email, String celular, String classe) {
		return null;		
	}

	public String pesquisaUsuarioPorId(String id) {
		return null;
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return null;
	}

	public void atualizaUsuario() {
			
	}

	public void removerUsuario() {
			
	}

	public void adicionarDescritor() {
		// TODO Auto-generated method stub
		
	}

	public void adicionaItem() {
		// TODO Auto-generated method stub
		
	}

	public String exibeItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizaItemParaDoacao(String idItem, String idDoador, Double quantidade, String tags) {
		// TODO Auto-generated method stub
		
	}

	public void removeItemParaDoacao(String idItem, String idDoador) {
		// TODO Auto-generated method stub
		
	}

}
