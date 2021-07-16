package Tester;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import Comparators.GpaComparator;
import Comparators.HelpComparator;
import Comparators.NameComparator;
import Enums.Attestation;
import Enums.Faculty;
import Enums.PeriodOfStudy;
import Intranet.Course;
import Intranet.IOUtils;
import Intranet.Student;
import Intranet.User;

public class Test3 {
	public static void main(String[] args) {
		Student student = new Student("Kambar", "Zhamauov", "kz", Faculty.FIT);
		Student student3 = new Student("Almas", "Alemarov", "algo", Faculty.FIT);
		Student student4 = new Student("Amir", "Almabekov", "tao", Faculty.FIT);
		
		Vector<Student> students = new Vector<Student>();
		students.add(student);
		students.add(student3);
		students.add(student4);
		
		Course course7 = new Course("Calculus 1", "MATH2103", 3, 5, Faculty.SMC);
		
		student3.getTranscript().addCourse(course7, PeriodOfStudy.FIRST_SPRING);
		student4.getTranscript().addCourse(course7, PeriodOfStudy.FIRST_SPRING);
		student.getTranscript().addCourse(course7, PeriodOfStudy.FIRST_SPRING);
		
		IOUtils.writer.println(student3.getTranscript().getCourses());
		IOUtils.writer.println(student4.getTranscript().getCourses());
		
		student3.getTranscript().setMark(course7, Attestation.FIRST, 30.0);
		student3.getTranscript().setMark(course7, Attestation.SECOND, 30.0);
		student3.getTranscript().setMark(course7, Attestation.FINAL, 40.0);
		
		student.getTranscript().setMark(course7, Attestation.FIRST, 20.0);
		student.getTranscript().setMark(course7, Attestation.SECOND, 20.0);
		student.getTranscript().setMark(course7, Attestation.FINAL, 30.0);
		
		student4.getTranscript().setMark(course7, Attestation.FIRST, 25.0);
		student4.getTranscript().setMark(course7, Attestation.SECOND, 25.0);
		student4.getTranscript().setMark(course7, Attestation.FINAL, 35.0);
		
		HelpComparator comparator = new NameComparator();
		boolean descendingOreder = true;
		
		while (true) {
			Collections.sort(students, comparator);
			IOUtils.writer.println(students);
			
			
			String option2 = IOUtils.input.nextLine();
			
			
			if (option2.equals("1")) {
				descendingOreder  = true;
			} else {
				descendingOreder  = false;
			}
			
			String option = IOUtils.input.nextLine();
			
			if (option.equals("1")) {
				comparator = new GpaComparator(descendingOreder );
			} else {
				comparator = new NameComparator(descendingOreder );
			}
		}		
	}
	
}
