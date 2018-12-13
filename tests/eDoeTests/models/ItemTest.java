package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eDoe.models.Item;

class ItemTest {

	public Item i;

	@BeforeEach
	void testItem() {
		this.i = new Item("item de teste", 1, "teste, JUnit", false, 0, "doador: dono Do mundo/00000000000");
	}

	@Test
	void testGetId() {
		assertEquals(this.i.getId(), 0);
	}

	@Test
	void testGetDescricao() {
		assertEquals(this.i.getDescricao(), "item de teste");
	}

	@Test
	void testGetQuantidade() {
		assertEquals(this.i.getQuantidade(), 1);
	}

	@Test
	void testSetQuantidade() {
		this.i.setQuantidade(20);
		assertEquals(this.i.getQuantidade(), 20);
	}

	@Test
	void testSetTags() {
		this.i.setTags("JUnit, teste2");
		assertEquals(this.i.toString(), "0 - item de teste, tags: [JUnit, teste2], quantidade: 1");
	}

	@Test
	void testToString() {
		assertEquals(this.i.toString(), "0 - item de teste, tags: [teste, JUnit], quantidade: 1");
	}

	@Test
	void testEhNecessario() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false, 0, "doador: dono Do mundo/00000000000");
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true, 1, "doador: dono Do mundo/00000000000");
		assertEquals(i1.ehNecessario(), false);
		assertEquals(i2.ehNecessario(), true);
	}

	@Test
	void testEqualsObject() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false, 0, "doador: dono Do mundo/00000000000");
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true, 1, "doador: dono Do mundo/00000000000");
		Item i3 = new Item("item de erro", 1, "teste, JUnit", true, 2, "doador: dono Do mundo/00000000000");
		Item i4 = new Item("item de erro", 1, "erro, JUnit", true, 3, "doador: dono Do mundo/00000000000");
		assertEquals(i1.equals(i2), true);
		assertEquals(i1.equals(i3), false);
		assertEquals(i1.equals(i4), false);
		assertEquals(i2.equals(i3), false);
		assertEquals(i3.equals(i4), false);
	}

	@Test
	void testHashCode() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false, 0, "doador: dono Do mundo/00000000000");
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true, 1, "doador: dono Do mundo/00000000000");
		Item i3 = new Item("item de erro", 1, "teste, JUnit", true, 2, "doador: dono Do mundo/00000000000");
		int item1 = i1.hashCode();
		int item2 = i2.hashCode();
		int item3 = i3.hashCode();
		assertEquals(item1 != item2, false);
		assertEquals(item3 != item2, true);
	}

	@Test
	void testSetPontuacao() {
		assertEquals(this.i.getPontuacao(), 0);
		this.i.setPontuacao(20);
		assertEquals(this.i.getPontuacao(), 20);
	}

	@Test
	void testGetDadosDoEmissor() {
		assertEquals(this.i.getDadosDoEmissor(), "doador: dono Do mundo/00000000000");
	}

	@Test
	void testGetPontuacao() {
		this.i.setPontuacao(20);
		assertEquals(this.i.getPontuacao(), 20);
	}

	@Test
	void testGetTags() {
		ArrayList<String> tags = new ArrayList<>();
		tags.add("novaTag");
		tags.add("novoTeste");
		this.i.setTags("novaTag, novoTeste");
		assertEquals(this.i.getTags(), tags);

	}

}