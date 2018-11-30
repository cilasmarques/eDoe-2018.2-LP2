package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.models.Item;

class ItemTest {

	@Test
	void testItem() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		assertEquals(i.toString(), "0 - item de teste, tags: [teste,  JUnit], quantidade: 1");
	}

	@Test
	void testSetId() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		i.setId(12345678);
		assertEquals(i.getId(), 12345678);
	}

	@Test
	void testGetId() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		i.setId(12345678);
		assertEquals(i.getId(), 12345678);
	}

	@Test
	void testGetDescricao() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		assertEquals(i.getDescricao(), "item de teste");
	}

	@Test
	void testGetQuantidade() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		assertEquals(i.getQuantidade(), 1);
	}

	@Test
	void testSetQuantidade() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		i.setQuantidade(20);
		assertEquals(i.getQuantidade(), 20);
	}

	@Test
	void testSetTags() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		i.setTags("JUnit, teste2");
		assertEquals(i.toString(), "0 - item de teste, tags: [JUnit,  teste2], quantidade: 1");
	}

	@Test
	void testToString() {
		Item i = new Item("item de teste", 1, "teste, JUnit", false);
		assertEquals(i.toString(), "0 - item de teste, tags: [teste,  JUnit], quantidade: 1");
	}

	@Test
	void testEhNecessario() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false);
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true);
		assertEquals(i1.ehNecessario(), false);
		assertEquals(i2.ehNecessario(), true);
	}

	@Test
	void testEqualsObject() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false);
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true);
		Item i3 = new Item("item de erro", 1, "teste, JUnit", true);
		Item i4 = new Item("item de erro", 1, "erro, JUnit", true);
		assertEquals(i1.equals(i2), true);
		assertEquals(i1.equals(i3), false);
		assertEquals(i1.equals(i4), false);
		assertEquals(i2.equals(i3), false);
		assertEquals(i3.equals(i4), false);
	}

	@Test
	void testHashCode() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false);
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true);
		Item i3 = new Item("item de erro", 1, "teste, JUnit", true);
		int item1 = i1.hashCode();
		int item2 = i2.hashCode();
		int item3 = i3.hashCode();
		assertEquals(item1 != item2, false);
		assertEquals(item3 != item2, true);
		assertEquals(item1 == item1, true);
	}
}
