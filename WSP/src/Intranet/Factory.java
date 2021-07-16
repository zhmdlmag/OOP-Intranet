package Intranet;

import Enums.Attestation;
import Enums.Faculty;
import Enums.NewsType;
import Enums.RequestType;
import Enums.ResponseStatus;
import Enums.TeacherTitle;

public class Factory {
	
	private static String[] attestationOptions = new String[] { "Attestation", "First", "Second", "Final" };
	
	private static String[] newsTypeOptions = new String[] { "News type", "Information", "Warning" };
	
	private static String[] facultyOptions = new String[] { "Faculty", 	"FIT", "BS", "SCE", "FGE", "KMA", "SMC" };
	
	private static String[] teacherTitleOptions = new String[] { "Teacher title", "Tutor", "Lector", 
			"Senior Lector", "Professor", "Adviser" };

	public static String[] responseStatusOptions = new String[] {"Option", "Approve", "Decline"};
	
	/**
	 * Generates the new login for the user using firstname and lastname
	 * @param firstname name 
	 * @param lastname surname
	 * @return generated login
	 */
	public static String genLogin(String firstname, String lastname) {
		int iterator = 1;
		String login = firstname.toLowerCase().substring(0, iterator) + "_" + lastname.toLowerCase();
		while (iterator < firstname.length() && DataBase.getInstance().getUserByLogin(login) != null) {
			iterator++;
			login = firstname.toLowerCase().substring(0, iterator) + "_" + lastname.toLowerCase();
		}
		return login;
	}
	
	public static User getUser(String userType) {
		String firstname = IOUtils.readString("firstname");
		
		String lastname = IOUtils.readString("lastname");
		
		String password = IOUtils.readString("password");
		
		User user = null;
		
		switch (userType) {
			case "Student" -> {
				Faculty faculty = getFaculty();
				user = new Student(firstname, lastname, password, faculty);
			}
			case "Teacher" -> {
				TeacherTitle title = getTeacherTitle();
				Faculty faculty = getFaculty();
				user = new Teacher(firstname, lastname, password, title, faculty);
			}
			case "Manager" -> user = new Manager(firstname, lastname, password);
			case "Admin" -> user = new Admin(firstname, lastname, password);
			case "Librarian" -> user = new Librarian(firstname, lastname, password);
		}
		return user;
	}
	
