package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

public class IdComparator implements Comparator<Item_eDoe> {

	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		if(o2.getId() < o1.getId()) return 1;
		return -1;
	}

}

