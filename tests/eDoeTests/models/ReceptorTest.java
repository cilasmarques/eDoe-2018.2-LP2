package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eDoe.models.Receptor;

class ReceptorTest {

	public Receptor r;

	@BeforeEach
	void testItem() {  
		this.r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
	}

	@Test
	void testReceptor() {
		assertEquals(this.r.toString(), "salic/40030020010, email321@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testGetStatus() {
		assertEquals(this.r.getStatus(), "Receptor");
	}

	@Test
	void testToString() {
		assertEquals(this.r.toString(), "salic/40030020010, email321@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testUsuario() {
		assertEquals(this.r.toString(), "salic/40030020010, email321@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testGetId() {
		assertEquals(this.r.getId(), "40030020010");
	}

	@Test
	void testGetNome() {
		assertEquals(this.r.getId(), "40030020010");
	}

	@Test
	void testSetNome() {
		r.setNome("teste");
		assertEquals(this.r.getNome(), "teste");
	}

	@Test
	void testSetEmail() {
		this.r.setEmail("algumEmailAi@email.com");
		assertEquals(this.r.toString(),
				"salic/40030020010, algumEmailAi@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testSetCelular() {
		this.r.setCelular("(83) 9.1234-5678");
		assertEquals(this.r.toString(), "salic/40030020010, email321@email.com, (83) 9.1234-5678, status: receptor");
	}

	@Test
	void testGetClasse() {
		assertEquals(this.r.getClasse(), "IGREJA");
	}

	@Test
	void testExibeItem() {
		this.r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 87654321);
		assertEquals(this.r.exibeItem(87654321), "87654321 - item de teste, tags: [teste, JUnit], quantidade: 1");
	}

	@Test
	void testAdicionaItemNecessario() {
		this.r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 87654321);
		assertEquals(this.r.getItens().isEmpty(), false);
	}

	@Test
	void testAtualizaItemNecessario() {
		this.r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 87654321);
		this.r.atualizaItem(87654321, 20, "JUnit, teste2");
		assertEquals(this.r.exibeItem(87654321), "87654321 - item de teste, tags: [JUnit, teste2], quantidade: 20");
	}

	@Test
	void testRemoveItemNecessario() {
		this.r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 87654321);
		assertEquals(this.r.getItens().size(), 1);
		this.r.removeItem(87654321);
		assertEquals(this.r.getItens().size(), 0);
	}

	@Test
	void testGetItemPorDescricao() {
		this.r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false, 87654321);
		assertEquals(this.r.getItens().containsValue(r.getItemPorDescricao("item de teste")), true);
	}

}
