package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.models.Receptor;
import eDoe.models.Usuario;

class ReceptorTest {

	@Test
	void testReceptor() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.toString(), "salic/40030020010, email321@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testGetStatus() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.getStatus(), "Receptor");
	}

	@Test
	void testToString() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.toString(), "salic/40030020010, email321@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testUsuario() {
		Usuario r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.toString(), "salic/40030020010, email321@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testGetId() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.getId(), "40030020010");
	}

	@Test
	void testGetNome() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.getId(), "40030020010");
	}

	@Test
	void testSetNome() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.setNome("teste");
		assertEquals(r.getNome(), "teste");
	}

	@Test
	void testSetEmail() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.setEmail("algumEmailAi@email.com");
		assertEquals(r.toString(), "salic/40030020010, algumEmailAi@email.com, (38) 9.0000-9999, status: receptor");
	}

	@Test
	void testSetCelular() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.setCelular("(83) 9.1234-5678");
		assertEquals(r.toString(), "salic/40030020010, email321@email.com, (83) 9.1234-5678, status: receptor");
	}

	@Test
	void testGetClasse() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		assertEquals(r.getClasse(), "IGREJA");
	}
	
	@Test
	void testExibeItem() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		int idItem = r.getItemPorDescricao("item de teste").getId();
		String saida = r.exibeItem(idItem);
		assertEquals(saida, idItem + " - item de teste, tags: [teste,  JUnit], quantidade: 1");
	}

	@Test
	void testAdicionaItemNecessario() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.adicionaItemNecessario("item de teste", 1, "Teste, JUnit", false);
		assertEquals(r.getItens().isEmpty(), false);
	}

	@Test
	void testAtualizaItemNecessario() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		int idItem = r.getItemPorDescricao("item de teste").getId();
		r.atualizaItemNecessario(idItem, 20, "JUnit, teste2");
		String saida = r.exibeItem(idItem);
		assertEquals(saida, idItem + " - item de teste, tags: [JUnit,  teste2], quantidade: 20");
	}

	@Test
	void testRemoveItemNecessario() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		int idItem = r.getItemPorDescricao("item de teste").getId();
		assertEquals(r.getItens().size(), 1);
		r.removeItemNecessario(idItem);
		assertEquals(r.getItens().size(), 0);
	}

	@Test
	void testGetItemPorDescricao() {
		Receptor r = new Receptor("40030020010", "salic", "email321@email.com", "(38) 9.0000-9999", "IGREJA", "Receptor");
		r.adicionaItemParaDoacao("item de teste", 1, "teste, JUnit", false);
		assertEquals(r.getItens().containsValue(r.getItemPorDescricao("item de teste")), true);
	}

}
