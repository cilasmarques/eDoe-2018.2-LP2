package eDoeTests.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.controllers.CrudUsuario;

class CrudUsuarioTest {

	@Test
	void testAdicionarDoador() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testPesquisaUsuarioPorId() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(cd.pesquisaUsuarioPorNome("Cilas"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testAtualizaUsuario() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
		cd.atualizaUsuario("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.0000-99999");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.0000-99999, status: doador");
		cd.atualizaUsuario("12345678910", "Cilas", "meuemail@gmail.com", "");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.0000-99999, status: doador");
		cd.atualizaUsuario("12345678910", "Cilas", "", "(83) 9.9999-0000");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
		cd.atualizaUsuario("12345678910", "Cilas", "outroemail@gmail.com", "(83) 9.9999-0000");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, outroemail@gmail.com, (83) 9.9999-0000, status: doador");
		cd.atualizaUsuario("12345678910", "Cilas", "outroemail@gmail.com", "(83) 9.0000-9999");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, outroemail@gmail.com, (83) 9.0000-9999, status: doador");
	}

	@Test
	void testRemoveUsuario() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
		cd.removeUsuario("12345678910");
		try {
			cd.pesquisaUsuarioPorId("12345678910");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDescritorVazio() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDescritor("");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDescritorNulo() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDescritor(null);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionaItemParaDoacao("12345678910", "item de teste", 1, "teste, JUnit");
	}

}
