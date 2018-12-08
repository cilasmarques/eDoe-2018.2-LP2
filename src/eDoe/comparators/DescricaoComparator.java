package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

public class DescricaoComparator implements Comparator<Item_eDoe> {

	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		return o1.getDescricao().compareTo(o2.getDescricao());
	}

}
