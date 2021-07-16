package Comparators;

import java.util.Comparator;
import Intranet.IOUtils;
import Intranet.TableBuilder;
import Intranet.User;

public abstract class HelpComparator implements Comparator<User> {
	public static String[] sortOptions = new String[] {"Order", "Increasing", "Decreasing"};
	
	public static boolean selectSortOrder() {
		TableBuilder.buildOptionsTable(sortOptions);
		
		String option = IOUtils.readArray(sortOptions, "order");
		
		boolean decreasing = false;
		
		if (option == null) return decreasing;
		
		switch (option) {
			case "Increasing" -> decreasing = false;
			case "Decreasing" -> decreasing = true;
		}
		return decreasing;
	}
	
	public Integer result = -1;

	public HelpComparator() {

	}
	
	public HelpComparator(boolean desc) {
		if (desc == true) result = 1;
	}
	
	public int compare(User o1, User o2) {
		return 0;
	};
	
	public String isDecreasing() {
		if (result == 1) {
			return "decreasing";
		}
		return "increasing";
	}
}