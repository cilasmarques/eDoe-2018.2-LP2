package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eDoe.models.Doador;

class DoadorTest {

	public Doador d;

	@BeforeEach
	void testItem() {
		this.d = new Doador("10020030040", "cilas", "email123@email.com", "(83) 9.9999-0000", "IGREJA", "doador");
	}

	@Test
	void testDoador() {
		assertEquals(this.d.toString(), "cilas/10020030040, email123@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testGetStatus() {
		assertEquals(this.d.getStatus(), "doador");
	}

	@Test
	void testToString() {
		assertEquals(this.d.toString(), "cilas/10020030040, email123@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testUsuario() {
		assertEquals(this.d.toString(), "cilas/10020030040, email123@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testGetId() {
		assertEquals(this.d.getId(), "10020030040");
	}

	@Test
	void testGetNome() {
		assertEquals(this.d.getId(), "10020030040");
	}

	@Test
	void testSetNome() {
		this.d.setNome("teste");
		assertEquals(this.d.getNome(), "teste");
	}

	@Test
	void testSetEmail() {
		this.d.setEmail("algumEmailAi@email.com");
		assertEquals(this.d.toString(), "cilas/10020030040, algumEmailAi@email.com, (83) 9.9999-0000, status: doador");
	}

	@Test
	void testSetCelular() {
		this.d.setCelular("(83) 9.1234-5678");
		assertEquals(this.d.toString(), "cilas/10020030040, email123@email.com, (83) 9.1234-5678, status: doador");
	}

	@Test
	void testGetClasse() {
		assertEquals(this.d.getClasse(), "IGREJA");
	}

	@Test
	void testAdicionaItemParaDoacao() {
		this.d.adicionaItemParaDoacao("item de teste", 1, "Teste, JUnit", false, 12345678);
		assertEquals(this.d.getItens().isEmpty(), false);
	}

	@Test
	void testExibeItem() {
		this.d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 12345678);
		assertEquals(this.d.exibeItem(12345678), "12345678 - item de teste, tags: [teste, JUnit], quantidade: 1");
	}

	@Test
	void testAtualizaItemParaDoacao() {
		this.d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 12345678);
		this.d.atualizaItem(12345678, 20, "JUnit, teste2");
		assertEquals(this.d.exibeItem(12345678), "12345678 - item de teste, tags: [JUnit, teste2], quantidade: 20");
	}

	@Test
	void testRemoveItemParaDoacao() {
		this.d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 12345678);
		assertEquals(this.d.getItens().size(), 1);
		this.d.removeItem(12345678);
		assertEquals(this.d.getItens().size(), 0);
	}

	@Test
	void testGetItemPorDescricao() {
		this.d.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 12345678);
		assertEquals(this.d.getItens().containsValue(d.getItemPorDescricao("item de teste")), true);
	}

}