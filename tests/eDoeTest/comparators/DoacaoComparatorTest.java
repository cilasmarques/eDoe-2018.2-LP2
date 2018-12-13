package eDoeTest.comparators;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import eDoe.comparators.DoacaoComparator;

class DoacaoComparatorTest {

	public DoacaoComparator dc;
	
	@Test
	void testCompare() throws IOException {
		String o1 = "11/11/11";
		String o2 = "12/12/12";
		System.out.println(this.dc.compare(o1, o2));
		assertEquals((this.dc.compare(o1, o2)), "");
	}

}
