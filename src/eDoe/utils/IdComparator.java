package eDoe.utils;

import java.util.Comparator;

import eDoe.models.Item;

public class IdComparator implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		if(o2.getId() < o1.getId()) return 1;
		return -1;
	}

}

