package eDoeTest.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.comparators.MatchComparator;
import eDoe.models.Item;

class MatchComparatorTest {

	@Test
	void testCompare() {
		Item i1 = new Item("descricao", 1, "tags", true, 12345690, "Receptor: Murilo Luiz Brito/84473712044");
		Item i2 = new Item("descricao2", 2, "tags, teste", false, 10345678, "Doador: Cilas/12345678910");
		MatchComparator mc = new MatchComparator();
		assertEquals(mc.compare(i1, i2), 1);
	}

}
