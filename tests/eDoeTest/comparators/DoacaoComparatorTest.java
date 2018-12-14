package eDoeTest.comparators;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import eDoe.comparators.DoacaoComparator;

class DoacaoComparatorTest {

	@Test
	void testCompare() throws IOException {
		DoacaoComparator dc = new DoacaoComparator();
		String o1 = "11/11/2013 - doador: Aramis Araujo/49847103331, item: jaqueta de couro, quantidade: 3, receptor: Antonella Sonia Moraes/32719454000103 |";
		String o2 = "12/12/2013 - 15/09/2016 - doador: Lucas Fernandes/13507190272, item: camiseta, quantidade: 100, receptor: Murilo Luiz Brito/84473712044 |";
		assertEquals((dc.compare(o1, o2)), 1);
		assertEquals((dc.compare(o2, o1)), -1);
	}

}
