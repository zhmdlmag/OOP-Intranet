package Comparators ;
import Intranet.Student;

public class GpaComparator extends HelpComparator
{
	public GpaComparator() {
		
	}
	public GpaComparator(boolean desc) {
		super(desc);
	}
	public int compare(Student student, Student student2) {
		if (student.getTranscript().getTotalGpa() < student2.getTranscript().getTotalGpa() ) {
			return -result;
		} else if (student.getTranscript().getTotalGpa() > student2.getTranscript().getTotalGpa() ) {
			return result;
		}
		return 0;	
	}
	@Override
	public String toString() {
		return String.format("(GPA: %s)", isDecreasing());
	}
}