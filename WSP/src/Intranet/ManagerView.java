package Intranet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import Comparators.HelpComparator;
import Comparators.NameComparator;
import Comparators.StudentsComparator;
import Comparators.TeachersComparator;
import Enums.Action;
import Interfaces.CanViewNews;
import Interfaces.CanViewRequests;
import Interfaces.CanViewResponses;

public class ManagerView extends EmployeeView implements CanViewNews, CanViewRequests, CanViewResponses {

	private static String[] managerViewOptions = new String[] { "Options", "Manage news", "Assign course to teacher", "View responses", 
			"Add course", "Approve students registration", "Create statistical reports on academic performance",
			"View info about students and teachers", "Check mail", "Logout" };
	
	private static String[] manageNewsOptions = new String[] { "Options", "Add news", "Remove news", "Go back" };
	
	private static String[] viewStudentsAndTeachersOptions = new String[] { "Options", "View students", "View teachers", "Go back" };
	
	private static String[] manageStudentsAndTeachersViewsOptions = new String[] {"Options", "Apply sorting", "Go back" };
	
	public Manager manager = null;
	
	public  ManagerView(User user) {
		super(user);
		this.manager = (Manager) user;
	}
	
	public void run() {
		boolean running = true;
		
		while (running) {
			TableBuilder.buildOptionsTable(managerViewOptions);
			
			String option = IOUtils.readArray(managerViewOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Manage news" -> manageNews();
				case "Assign course to teacher" -> assignCourseToTeacher();
				case "Add course" -> addCourse();
				case "View responses" -> viewResponses(manager); 
				case "Approve students registration" -> approveStudentsRegestration();
				case "Create statistical reports on academic performance" -> createStaticticalReport();
				case "View info about students and teachers" -> viewStudentsAndTeachers();
				case "Check mail" -> manageMail();
				case "Logout" -> running = false;
			}
		}
	}
	
	public void createStaticticalReport() {
		java.util.List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(TableBuilder.statisticalReportHeaders);
		tableList.addAll(manager.extractStatistics());
		TableBuilder.buildTable(tableList, "Statistical report on academic performance");
		IOUtils.waitKey();
	}
	
	public void assignCourseToTeacher() {
		Vector<Teacher> teachers = DataBase.getInstance().getTeachers();
		TableBuilder.buildTable(TableBuilder.teacherHeaders, teachers, "Teachers");
		Teacher teacher = IOUtils.read(teachers, "teacher");
		
		Vector<Course> courses = DataBase.getInstance().getCoursesWithoutTeacher();
		TableBuilder.buildTable(TableBuilder.courseHeaders, courses, "Courses");
		Course course = IOUtils.read(courses, "course to teacher");
		
		if (course != null && teacher != null) manager.assignCourseToTeacher(teacher, course);
	}
	
	public void manageNews() {
		boolean running = true;
		
		while (running) {
			viewNews();
			
			TableBuilder.buildOptionsTable(manageNewsOptions);
			
			String option = IOUtils.readArray(manageNewsOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Add news" -> addNews();
				case "Remove news" -> removeNews();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void addNews() {
		// News removes automatically after some time interval
		manager.addNews();
		DataBase.getInstance().addLogs(new Log(Action.MANAGE_NEWS, manager));
	}
	
	public void removeNews() {
		viewNews();
		News news = IOUtils.read(DataBase.getInstance().getNews(), "news to remove");
		IOUtils.remove("news", manager.removeNews(news));
		DataBase.getInstance().addLogs(new Log(Action.MANAGE_NEWS, manager));
	}
	
	public void addCourse() {
		manager.addCourse();
		TableBuilder.buildTable(TableBuilder.courseHeaders, DataBase.getInstance().getCourses());
	}
	
	public void approveStudentsRegestration() {
		viewRequests(manager);
		Request request = IOUtils.read(DataBase.getInstance().getRequests(manager), "request");
		if (request != null) {
			request.setTo(manager);
			request.callback();			
		}
		DataBase.getInstance().addLogs(new Log(Action.APPROVE_REGESTRATION, manager));
	}
	
	public void viewStudentsAndTeachers() {
		boolean running = true;
		
		while (running) {
			TableBuilder.buildOptionsTable(viewStudentsAndTeachersOptions);
			
			String option = IOUtils.readArray(viewStudentsAndTeachersOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View students" -> manageStudentsView();
				case "View teachers" -> manageTeachersView();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void manageTeachersView() {
		boolean running = true;
		HelpComparator comparator = new NameComparator();
		
		while (running) {	
			viewTeachers(comparator);

			TableBuilder.buildOptionsTable(manageStudentsAndTeachersViewsOptions);
			String option = IOUtils.readArray(manageStudentsAndTeachersViewsOptions, "option");
			if (option == null) continue;
			
			switch (option) {
				case "Apply sorting" -> comparator = TeachersComparator.selectSort();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewTeachers(HelpComparator comparator) {
		Vector<Teacher> teachers = DataBase.getInstance().getTeachers();
		Collections.sort(teachers, comparator);
		TableBuilder.buildTable(TableBuilder.teacherHeaders, teachers, "Teachers" + comparator);
	}
	
	public void manageStudentsView() {		
		boolean running = true;
		HelpComparator comparator = new NameComparator();
		
		while (running) {	
			viewStudents(comparator);

			TableBuilder.buildOptionsTable(manageStudentsAndTeachersViewsOptions);
			String option = IOUtils.readArray(manageStudentsAndTeachersViewsOptions, "option");
			if (option == null) continue;
			
			switch (option) {
				case "Apply sorting" -> comparator = StudentsComparator.selectSort();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewStudents(HelpComparator comparator) {
		Vector<Student> students = DataBase.getInstance().getStudents();
		Collections.sort(students, comparator);
		ArrayList<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(TableBuilder.studentStatisticHeaders);
		for (int i = 0; i < students.size(); ++i) {
			tableList.add(students.get(i).extractStatisticData());			
		}
		TableBuilder.buildTable(tableList, "Students" + comparator);
	}
}