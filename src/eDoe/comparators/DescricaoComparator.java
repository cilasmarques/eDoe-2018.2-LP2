package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item;

public class DescricaoComparator implements Comparator<Item>{
	
	@Override
	public int compare(Item o1, Item o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}

}
