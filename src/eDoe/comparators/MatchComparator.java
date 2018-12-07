package eDoe.comparators;

import java.util.Comparator;

import eDoe.models.Item_eDoe;

public class MatchComparator implements Comparator<Item_eDoe> {

	@Override
	public int compare(Item_eDoe o1, Item_eDoe o2) {
		if (o1.getPontuacao() == o2.getPontuacao()) {
			if (o1.getId() > o2.getId())
				return 1;
			return -1;
		}
		if (o1.getPontuacao() > o2.getPontuacao())
			return -1;
		return 1;
	}

}

