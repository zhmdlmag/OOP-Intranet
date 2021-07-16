package Tester;

import java.io.File;
import java.util.Date;
import java.util.Vector;

import Enums.Faculty;
import Enums.State;
import Intranet.*;


public class Test2 {
	public static void main(String[] args) {
		Course course = new Course("OOP", "CSCI1221", 3, 5, Faculty.FIT);
		Course course2 = new Course("ADS", "CSCI1222", 4, 7, Faculty.FIT);
		Course course3 = new Course("DB", "CSCI1223", 3, 5, Faculty.FIT);
		Course course4 = new Course("ICT", "CSCI1224", 4, 7, Faculty.FIT);
		Course course5 = new Course("PP1", "CSCI1225", 3, 6, Faculty.FIT);
		Course course6 = new Course("PP2", "CSCI1225", 3, 6, Faculty.FIT);
		
		Course course7 = new Course("OOP", "CSCI1221", 3, 5, Faculty.FIT);

		Vector<Course> courses = new Vector<>();
		courses.add(course);
		

		Vector<Course> courses2 = new Vector<>();
		courses2.add(course);
		courses2.add(course2);
		
		for (Course c : courses2) {
			if (!courses.contains(c)) {
				IOUtils.writer.println("OKKK");
			}
		}
		IOUtils.writer.println("OKKKKKK");
	}
}