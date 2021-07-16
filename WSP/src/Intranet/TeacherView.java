package Intranet;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.crypto.Data;

import Enums.Action;
import Enums.Attestation;
import Interfaces.CanViewNews;
import Interfaces.CanViewRequests;

public class TeacherView extends EmployeeView implements CanViewNews, CanViewRequests {

	private static String[] teacherViewOptions = new String[] { "Options", "View courses", "View news", "View requests", "Check mail", "Logout" };
	
	private static String[] viewCoursesOptions = new String[] { "Options", "View course files", "View schedule", "View students", "Go back" };

	private static String[] viewCourseFilesOptions = new String[] { "Options", "Add course file", "Remove course file", "Update course file", "Go back" };
	
	private static String[] manageStudentsOptions = new String[] { "Options", "Put mark", "Go back" };
	
	private Teacher teacher = null;
	
	public TeacherView() {

	}
	
	public TeacherView(User user) {
		super(user);
		this.teacher = (Teacher) user;
	}
	
	public void run() {
		boolean running = true;
		
		while (running) {
			TableBuilder.buildOptionsTable(teacherViewOptions);
			
			String option = IOUtils.readArray(teacherViewOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View courses" -> manageCourses();
				case "Check mail" -> manageMail();
				case "View requests" -> viewRequests();
				case "View news" -> viewNews();
				case "Logout" -> running = false;
			}
		}
	}
	
	public void viewRequests() {
		Vector<Request> requests = DataBase.getInstance().getRequests(teacher);
		viewRequests(teacher);
		
		Request request = IOUtils.read(requests, "request to response");
		
		if (request != null) request.callback();
	}
	
	public void viewCourses() {
		TableBuilder.buildTable(TableBuilder.courseHeaders, teacher.getCourses(), "Teachers' courses");
	}
	
	public void manageCourses() {
		boolean running = true;
		
		while (running) {
			// Display Courses
			viewCourses();
			
			// Display options over Courses
			TableBuilder.buildOptionsTable(viewCoursesOptions);
			
			String option = IOUtils.readArray(viewCoursesOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View course files" -> manageCourseFiles();
				case "View students" -> manageStudents();
				case "View schedule" -> viewSchedule();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewSchedule() {
		TableBuilder.buildTable(TableBuilder.lessonHeaders, teacher.getLessons(), "Teachers' lessons");
		IOUtils.waitKey();
	}
	
	public void viewCourseFiles(Course course) {	
		// Display Course Files
		Vector<CourseFile> courseFiles = course.getCourseFiles();
		TableBuilder.buildTable(TableBuilder.courseFileHeaders, courseFiles, "Course files");
	}
	
	public void manageCourseFiles() {
		viewCourses();
		
		Vector<Course> courses = teacher.getCourses();
		Course course = IOUtils.read(courses, "course");
		
		if (course == null) return;
		
		boolean running = true;
		
		while(running) {
			// Display Course Files
			viewCourseFiles(course);
			
			// Display options over Course Files
			TableBuilder.buildOptionsTable(viewCourseFilesOptions);
			
			String option = IOUtils.readArray(viewCourseFilesOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Add course file" -> addCourseFile(course);
				case "Remove course file" -> removeCourseFile(course);
				case "Update course file" -> updateCourseFile(course);
				case "Go back" -> running = false;
			}
		}
	}
	
	public void addCourseFile(Course course) {
		teacher.addCourseFile(course);
	}
	
	public void removeCourseFile(Course course) {
		Vector<CourseFile> courseFiles = course.getCourseFiles();
		CourseFile courseFile = IOUtils.read(courseFiles, "course file to be removed");
		if (courseFile != null) IOUtils.remove("Course file", teacher.removeCourseFile(course, courseFile));
	}
	
	public void updateCourseFile(Course course) {
		Vector<CourseFile> courseFiles = course.getCourseFiles();
		CourseFile courseFile = IOUtils.read(courseFiles, "course file to be updated");
		if (courseFile != null) teacher.updateCourseFile(course, courseFile);
	}
	
	public void viewStudents(Course course) {
		Vector<Student> students = DataBase.getInstance().getStudents(course);
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(TableBuilder.studentHeaders);
		for (int i = 0; i < students.size(); ++i) {
			tableList.add(students.get(i).extractData(course));			
		}
		TableBuilder.buildTable(tableList, "Students of this course");
	}
	
	public void manageStudents() {
		viewCourses();
		
		Vector<Course> courses = teacher.getCourses();
		Course course = IOUtils.read(courses, "course");
		
		if (course == null) return;
		
		boolean running = true;
		
		while (running) {
			// Display students
			viewStudents(course);		
			
			TableBuilder.buildOptionsTable(manageStudentsOptions);
			
			String option = IOUtils.readArray(manageStudentsOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Put mark" -> putMark(course);
				case "Go back" -> running = false;
			}
		}	
	}
	
	public void putMark(Course course) {
		Vector<Student> students = DataBase.getInstance().getStudents(course);
		Student student = IOUtils.read(students, "student");	
  		if (student != null) {
  			teacher.putMark(course, student);
  			DataBase.getInstance().addLogs(new Log(Action.PUT_MARK, teacher));  			
  		}
	}
}
