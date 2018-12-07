package eDoeTests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eDoe.models.Item;

class ItemTest {

	public Item i;
	
	@BeforeEach
	void testItem() {
		this.i = new Item("item de teste", 1, "teste, JUnit", false, 0);
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
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false, 0);
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true, 1);
		assertEquals(i1.ehNecessario(), false);
		assertEquals(i2.ehNecessario(), true);
	}

	@Test
	void testEqualsObject() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false, 0);
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true, 1);
		Item i3 = new Item("item de erro", 1, "teste, JUnit", true, 2);
		Item i4 = new Item("item de erro", 1, "erro, JUnit", true, 3);
		assertEquals(i1.equals(i2), true);
		assertEquals(i1.equals(i3), false);
		assertEquals(i1.equals(i4), false);
		assertEquals(i2.equals(i3), false);
		assertEquals(i3.equals(i4), false);
	}

	@Test
	void testHashCode() {
		Item i1 = new Item("item de teste", 1, "teste, JUnit", false, 0);
		Item i2 = new Item("item de teste", 1, "teste, JUnit", true, 1);
		Item i3 = new Item("item de erro", 1, "teste, JUnit", true, 2);
		int item1 = i1.hashCode();
		int item2 = i2.hashCode();
		int item3 = i3.hashCode();
		assertEquals(item1 != item2, false);
		assertEquals(item3 != item2, true);
	}
}
