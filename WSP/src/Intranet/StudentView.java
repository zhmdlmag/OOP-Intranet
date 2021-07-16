package Intranet;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Enums.Action;
import Enums.Faculty;
import Enums.State;
import Interfaces.CanViewNews;
import Interfaces.CanViewRequests;
import Interfaces.CanViewResponses;

public class StudentView extends UserView implements CanViewNews, CanViewResponses {
	
	private static String[] studentViewOptions = new String[] {"Options", "View transcript", "View courses",
			"View teachers", "View schedule", "View news", "View responses",
			"Order book", "Registrate for courses", "Logout"};
	
	private static String[] viewCoursesOptions = new String[] {"Options", "View course files", "Go back"};
	
	private static String[] viewTeachersOptions = new String[] {"Options", "Rate teacher", "View teacher courses", "Go back"};
	
	private static String[] registrateForCoursesOptions = new String[] {"Options", "Select course", "Remove course", "View selected courses", "Send request", "Go back"};

	private Student student = null;
	
	public StudentView() { }
	
	public StudentView(User user) {
		this.student = (Student) user;
	}
	
	public void run() {
		boolean running = true;
		while (running) {
			TableBuilder.buildOptionsTable(studentViewOptions);
			
			String option = IOUtils.readArray(studentViewOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View transcript" -> viewTranscript();
				case "View courses" -> manageCourses();
				case "View teachers" -> manageTeachersView();
				case "View schedule" -> viewSchedule();
				case "View news" -> viewNews();
				case "Order book" -> orderBook();
				case "View responses" -> viewResponses(student);
				case "Registrate for courses" -> registrateForCourses();
				case "Logout" -> running = false;
			}
		}
	}
	
	public void viewTranscript() {
		Transcript transcript = student.getTranscript();
		TableBuilder.buildTable(transcript.extractData(), "Transcript");
		IOUtils.waitKey();
	}
	
	public void viewCourses() {
		Vector<Course> courses = student.getTranscript().getCourses();
		TableBuilder.buildTable(TableBuilder.courseHeaders, courses, "Students' courses");
	}
	
	public void manageCourses() {
		boolean running = true;
		
		while (running) {
			viewCourses();
			TableBuilder.buildOptionsTable(viewCoursesOptions);
			
			String option = IOUtils.readArray(viewCoursesOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View course files" -> manageCourseFiles();
				case "Go back" -> running = false;
			}
		}
	}

	public void viewCourseFiles(Vector<CourseFile> courseFiles) {
		TableBuilder.buildTable(TableBuilder.courseFileHeaders, courseFiles, "Course files");
		IOUtils.waitKey();
	}
	
	public void manageCourseFiles() {
		Vector<Course> courses = student.getTranscript().getCourses();
		Course course = IOUtils.read(courses, "course to see course files");
		if (course != null) viewCourseFiles(course.getCourseFiles());;
	}
	
	public void rateTeacher() {
		Teacher teacher = IOUtils.read(DataBase.getInstance().getTeachers(), "teacher");
		if (teacher != null) teacher.updateRating();
		DataBase.getInstance().addLogs(new Log(Action.RATE_TEACHER, student));
	}
	
	public void viewTeacherCourses() {
		Teacher teacher = IOUtils.read(DataBase.getInstance().getTeachers(), "teacher");
		if (teacher != null) {
			TableBuilder.buildTable(TableBuilder.courseHeaders, teacher.getCourses(), "Teachers' courses");
			IOUtils.waitKey();
		}
	}
	
	public void viewTeachers() {
		TableBuilder.buildTable(TableBuilder.teacherHeaders, DataBase.getInstance().getTeachers(), "Teachers");
	}
	
	public void manageTeachersView() {
		boolean running = true;
		
		while (running) {
			viewTeachers();
			TableBuilder.buildOptionsTable(viewTeachersOptions);
			
			String option = IOUtils.readArray(viewTeachersOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Rate teacher" -> rateTeacher();
				case "View teacher courses" -> viewTeacherCourses();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewSchedule() {
		TableBuilder.buildTable(TableBuilder.lessonHeaders, student.getLessons(), "Students' schedule");
		IOUtils.waitKey();
	}
	
	public void orderBook() {
		Vector<Book> books = DataBase.getInstance().getBooks();
		
		TableBuilder.buildTable(TableBuilder.bookHeaders, books, "Available books on site");
		
		Book book = IOUtils.read(books, "book");
		
		if (book != null) student.orederBook(book);
		DataBase.getInstance().addLogs(new Log(Action.ORDER_BOOK, student));
	}

	public void registrateForCourses() {
		Vector<Course> coursesForRegistration = new Vector<Course>();
		boolean running = true;
		while (running) {
			viewRequiredCourses();
			viewCoursesForRegistration();
			
			String option = IOUtils.readArray(registrateForCoursesOptions, "option");
			
			switch (option) {
				case "Select course" -> selectCourse(coursesForRegistration);
				case "Remove selected course" -> removeSelectedCourse(coursesForRegistration);
				case "View selected courses" -> viewSelectedCourses(coursesForRegistration);
				case "Send request" -> {
					student.registerForCourses(coursesForRegistration);
					running = false;
				}
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewRequiredCourses() {
		TableBuilder.buildTable(TableBuilder.courseHeaders, DataBase.getInstance().getRegistrationRequirements(student.getNextPeriodOfStudy(), student.getFaculty()).getRequiredCourses(), "All required courses for " + student.getNextPeriodOfStudy());
	}
	
	public void viewCoursesForRegistration() {
		TableBuilder.buildTable(TableBuilder.courseHeaders, DataBase.getInstance().getCourses(), "Courses for registration");	
		TableBuilder.buildOptionsTable(registrateForCoursesOptions);
	}
	
	public void viewSelectedCourses(Vector<Course> coursesForRegistration) {
		TableBuilder.buildTable(TableBuilder.courseHeaders, coursesForRegistration);
		IOUtils.waitKey();
	}
	
	public void removeSelectedCourse(Vector<Course> coursesForRegistration) {
		TableBuilder.buildTable(TableBuilder.courseHeaders, coursesForRegistration);
		Course course = IOUtils.read(coursesForRegistration, "course");
		coursesForRegistration.remove(course);
	}
	
	public void selectCourse(Vector<Course> coursesForRegistration) {
		Course course = IOUtils.read(DataBase.getInstance().getCourses(), "course");
	 	if (course != null && !coursesForRegistration.contains(course)) coursesForRegistration.add(course);
	}
}
