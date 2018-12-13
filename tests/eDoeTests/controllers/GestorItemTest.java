package eDoeTests.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eDoe.controllers.CrudUsuario;
import eDoe.controllers.GestorItem;
import eDoe.models.Usuario;

class GestorItemTest {

	public GestorItem gi;

	@BeforeEach
	void testItem() {
		gi = new GestorItem();
	}

	@Test
	void testAdicionarDescritor() {
		gi.adicionarDescritor("descricao teste");
		assertEquals(gi.getDescritores().containsKey("descricao teste"), true);
	}

	@Test
	void testAdicionarDescritorNulo() {
		try {
			gi.adicionarDescritor(null);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionarDescritorVazio() {
		try {
			gi.adicionarDescritor("");
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		Usuario user = cd.getUsuarioValido("12345678910", "doador");
		gi.adicionaItemParaDoacao(user, "descricao", 1, "tags, teste", 12345678);
		assertEquals(user.getItens().containsKey(12345678), true);
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorNulo() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), null, 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoDescritorVazio() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "", 1, "tags, teste", 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoQuantidadeZero() {
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
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", 1, null, 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testAdicionaItemParaDoacaoTagsVazia() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		try {
			gi.adicionaItemParaDoacao(cd.getUsuarioValido("12345678910", "doador"), "descricao", 1, "", 12345678);
		} catch (NullPointerException npe) {
		}
	}

	@Test
	void testExibeItem() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(gi.exibeItem(cd.getUsuarioValido("12345678910", "doador"), 12345678),
				"12345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAtualizaItemParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		Usuario user = cd.getUsuarioValido("12345678910", "doador");
		gi.atualizaItemParaDoacao(user, 12345678, 2, "tags, teste");
		assertEquals(gi.exibeItem(user, 12345678), "12345678 - descricao, tags: [tags, teste], quantidade: 2");
	}

	@Test
	void testRemoveItemParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		Usuario user = cd.getUsuarioValido("12345678910", "doador");
		gi.adicionarDescritor("descricao");
		gi.removeItemParaDoacao(user, 12345678);
		try {
			gi.exibeItem(user, 12345678);
		} catch (IllegalArgumentException npe) {
		}
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 12345678);
		assertEquals(gi.listaDescritorDeItensParaDoacao(), "");
	}

	@Test
	void testListaTodosOsItensExistentes() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionarDoador("12345678923", "Brenno", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		cd.adicionaItemParaDoacao("12345678923", "descricao", 1, "tags, teste", 12345678);
		assertEquals(gi.listaTodosOsItensParaDoacao(cd.getUsuarios()),
				"10345678 - descricao, tags: [tags, teste], quantidade: 1, doador: Cilas/12345678910 | 12345678 - descricao, tags: [tags, teste], quantidade: 1, doador: Brenno/12345678923");
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		assertEquals(gi.pesquisaItemParaDoacaoPorDescricao("descricao", cd.getUsuarios()),
				"10345678 - descricao, tags: [tags, teste], quantidade: 1");
	}

	@Test
	void testAdicionaItemNecessario() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		assertEquals(user.getItens().containsKey(12345690), true);
	}

	@Test
	void testAtualizaNecessario() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		gi.atualizaItemParaDoacao(user, 12345690, 2, "tags, teste");
		assertEquals(gi.exibeItem(user, 12345690), "12345690 - descricao, tags: [tags, teste], quantidade: 2");
	}

	@Test
	void testListaItensNecessarios() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		gi.adicionaItemNecessario(user, "teste", 1, "tags", 12345695);
		assertEquals(gi.listaItensNecessarios(cd.getUsuarios()), "12345690 - descricao, tags: [tags], quantidade: 1, Receptor: Murilo Luiz Brito/84473712044 | 12345695 - teste, tags: [tags], quantidade: 1, Receptor: Murilo Luiz Brito/84473712044");
	}

	@Test
	void testRemoveItemNecessario() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		gi.removeItemNecessario(user, 12345690);
		assertEquals(gi.listaItensNecessarios(cd.getUsuarios()),"");
	}

	@Test
	void testMatch() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		assertEquals(gi.match(user, 12345690, cd.getUsuarios()), "10345678 - descricao, tags: [tags, teste], quantidade: 1, doador: Cilas/12345678910");
	}

	@Test
	void testGetItensParaRealizarDoacao() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		assertEquals(gi.getItensParaRealizarDoacao(12345690, 10345678, cd.getUsuarios()), "[12345690 - descricao, tags: [tags], quantidade: 1, 10345678 - descricao, tags: [tags, teste], quantidade: 1]");
	}

	@Test
	void testGetNumItensDoados() throws IOException {
		CrudUsuario cd = new CrudUsuario();
		cd.lerReceptores("arquivos_sistema/novosReceptores.csv");
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		Usuario user = cd.getUsuarioValido("84473712044", "Receptor");
		Usuario doador = cd.getUsuarioValido("12345678910", "doador");
		gi.adicionaItemNecessario(user, "descricao", 1, "tags", 12345690);
		assertEquals(gi.getNumItensDoados(doador.getItemPorId(10345678), user.getItemPorId(12345690)), 1);
	}

	@Test
	void testGetDescritores() {
		CrudUsuario cd = new CrudUsuario();
		cd.adicionarDoador("12345678910", "Cilas", "meuemail@gmail.com", "(83) 9.9999-0000", "IGREJA");
		cd.adicionaItemParaDoacao("12345678910", "descricao", 1, "tags, teste", 10345678);
		System.out.println(gi.getDescritores().containsKey("descricao"));
	}

	@Test
	void testCarregaDescritores() {
		fail("Not yet implemented");
	}

}
