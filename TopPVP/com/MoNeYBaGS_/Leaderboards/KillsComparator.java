package TopPVP.com.MoNeYBaGS_.Leaderboards;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("rawtypes")
class KillsComparator implements Comparator {

	Map base;
	public KillsComparator(Map base) {
		this.base = base;
	}

	public int compare(Object a, Object b) {
		if((Integer)base.get(a) != null)
		{
			if((Integer)base.get(a) < (Integer)base.get(b)) {
				return 1;
			} else if((Integer)base.get(a) == (Integer)base.get(b)) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}
}