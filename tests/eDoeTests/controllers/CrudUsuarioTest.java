package eDoeTests.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import eDoe.controllers.CrudUsuario;
import eDoe.controllers.GestorItem;

class CrudUsuarioTest {

	@Test
	void testLerReceptores() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		assertEquals(cd.pesquisaUsuarioPorId("84473712044"),
				"Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor");
	}

	@Test
	void testAdicionarDoador() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		assertEquals(cd.pesquisaUsuarioPorId("12345678910"),
				"Cilas/12345678910, meuemail@gmail.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testAdicionarDoadorNomeNulo() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", null, "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorNomeVazio() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorEmailNulo() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "Cilas", null, "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorEmailVazio() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "Cilas", "", "(83) 9.9999-0000", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorCelularNulo() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", null, "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorCelularVazio() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "", "IGREJA");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorClasseNulo() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", null);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDoadorClasseVazia() {
		CrudUsuario cd = new CrudUsuario();
		try {
			cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "");
		} catch (IllegalArgumentException npe) {
		}
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
	void testAdicionarDescritor() {
		CrudUsuario cd = new CrudUsuario();
		GestorItem gi = new GestorItem();
		cd.adicionarDescritor("descircao teste");
		assertEquals(gi.getDescritores().containsKey("descricao teste"), true);
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
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste",
				12345678);
		assertEquals(gi.getDescritores().containsKey("descricao"), true);
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorNulo() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			cd.adicionaItemParaDoacao("12345678910", null, 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorVazio() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			cd.adicionaItemParaDoacao("12345678910", "", 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeZero() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			cd.adicionaItemParaDoacao("12345678910", "descricao", 0, "tags, teste",
					12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeNegativa() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			cd.adicionaItemParaDoacao("12345678910", "descricao", -1, "tags, teste",
					12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsNula() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			cd.adicionaItemParaDoacao("12345678910", "descricao", 1, null, 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsVazia() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "", 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testExibeItem() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(cd.exibeItem(12345678, "12345678910"),
				"12345678 - descricao, tags: [tags, teste], quantidade: 1");
		}
	
	@Test
	void testAtualizaItemParaDoacao() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		cd.atualizaItemParaDoacao(12345678, "12345678910", 2, "tags, teste");
		System.out.println(gi.getDescritores().get("descricao").toString());
		assertEquals(gi.getDescritores().get("descricao").toString(), "2");
	}

	@Test
	void testRemoveItemParaDoacao() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		cd.removeItemParaDoacao(12345678, "12345678910");
		assertEquals(gi.getDescritores().containsKey("descricao"), false);
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(cd.listaDescritorDeItensParaDoacao(), "");
	}

	@Test
	void testListaItensParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		System.out.println(cd.listaItensParaDoacao());
		assertEquals(cd.listaItensParaDoacao(),"10345678 - descricao, tags: [tags, teste], quantidade: 1, doador: Cilas/12345678910\n" + 
				"1 - descricao");
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		assertEquals(cd.pesquisaItemParaDoacaoPorDescricao("descricao"), "10345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAdicionaItemNecessario() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaItemNecessario() {
		fail("Not yet implemented");
	}

	@Test
	void testListaItensNecessarios() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveItemNecessario() {
		fail("Not yet implemented");
	}

	@Test
	void testMatch() {
		fail("Not yet implemented");
	}
}