	public static User updateUser(User userToUpdate) {
		try {
			User user = (User) userToUpdate.clone();
			user.setFirstname(IOUtils.readString("firstname"));
			
			user.setLastname(IOUtils.readString("lastname"));
			
			user.setPassword(IOUtils.readString("password"));
			return user;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public static CourseFile getCourseFile() {
		String name = IOUtils.readString("name");
		String content = IOUtils.readString("content");
		return new CourseFile(name, content);
	}
	
	public static CourseFile updateCourseFile(CourseFile courseFile) {
		try {
			CourseFile updatedCourseFile = (CourseFile) courseFile.clone();
			String name = IOUtils.readString("name");
			updatedCourseFile.setName(name);
			String content = IOUtils.readString("content");
			updatedCourseFile.setContent(content);
			return updatedCourseFile;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Message getMessage(Employee from, Employee to) {
		String title = IOUtils.readString("title");
		String message = IOUtils.readString("message");
		return new Message(title, from, to, message);
	}
	
	
	public static Attestation getAttestation() {
		TableBuilder.buildOptionsTable(attestationOptions);
		
		String option = IOUtils.readArray(attestationOptions, "attestation");
		return getAttestation(option);
	}
	
	public static Attestation getAttestation(String userType) {
		if (userType == null) return null;
		Attestation attestation = null;
		switch (userType) {
			case "First" -> attestation = Attestation.FIRST;
			case "Second" -> attestation = Attestation.SECOND;
			case "Final" -> attestation = Attestation.FINAL;
		}
		return attestation;
	}
	
	public static ResponseStatus getreResponseStatus() {
		TableBuilder.buildOptionsTable(responseStatusOptions);
		
		String option = IOUtils.readArray(responseStatusOptions, "response status");
		return getreResponseStatus(option);
	}
	
	public static ResponseStatus getreResponseStatus(String userType) {
		if (userType == null) return null;
		ResponseStatus responseStatus = null;
		switch (userType) {
			case "Approve" -> responseStatus = ResponseStatus.APPROVED;
			case "Decline" -> responseStatus = ResponseStatus.DECLINED;
		}
		return responseStatus;
	}

	public static NewsType getNewsType() {
		TableBuilder.buildOptionsTable(newsTypeOptions);
		
		String option = IOUtils.readArray(newsTypeOptions, "news type");

		return getNewsType(option);
	}
	
	public static NewsType getNewsType(String userType) {
		if (userType == null) return null;
		NewsType newsType = null;
		switch (userType) {
			case "First" -> newsType = NewsType.INFORMATION;
			case "Final" -> newsType = NewsType.WARNING;
		}
		return newsType;
	}
	
	public static TeacherTitle getTeacherTitle() {
		TableBuilder.buildOptionsTable(teacherTitleOptions);
		
		String option = IOUtils.readArray(teacherTitleOptions, "teacher title");

		return getTeacherTitle(option);
	}
	
	public static TeacherTitle getTeacherTitle(String userType) {
		if (userType == null) return null;
		TeacherTitle teacherTitle = null;
		switch (userType) {
			case "TUTOR" -> teacherTitle = TeacherTitle.TUTOR;
			case "LECTOR" -> teacherTitle = TeacherTitle.LECTOR;
			case "SENIOR_LECTOR" -> teacherTitle = TeacherTitle.SENIOR_LECTOR;
			case "PROFESSOR" -> teacherTitle = TeacherTitle.PROFESSOR;
			case "ADVISER" -> teacherTitle = TeacherTitle.ADVISER;
		}
		return teacherTitle;
	}
	
	public static Faculty getFaculty() {
		TableBuilder.buildOptionsTable(facultyOptions);
		
		String option = IOUtils.readArray(facultyOptions, "faculty");

		return getFaculty(option);
	}
	
	public static Faculty getFaculty(String userType) {
		if (userType == null) return null;
		Faculty faculty = null;
		switch (userType) {
			case "FIT" -> faculty = Faculty.FIT;
			case "BS" -> faculty = Faculty.BS;
			case "SCE" -> faculty = Faculty.SCE;
			case "FGE" -> faculty = Faculty.FGE;
			case "KMA" -> faculty = Faculty.KMA;
			case "SMC" -> faculty = Faculty.SMC;
		}
		return faculty;
	}
	
	public static News getNews() {
		String title = IOUtils.readString("title");
		String info = IOUtils.readString("info");
		NewsType newsType = getNewsType();
		return new News(title, newsType, info);
	}
	
	public static Course getCourse() {
		Course course = null;
		try {
			String name = IOUtils.readString("name");
			String cypher = IOUtils.readString("cypher");
			Integer credits = IOUtils.readInteger("credits");
			Integer ects = IOUtils.readInteger("ects");
			String description = IOUtils.readString("description");
			Integer lectureHours = IOUtils.readInteger("lecture hours");
			Integer practiceHours = IOUtils.readInteger("practice hours");
			Integer labWorkHours = IOUtils.readInteger("lab work hours");
			Faculty faculty = getFaculty();
			
			course = new Course(name, cypher, credits, ects, faculty, description, lectureHours, practiceHours, labWorkHours);
			
			LessonsGenerator.generateLessons(course);
		} catch (Exception e) {
			IOUtils.writer.println(e.getMessage());
		}
		return course;
	}
	
	public static RequestType getRequestType() {
		TableBuilder.buildOptionsTable(facultyOptions);
		
		String option = IOUtils.readArray(facultyOptions, "faculty");

		return getRequestType(option);
	}
	
	public static RequestType getRequestType(String userType) {
		if (userType == null) return null;
		RequestType requestType = null;
		switch (userType) {
			case "Approve students regestration" -> requestType = RequestType.APPROVE_STUDENTS_REGESTRATION;
			case "Assign course to teacher" -> requestType = RequestType.ASSIGN_COURSE_TO_TEACHER;
		}
		return requestType;
	}
}