package com.MoNeYBaGS_.Leaderboards;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class KDRComparator implements Comparator{

	Map base;
	public KDRComparator(Map base) {
		this.base = base;
	}

	public int compare(Object a, Object b) {
		if((Double)base.get(a) != null)
		{
			if((Double)base.get(a) < (Double)base.get(b)) {
				return 1;
			} else if((Double)base.get(a) == (Double)base.get(b)) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}

}
