package eDoeTests.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.controllers.CrudUsuario;
import eDoe.controllers.GestorItem;

class GestorItemTest {

	@Test
	void testAdicionarDescritor() {
		GestorItem gi = new GestorItem();
		gi.adicionarDescritor("descricao teste");
		assertEquals(gi.getDescritores().containsKey("descricao teste"), true);
	}

	@Test
	void testAdicionarDescritorNulo() {
		GestorItem gi = new GestorItem();
		try {
			gi.adicionarDescritor(null);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDescritorVazio() {
		GestorItem gi = new GestorItem();
		try {
			gi.adicionarDescritor("");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacao() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", 1, "tags, teste",
				12345678);
		assertEquals(gi.getDescritores().containsKey("descricao"), true);
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorNulo() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), null, 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorVazio() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "", 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeZero() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", 0, "tags, teste",
					12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeNegativa() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", -1, "tags, teste",
					12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsNula() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", 1, null, 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsVazia() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", 1, "", 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testExibeItem() {
		GestorItem gi = new GestorItem();
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(gi.exibeItem(cd.getUsuarioValido("12345678910", "doador"), 12345678),
				"12345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAtualizaItemParaDoacao() {
		GestorItem gi = new GestorItem();

	}

	@Test
	void testRemoveItemParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testListaTodosOsItensExistentes() {
		fail("Not yet implemented");
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		fail("Not yet implemented");
	}

	@Test
	void testAdicionaItemNecessario() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaNecessario() {
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

}
