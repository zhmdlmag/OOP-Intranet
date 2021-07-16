package Comparators ;
import Intranet.Teacher;

public class RatingComparator extends HelpComparator
{
	public RatingComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public RatingComparator(boolean desc){
		super(desc);
	}

	public int compare(Teacher teacher, Teacher teacher2) {
		if (teacher.getRating() < teacher2.getRating()) {
			return -result;
		} else if (teacher.getRating() > teacher2.getRating()) {
			return result;
		}
		return 0;	
	}
	
	@Override
	public String toString() {
		return String.format("(rating: %s)", isDecreasing());
	}
}