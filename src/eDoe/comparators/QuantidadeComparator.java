package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

public class QuantidadeComparator implements Comparator<Item_eDoe>{

	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		if(o1.getQuantidade() == o2.getQuantidade()) {
			return o1.getDescricao().compareTo(o2.getDescricao());
		}
		if(o1.getQuantidade() > o2.getQuantidade()) return -1;
		return 1;
	}

}
