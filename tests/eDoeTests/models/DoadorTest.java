package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.models.Doador;
import eDoe.models.Usuario;

class DoadorTest {

	@Test
	void testDoador() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.toString(), "cilas/10020030040, email123@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testGetStatus() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.getStatus(), "doador");
	}

	@Test
	void testToString() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.toString(), "cilas/10020030040, email123@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testUsuario() {
		Usuario d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.toString(), "cilas/10020030040, email123@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testGetId() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.getId(), "10020030040");
	}

	@Test
	void testGetNome() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.getId(), "10020030040");
	}

	@Test
	void testSetNome() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.setNome("teste");
		assertEquals(d.getNome(), "teste");
	}

	@Test
	void testSetEmail() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.setEmail("algumEmailAi@email.com");
		assertEquals(d.toString(), "cilas/10020030040, algumEmailAi@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testSetCelular() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.setCelular("(83) 9.1234-5678");
		assertEquals(d.toString(), "cilas/10020030040, email123@email.com, (83) 9.1234-5678, status: doador");
	}

	@Test
	void testGetClasse() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		assertEquals(d.getClasse(), "IGREJA");
	}

	@Test
	void testAdicionaItemParaDoacao() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.adicionaItemParaDoacao("item de teste", 1, "Teste, JUnit", false);
		assertEquals(d.getItens().isEmpty(), false);
	}

	@Test
	void testExibeItem() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		int idItem = d.getItemPorDescricao("item de teste").getId();
		String saida = d.exibeItem(idItem);
		assertEquals(saida, idItem + " - item de teste, tags: [teste,  JUnit], quantidade: 1");
	}

	@Test
	void testAtualizaItemParaDoacao() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		int idItem = d.getItemPorDescricao("item de teste").getId();
		d.atualizaItemParaDoacao(idItem, 20, "JUnit, teste2");
		String saida = d.exibeItem(idItem);
		assertEquals(saida, idItem + " - item de teste, tags: [JUnit,  teste2], quantidade: 20");
	}

	@Test
	void testRemoveItemParaDoacao() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		int idItem = d.getItemPorDescricao("item de teste").getId();
		assertEquals(d.getItens().size(), 1);
		d.removeItemParaDoacao(idItem);
		assertEquals(d.getItens().size(), 0);
	}

	@Test
	void testGetItemPorDescricao() {
		Doador d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
		d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		assertEquals(d.getItens().containsValue(d.getItemPorDescricao("item de teste")), true);
	}

}
