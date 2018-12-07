package eDoeTests.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eDoe.controllers.CrudUsuario;

class CrudUsuarioTest {

	public CrudUsuario cd;

	@BeforeEach
	void testItem() {
		this.cd = new CrudUsuario();
	}

	@Test
	void testAdicionarDoador() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testPesquisaUsuarioPorId() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.pesquisaUsuarioPorNome("Cilas"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testAtualizaUsuario() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
		this.cd.atualizaUsuario("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.0000-99999");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.0000-99999, status: doador");
		this.cd.atualizaUsuario("12345678910", "Cilas", "meuemail@gmail.com", "");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.0000-99999, status: doador");
		this.cd.atualizaUsuario("12345678910", "Cilas", "", "(83) 9.9999-0000");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
		this.cd.atualizaUsuario("12345678910", "Cilas", "outroemail@gmail.com", "(83) 9.9999-0000");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, outroemail@gmail.com, (83) 9.9999-0000, status: doador");
		this.cd.atualizaUsuario("12345678910", "Cilas", "outroemail@gmail.com", "(83) 9.0000-9999");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, outroemail@gmail.com, (83) 9.0000-9999, status: doador");
	}

	@Test
	void testRemoveUsuario() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
		this.cd.removeUsuario("12345678910");
		assertThrows(IllegalArgumentException.class, () -> {
			this.cd.pesquisaUsuarioPorId("12345678910");
		});
	}

	@Test
	void testAdicionarDescritorVazio() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.cd.adicionarDescritor("");
		});
	}

	@Test
	void testAdicionarDescritorNulo() {

		assertThrows(IllegalArgumentException.class, () -> {
			this.cd.adicionarDescritor(null);
		});
	}

}
