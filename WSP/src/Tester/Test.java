package Tester;

import java.util.Date;
import java.util.Vector;

import Enums.Attestation;
import Enums.Day;
import Enums.Faculty;
import Enums.LessonForm;
import Enums.NewsType;
import Enums.PeriodOfStudy;
import Intranet.*;

public class Test {
	public static void main(String[] args) {
		
//		User user = 		  new Admin("Sys", "Admin2", "admin");
//		User user2 =          new Admin("Sys2", "Admin22", "admin2");
//		Student student =     new Student("Kambar", "Zhamauov", "kz", Faculty.FIT);
//		Student student2 =    new Student("Dias", "Dogalakov", "DD", Faculty.FIT);
//		Student student3 =    new Student("Almas", "Alemarov", "algo", Faculty.FIT);
//		Student student4 =    new Student("Amir", "Almabekov", "tao", Faculty.FIT);
//		Student student5 =    new Student("Magzhan", "Zhumadilov", "maga", Faculty.FIT);
//		Teacher teacher =     new Teacher("Pakita", "Shamoi", "oop");
//		Teacher teacher2 =    new Teacher("Alimzhan", "Amanov", "acm");
//		Librarian librarian = new Librarian("Name", "Surname", "books");
//		Manager manager =     new Manager("Manager", "Fit", "fit");
//		dataBase.addUser(user);
//		dataBase.addUser(user2);
//		dataBase.addUser(librarian);
//		dataBase.addUser(teacher);
//		dataBase.addUser(teacher2);
//		dataBase.addUser(student);
//		dataBase.addUser(student2);
//		dataBase.addUser(student3);
//		dataBase.addUser(student4);
//		dataBase.addUser(student5);
//		dataBase.addUser(manager);
//		
//		Course course =  new Course("OOP", "CSCI1221", 3, 5, Faculty.FIT, "The main objective of the course "
//				+ "is to develop an understanding of the principles underpinning object oriented programming "
//				+ "and apply object base approaches using java proramming language.");
//		Course course2 = new Course("ADS", "CSCI1222", 4, 7, Faculty.FIT, "This course is designed to teach efficient use of"
//				+ "data structures and algorithms to solve problems."
//				+ "Students study the logical relationship between"
//				+ "data structures associated with a problem and the"
//				+ "physical representation.");
//		Course course3 = new Course("DB", "CSCI1223", 3, 5, Faculty.FIT, "This course aims to basic priciples of databases, "
//				+ "design and development, databases as part of an entire information system, "
//				+ "proficiency in basic database development.");
//		Course course4 = new Course("ICT", "CSCI1224", 4, 7, Faculty.FIT, "The course is designed to help the students "
//				+ "develop their IT competence for their current academic studies. "
//				+ "The course is focused on learning Operating System(MS Windows), "
//				+ "software products of MS Office and provides opportunities for building up "
//				+ "own Interactive Multimedia Presentations.");
//		
//		Course course8 = new Course("CV", "CSCI3240", 3, 5, Faculty.FIT, "The course is about extraction "
//				+ "of useful information from images by using basic computer vision algorithms "
//				+ "such as feature detection, motion estimation, tracking, etc.");
//		
//		course.setTeacher(teacher);
//		course2.setTeacher(teacher2);
//		course3.setTeacher(teacher);
//		course4.setTeacher(teacher2);
//		dataBase.addCourse(course);
//		dataBase.addCourse(course2);
//		dataBase.addCourse(course3);
//		dataBase.addCourse(course4);
//
//		Vector<Course> courses = dataBase.getCourses();
//		RegistrationRequirements registrationRequirements = new RegistrationRequirements(courses, 21, 0, 0);
//		dataBase.addRegestrationRequirements(PeriodOfStudy.THIRD_FALL, Faculty.FIT, registrationRequirements);
//		
//		dataBase.addCourse(course8);
//		
//		IOUtils.writer.println(student5.getNextPeriodOfStudy());
//		
//		IOUtils.writer.println(dataBase.getRegistrationRequirements(PeriodOfStudy.THIRD_FALL, Faculty.FIT).getRequiredCourses());
//		
//		course.addCourseFile(new CourseFile("OOP and Design", "All about OOP concepts"));
//		course2.addCourseFile(new CourseFile("Knuth", "Most efficient algorithms"));
//		
//		student.getTranscript().addCourse(course, PeriodOfStudy.SECOND_SPRING);
//		student.getTranscript().addCourse(course2, PeriodOfStudy.SECOND_FALL);
//		student3.getTranscript().addCourse(course, PeriodOfStudy.SECOND_SPRING);
//		student3.getTranscript().addCourse(course2, PeriodOfStudy.SECOND_FALL);
//		student4.getTranscript().addCourse(course, PeriodOfStudy.SECOND_SPRING);
//		student4.getTranscript().addCourse(course2, PeriodOfStudy.SECOND_FALL);
//		student2.getTranscript().addCourse(course2, PeriodOfStudy.FIRST_SPRING);
//		
//		student3.getTranscript().setMark(course, Attestation.FIRST, 30.0);
//		student3.getTranscript().setMark(course, Attestation.SECOND, 30.0);
//		student3.getTranscript().setMark(course, Attestation.FINAL, 40.0);
//		
//		student.getTranscript().setMark(course, Attestation.FIRST, 20.0);
//		student.getTranscript().setMark(course, Attestation.SECOND, 20.0);
//		student.getTranscript().setMark(course, Attestation.FINAL, 30.0);
//		
//		student4.getTranscript().setMark(course, Attestation.FIRST, 25.0);
//		student4.getTranscript().setMark(course, Attestation.SECOND, 25.0);
//		student4.getTranscript().setMark(course, Attestation.FINAL, 35.0);
//		
//		student3.getTranscript().setMark(course2, Attestation.FIRST, 30.0);
//		student3.getTranscript().setMark(course2, Attestation.SECOND, 30.0);
//		student3.getTranscript().setMark(course2, Attestation.FINAL, 40.0);
//		
//		student.getTranscript().setMark(course2, Attestation.FIRST,   20.0);
//		student.getTranscript().setMark(course2, Attestation.SECOND,  20.0);
//		student.getTranscript().setMark(course2, Attestation.FINAL,   30.0);
//		
//		student4.getTranscript().setMark(course2, Attestation.FIRST,  25.0);
//		student4.getTranscript().setMark(course2, Attestation.SECOND, 25.0);
//		student4.getTranscript().setMark(course2, Attestation.FINAL,  35.0);
//		
//		IOUtils.writer.println(dataBase.getStudents(course2));
//		
//		dataBase.addLesson(new Lesson(LessonForm.LECTURE, 8, 10, course, Day.TUESDAY));
//		dataBase.addLesson(new Lesson(LessonForm.PRACTICE, 10, 11, course, Day.TUESDAY));
//		dataBase.addLesson(new Lesson(LessonForm.LECTURE, 13, 15, course2, Day.FRIDAY));
//		dataBase.addLesson(new Lesson(LessonForm.PRACTICE, 15, 16, course2, Day.SATURDAY));
//		
//		Book book = new Book("The Master and Margarita", "Long story", "Mikhail Bulgakov");
//		Book book2 = new Book("Concrete Mathematics", "Some algorithms described", "Donald Knuth");
//		Book book3 = new Book("Tom Sawyer & Huckleberry Finn", "Long story", "Mark Twain");
//		Book book4 = new Book("Probability and Statistics", "Statistic theorems", "Morris H. DeGroot");
//		dataBase.addBook(book);
//		dataBase.addBook(book2);
//		dataBase.addBook(book3);
//		dataBase.addBook(book4);
//		
//		Message message = new Message("About books", teacher2, librarian, "I need more books");
//		dataBase.addMessage(message);
//		
//		OrderBookRequest order = new OrderBookRequest(student2, book);
//		dataBase.addRequest(order);
//		
//		News news = new News("About acm competitions", NewsType.INFORMATION, "Next month KBTU organize KBTU OPEN");
//		dataBase.addNews(news);
//		
//		IOUtils.writer.println(dataBase.getUsers());
		
//		IOUtils.writer.println(c);
		
//		Admin admin = new Admin("admin", "admin", "admin");
//		DataBase.getInstance().addUser(admin);
//		for (User user : DataBase.getInstance().getUsers()) {
//			IOUtils.writer.println(user.getLogin() + " " + user.getPassword());			
//		}
		
//		DataBase dataBase = DataBase.getInstance();
//		Course course =  new Course("Algortihms", "CSCI1003", 3, 5, Faculty.FIT, "About algo", 2, 1, 0);
//		
//		Teacher teacher = new Teacher("Misha", "Hicenko", "mishka");
//		
//		Student student = new Student("Pakita", "Shamoi", "oop", Faculty.FIT);
//		
//		dataBase.addUser(student);
//		
//		dataBase.addUser(teacher);
//		
//		course.setTeacher(teacher);
//		
//		dataBase.addCourse(course);
//		
//		RegistrationRequirements registrationRequirements2 = new RegistrationRequirements(new Vector<Course>(), 21, 2, 2);
//		dataBase.addRegestrationRequirements(PeriodOfStudy.SECOND_SPRING, Faculty.FIT, registrationRequirements2);
//		
//		RegistrationRequirements registrationRequirements3 = new RegistrationRequirements(new Vector<Course>(), 21, 1, 1);
//		dataBase.addRegestrationRequirements(PeriodOfStudy.THIRD_FALL, Faculty.FIT, registrationRequirements3);
//		
//		RegistrationRequirements registrationRequirements4 = new RegistrationRequirements(new Vector<Course>(), 21, 1, 1);
//		dataBase.addRegestrationRequirements(PeriodOfStudy.THIRD_SPRING, Faculty.FIT, registrationRequirements4);
		
		DataBase.getInstance().addUser(new Admin("admin", "admin", "admin"));
		
		DataBase.getInstance().addUser(new Manager("manager", "fit", "fit"));
		
		Application application = new Application();
		
		boolean running = true; 
		while (running) {
			application.run();			
		}
	}
}