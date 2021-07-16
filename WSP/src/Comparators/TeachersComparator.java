package Comparators;

import Intranet.IOUtils;
import Intranet.TableBuilder;

public class TeachersComparator {
	
	public static String[] sortOptions = new String[] {"Sort", "By name", "By rating"};
	
	public TeachersComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public static HelpComparator selectSort() {
		TableBuilder.buildOptionsTable(sortOptions);
		String option = IOUtils.readArray(sortOptions, "sort");

		HelpComparator comparator = new NameComparator();
		
		if (option == null) return comparator;
		
		boolean decreasing = HelpComparator.selectSortOrder();
		
		switch (option) {
			case "By name" -> comparator = new NameComparator(decreasing);
			case "By rating" -> comparator = new RatingComparator(decreasing);
		}
		return comparator;
	}
}