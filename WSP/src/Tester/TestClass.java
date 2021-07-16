package Tester;

import java.util.Collections;

import java.util.Comparator;

import Enums.Faculty;
import Enums.PeriodOfStudy;
import Enums.State;
import Exceptions.InvalidMarkException;
import Intranet.Course;
import Intranet.DataBase;
import Intranet.IOUtils;
import Intranet.Student;
import Intranet.TableBuilder;
import Intranet.Transcript;
import Intranet.User;

public class TestClass {

	public static void main(String[] args) {
		TableBuilder.buildTable(TableBuilder.bookHeaders, DataBase.getInstance().getBooks(), "LALLA");
		TableBuilder.buildTable(TableBuilder.courseHeaders, DataBase.getInstance().getCourses(), "LALLA");
		Student student =     new Student("Kambar", "Zhamauov", "kz", Faculty.FIT);
		Course course2 = new Course("ADS", "CSCI1222", 4, 7, Faculty.FIT);
		student.getTranscript().addCourse(course2, PeriodOfStudy.FIRST_FALL);
		Transcript transcript = student.getTranscript();
		TableBuilder.buildTable(transcript.extractData(), "ASDAD");
		TableBuilder.buildTable(TableBuilder.courseFileHeaders, course2.getCourseFiles(), "ASasdd");
//		5 -> Length - 5 = 0
//		6 -> Length - 6 = -1
//		7 -> Length - 7 = -2
//		8 -> Length - 8 = -3
		
	}
}