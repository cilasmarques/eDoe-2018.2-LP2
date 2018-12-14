package eDoeTest.comparators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eDoe.comparators.QuantidadeComparator;
import eDoe.models.Item;

class QuantidadeComparatorTest {

	@Test
	void testCompare() {
		QuantidadeComparator qc = new QuantidadeComparator();
		Item i1 = new Item("descricao", 1, "tags", true, 12345690, "Receptor: Murilo Luiz Brito/84473712044");
		Item i2 = new Item("descricao", 2, "tags, teste", false, 10345678, "Doador: Cilas/12345678910");
		assertEquals(qc.compare(i1, i2), 1);
		assertEquals(qc.compare(i2, i1), -1);
	}

}
