package Comparators;
import Intranet.User;

public class NameComparator extends HelpComparator
{
	public NameComparator() {
		// TODO Auto-generated constructor stub
	}
	
	public NameComparator(boolean desc) {
		super(desc);
	}
	
	@Override
	public int compare(User user, User user2) {
		int nameComparisonResult = user.getFirstname().compareTo(user2.getFirstname());
		if (nameComparisonResult == 0) {
			return result * user.getLastname().compareTo(user2.getLastname());
		}
		return result * nameComparisonResult;	
	}
	
	@Override
	public String toString() {
		return String.format("(name: %s)", isDecreasing());
	}
}