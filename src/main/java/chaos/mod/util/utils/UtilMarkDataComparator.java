package chaos.mod.util.utils;

import java.util.Comparator;

public class UtilMarkDataComparator implements Comparator<UtilMarkData> {
	public int compare(UtilMarkData o1, UtilMarkData o2) {
		if (o1.hours == o2.hours && o1.minutes == o2.minutes)
			return 0;
		if (o1.hours > o2.hours)
			return 1;
		if (o1.hours >= o2.hours) {
			if (o1.minutes > o2.minutes)
				return 1;
			if (o1.minutes >= o2.minutes)
				return 1;
		}
		return -1;
	}
}
