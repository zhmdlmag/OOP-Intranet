package Comparators;

import Intranet.IOUtils;
import Intranet.TableBuilder;

/**
 * The StudentsComparator class used as a view to select comparator
 * for sorting students.
 */

public class StudentsComparator {
	
	public static String[] sortOptions = new String[] {"Sort", "By name", "By GPA"};
	/**
	 * Default StudentsComparator constructor
	 */
	public StudentsComparator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Returns HelpComparator class as a comparator 
	 * for applying sort operation over students 
	 * @return comparator
	 * @see HelpComparator
	 */
	public static HelpComparator selectSort() {
		TableBuilder.buildOptionsTable(sortOptions);
		
		String option = IOUtils.readArray(sortOptions, "sort");

		HelpComparator comparator = new NameComparator();
		
		if (option == null) return comparator;
		
		boolean decreasing = HelpComparator.selectSortOrder();
		
		switch (option) {
			case "By name" -> comparator = new NameComparator(decreasing);
			case "By GPA" -> comparator = new GpaComparator(decreasing);
		}
		return comparator;
	}
}