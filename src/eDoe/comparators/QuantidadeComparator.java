package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item;

public class QuantidadeComparator implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		if(o1.getQuantidade() == o2.getQuantidade()) {
			return o1.getDescricao().compareTo(o2.getDescricao());
		}
		if(o1.getQuantidade() > o2.getQuantidade()) return -1;
		return 1;
	}

}
