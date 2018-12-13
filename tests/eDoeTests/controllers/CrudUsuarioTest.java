package eDoeTests.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

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
	void testLerReceptores() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		assertEquals(this.cd.pesquisaUsuarioPorId("84473712044"),
				"Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor");
	}

	@Test
	void testAdicionarDoador() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testAdicionarDoadorNomeNulo() {
		try {
			this.cd.adicionarDoador("12345678910", null, "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorNomeVazio() {
		try {
			this.cd.adicionarDoador("12345678910", "", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorEmailNulo() {
		try {
			this.cd.adicionarDoador("12345678910", "Cilas", null, "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorEmailVazio() {
		try {
			this.cd.adicionarDoador("12345678910", "Cilas", "", "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorCelularNulo() {
		try {
			this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", null, "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorCelularVazio() {
		try {
			this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorClasseNulo() {
		try {
			this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", null);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorClasseVazia() {
		try {
			this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "");
		} catch (IllegalArgumentException npe) {
		}
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
		try {
			this.cd.pesquisaUsuarioPorId("12345678910");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDescritor() {
		this.cd.adicionarDescritor("descircao teste");
		assertEquals(this.cd.listaDescritorDeItensParaDoacao(), "0 - descircao teste");
	}

	@Test
	void testAdicionarDescritorVazio() {
		try {
			this.cd.adicionarDescritor("");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDescritorNulo() {
		try {
			this.cd.adicionarDescritor(null);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacao() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(this.cd.getUsuarioValido("12345678910", "doador").exibeItem(12345678),
				"12345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorNulo() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			this.cd.adicionaItemParaDoacao("12345678910", null, 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorVazio() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			this.cd.adicionaItemParaDoacao("12345678910", "", 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeZero() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			this.cd.adicionaItemParaDoacao("12345678910", "descricao", 0, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeNegativa() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			this.cd.adicionaItemParaDoacao("12345678910", "descricao", -1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsNula() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, null, 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsVazia() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "", 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testExibeItem() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(this.cd.exibeItem(12345678, "12345678910"),
				"12345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAtualizaItemParaDoacao() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		this.cd.atualizaItemParaDoacao(12345678, "12345678910", 1000, "tags, teste, tags123");
		assertEquals(this.cd.getUsuarioValido("12345678910", "doador").exibeItem(12345678),
				"12345678 - descricao, tags: [tags, teste, tags123], quantidade: 1000");
	}

	@Test
	void testRemoveItemParaDoacao() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		this.cd.removeItemParaDoacao(12345678, "12345678910");
		try {
			this.cd.getUsuarioValido("12345678910", "doador").exibeItem(12345678);
		} catch (NullPointerException e) {
		}
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		this.cd.adicionaItemParaDoacao("12345678910", "teste", 1, "tags, teste", 12345876);
		this.cd.adicionaItemParaDoacao("12345678910", "descricao2", 1, "tags, teste", 12345678);
		assertEquals(this.cd.listaDescritorDeItensParaDoacao(), "1 - descricao | 1 - descricao2 | 1 - teste");
	}

	@Test
	void testListaItensParaDoacao() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10105678);
		this.cd.adicionaItemParaDoacao("12345678910", "teste", 1, "tags2, teste2", 12345678);
		assertEquals(this.cd.listaItensParaDoacao(),
				"10105678 - descricao, tags: [tags, teste], quantidade: 1, doador: Cilas/12345678910 | 12345678 - teste, tags: [tags2, teste2], quantidade: 1, doador: Cilas/12345678910");
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		assertEquals(this.cd.pesquisaItemParaDoacaoPorDescricao("descricao"),
				"10345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAdicionaItemNecessario() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		assertEquals(this.cd.getUsuarioValido("84473712044", "Receptor").exibeItem(12345690),
				"12345690 - descricao, tags: [tags2, teste2], quantidade: 1");
	}

	@Test
	void testAtualizaItemNecessario() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		this.cd.atualizaItemNecessario("84473712044", 12345690, 1000, "tags3");
		assertEquals(this.cd.getUsuarioValido("84473712044", "Receptor").exibeItem(12345690),
				"12345690 - descricao, tags: [tags3], quantidade: 1000");
	}

	@Test
	void testListaItensNecessarios() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		this.cd.adicionaItemNecessario("84473712044", "teste", 1, "tags2, teste2", 12345692);
		assertEquals(this.cd.listaItensNecessarios(),
				"12345690 - descricao, tags: [tags2, teste2], quantidade: 1, Receptor: Murilo Luiz Brito/84473712044 | 12345692 - teste, tags: [tags2, teste2], quantidade: 1, Receptor: Murilo Luiz Brito/84473712044");
	}

	@Test
	void testRemoveItemNecessario() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		this.cd.removeItemNecessario("84473712044", 12345690);
		assertEquals(this.cd.listaItensNecessarios(), "");
	}

	@Test
	void testMatch() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		assertEquals(this.cd.match("84473712044", 12345690),
				"10345678 - descricao, tags: [tags, teste], quantidade: 1, doador: Cilas/12345678910");
	}

	@Test
	void testRealizaDoacao() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		assertEquals(this.cd.realizaDoacao(12345690, 10345678, "11/11/11"),
				"11/11/11 - doador: Cilas/12345678910, item: descricao, quantidade: 1, receptor: Murilo Luiz Brito/84473712044");
	}

	@Test
	void testListaDoacoes() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags2, teste2", 12345690);
		this.cd.realizaDoacao(12345690, 10345678, "11/11/11");
		assertEquals(this.cd.listaDoacoes(),
				"11/11/11 - doador: Cilas/12345678910, item: descricao, quantidade: 1, receptor: Murilo Luiz Brito/84473712044");
	}

	@Test
	void testGetUsuarioValido() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.getUsuarios().containsValue(cd.getUsuarioValido("12345678910", "doador")), true);
	}

	@Test
	void testGetUsuarios() {
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(this.cd.getUsuarios().containsValue(cd.getUsuarioValido("12345678910", "doador")), true);
	}

	@Test
	void testGetDoacoesRealizadas() throws IOException {
		this.cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		this.cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		this.cd.adicionaItemNecessario("84473712044", "descricao", 1, "tags, teste2", 12345690);
		this.cd.realizaDoacao(12345690, 10345678, "11/11/11");
		ArrayList<String> doacoesrealizadas = new ArrayList<>();
		doacoesrealizadas.add(
				"11/11/11 - doador: Cilas/12345678910, item: descricao, quantidade: 1, receptor: Murilo Luiz Brito/84473712044");
		assertEquals(this.cd.getDoacoesRealizadas(), doacoesrealizadas);
	}

}
