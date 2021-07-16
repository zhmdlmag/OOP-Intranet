package Intranet;
import java.util.Vector;

import Enums.Faculty;
import Enums.PeriodOfStudy;
import Enums.RequestType;
import Enums.StudentMarks;
import Threads.NewsThread;

import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * The DataBase class used as a storage for the application.
 * This class supports CRUD operations for the stored data.
 */

public class DataBase implements Serializable
{	
	class EofIndicatorClass implements Serializable{}
	
	private static DataBase instance = null;
	
	private static String fileName = "data1.ser";
	
	private Date date = new Date();
	
	private HashSet<Course> courses = new HashSet<Course>();
	
	private TreeSet<User> users = new TreeSet<User>();
	
	private Vector<Message> messages = new Vector<Message>();
	
	private Vector<News> news = new Vector<News>();
	
	private Vector<Lesson> lessons = new Vector<Lesson>();
	
	private Vector<Log> logs = new Vector<Log>();
	
	private HashMap<Book, Integer> booksInStock = new HashMap<Book, Integer>();
	
	private HashMap<PeriodOfStudy, HashMap<Faculty, RegistrationRequirements > > registrationRequirements = new HashMap<PeriodOfStudy, HashMap<Faculty, RegistrationRequirements > >();
	
	private Vector<Response> responses = new Vector<Response>();
	
	private Vector<Request> requests = new Vector<Request>();
	
	/**
	 * DataBase default constructor
	 */
	private DataBase() {

	}
	/**
	 * Gets the DataBase with preventing
	 * of creation a new DataBase instance
	 * @return DataBase
	 * @see #load()
	 */
	public static DataBase getInstance() {
		if (instance == null) {
//			instance = load();
			instance = new DataBase();
		}
		return instance;
	}
	/**
	 * Gets deserialized DataBase from the file
	 * in case when data couldn't extracted return new DataBase
	 * @return
	 */
	private static DataBase load() {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			DataBase instance = (DataBase) ois.readObject();
			IOUtils.writer.println("Data has been extracted successfuly");
			ois.close();			
			fis.close();
			return instance;
		}
		catch (Exception e) {
			IOUtils.writer.println("Couldn't extract data");
			IOUtils.writer.println(e.toString());
			return new DataBase();
		}
	}
	/**
	 * Serializes the DataBase into the file
	 */
	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			IOUtils.writer.println("Files saved successfully");
			oos.writeObject(instance);
			
			oos.writeObject(new EofIndicatorClass());
			
			oos.close();			
			fos.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	
	public Date getDate() {
		return date;
	}
	/**
	 * Checks if there is book in stock
	 * @param book the book that we need to find
	 * @return true or false
	 * @see #getBookCount(Book)
	 */
	public boolean hasBook(Book book) {
		return getBookCount(book) > 0;
	}
	/**
	 * Gets books count in stock
	 * @param book
	 * @return books count
	 */
	public Integer getBookCount(Book book) {
		return booksInStock.get(book);
	}
	
	public Vector<Book> getBooks() {
		Vector<Book> books = new Vector<Book>();
		for (Book book : booksInStock.keySet()) {
			books.add(book);
		}
		return books;
	}
	
	public void getBook(Book book) {
		booksInStock.merge(book, 1, (oldValue, decrement) -> oldValue - decrement);
	}
	
	public void addBook(Book book) {
		if (booksInStock.containsKey(book)) {
			booksInStock.merge(book, 1, (oldValue, increment) -> oldValue + increment);			
		} else {
			booksInStock.put(book, 1);
		}
	}
	
	public User getUserByLogin(String login) {
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}
	
	public Vector<Course> getCourses() {
		Vector<Course> courses = new Vector<Course>();
		for (Course course : this.courses) {
			courses.add(course);
		}
		return courses;	
	}
	
	public Vector<Course> getCoursesWithoutTeacher() {
		Vector<Course> courses = new Vector<Course>();
		for (Course course : this.courses) {
			if (course.getTeacher() == null) {
				courses.add(course);				
			}
		}
		return courses;	
	}
	
	public Vector<Course> getCourses(Teacher teacher) {
		Vector<Course> courses = new Vector<Course>();
		for (Course course : this.courses) {
			if (teacher.equals(course.getTeacher())) {
				courses.add(course);				
			}
		}
		return courses;	
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public Vector<User> getUsers() {
		Vector<User> users2 = new Vector<User>();
		for (User user : users) {
			users2.add(user);
		}
		return users2;	
	}
	/**
	 * Returns user if it user with current cridentials exists
	 * @param position user type
	 * @param login 
	 * @param password
	 * @return user 
	 */
	public User login(String position, String login, String password) {
		for (User user : users) {
			if (user.getLogin().equals(login) && 
				user.getPassword().equals(password) &&
				user.getClass().getName().contains(position) ) {
				return user;
			}
		}
		return null;
	}
	
	public Vector<News> getNews() {
		return news;
	}
	
	public void addNews(News news) {
		new NewsThread(news).start();
		this.news.add(news);
	}
	
	public boolean removeNews(News news) {
		return this.news.remove(news);
	}
	
	public Vector<Student> getStudents() {
		Vector<Student> students = new Vector<Student>();
		for (User user : users) {
			if (user instanceof Student) {
				students.add((Student) user);
			}
		}
		return students;	
	}
	
	public Vector<Student> getStudents(Course course) {
		Vector<Student> students = new Vector<Student>();
		for (User user : users) {
			if (user instanceof Student) {
				Student student = (Student) user;
				if (student.getTranscript().getCourses().contains(course)) {
					students.add(student);
				}
			}
		}
		return students;
	}
	
	public Vector<Teacher> getTeachers() {
		Vector<Teacher> teachers = new Vector<Teacher>();
		for (User user : users) {
			if (user instanceof Teacher) {
				teachers.add((Teacher) user);
			}
		}
		return teachers;	
	}
	
	public Vector<Employee> getEmployees() {
		Vector<Employee> employees = new Vector<Employee>();
		for (User user : users) {
			if (user instanceof Employee) {
				employees.add((Employee) user);
			}
		}
		return employees;	
	}
	
	public Vector<Lesson> getLessons(Student student) {
		Vector<Lesson> studentLessons = new Vector<Lesson>();
		for (Course course : student.getTranscript().getCourses()) {
			for (Lesson lesson : lessons) {
				if (lesson.getCourse().equals(course)) {
					studentLessons.add(lesson);
				}
			}
		}
		return studentLessons;
	}
	
	public Vector<Lesson> getLessons(Teacher teacher) {
		Vector<Lesson> teacherLessons = new Vector<Lesson>();
		for (Course course : courses) {
			if (teacher.equals(course.getTeacher())) {
				for (Lesson lesson : lessons) {
					if (course.equals(lesson.getCourse())) {
						teacherLessons.add(lesson);
					}
				}				
			}
		}
		return teacherLessons;
	}
	
	public Vector<Lesson> getLessons(Course course) {
		Vector<Lesson> courseLessons = new Vector<Lesson>();
		for (Lesson lesson : lessons) {
			if (lesson.getCourse().equals(course)) {
				courseLessons.add(lesson);
			}
		}
		return courseLessons;
	}
	
	public Vector<Lesson> getLessons() {
		return lessons;
	}
	
	public void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}
	
	public User addUser(User user) {
		users.add(user);
		return user;	
	}
	
	public boolean removeUser(User user) {
		return users.remove(user);
	}
	
	public boolean removeMessage(Message message) {
		return messages.remove(message);
	}
	
	public void updateUser(User user, User updatedUser) {
		users.remove(user);
		users.add(updatedUser);
	}
	
	public Vector<Log> getLogs() {
		return logs;	
	}
	
	public Vector<Log> getLogs(User user) {
		Vector<Log> userLogs = new Vector<Log>();
		for (Log log : logs) {
			if (log.getUser().equals(user)) {
				userLogs.add(log);
			}
		}
		return userLogs;	
	}
	
	public void addLogs(Log log) {
		logs.add(log);
	}
	
	public HashMap<Book, Integer> getBooksInStock() {
		return booksInStock;	
	}
	
	public Vector<Message> getMessages(Employee employee) {
		Vector<Message> messages2 = new Vector<Message>();
		for (Message message : messages) {
			if (message.isTo().equals(employee)) {
				messages2.add(message);
			}
		}
		return messages2;	
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	public RegistrationRequirements getRegistrationRequirements(PeriodOfStudy periodOfStudy, Faculty faculty) {
		if (registrationRequirements.get(periodOfStudy) == null) {
			addRegestrationRequirements(periodOfStudy, faculty, new RegistrationRequirements() );
		}
		return registrationRequirements.get(periodOfStudy).get(faculty);
	}
	
	public void addRegestrationRequirements(PeriodOfStudy periodOfStudy, Faculty faculty, RegistrationRequirements newRegestrationRequirements) {
		HashMap<Faculty, RegistrationRequirements> innerMap = registrationRequirements.get(periodOfStudy);
		if (innerMap == null) {
			innerMap = new HashMap<Faculty, RegistrationRequirements>();
		}
		innerMap.put(faculty, newRegestrationRequirements);
		registrationRequirements.put(periodOfStudy, innerMap);	
	}
	
	public int getCredits(Vector<Course> courses) {
		int totalCredits = 0;
		for (Course course : courses) {
			totalCredits += course.getCredits();
		}
		return totalCredits;
	}
	
	public Vector<Request> getRequests(User user) {
		Vector<Request> requests2 = new Vector<Request>();
		for (Request request : requests) {
			if (user.equals(request.isTo())) {
				requests2.add(request);				
			}
		}
		if (user instanceof Manager) {
			Manager manager = (Manager) user;
			requests2.addAll(getRequests(manager));
		}
		if (user instanceof Librarian) {
			Librarian librarian = (Librarian) user;
			requests2.addAll(getRequests(librarian));
		}
		return requests2;
	}
	
	public Vector<Request> getRequests(Manager manager) {
		Vector<Request> requests2 = new Vector<Request>();
		for (Request request : requests) {
			if (request.getRequestType() == RequestType.APPROVE_STUDENTS_REGESTRATION) {
				requests2.add(request);
			}
		}
		return requests2;
	}
	
	public Vector<Request> getRequests(Librarian librarian) {
		Vector<Request> requests2 = new Vector<Request>();
		for (Request request : requests) {
			if (request.getRequestType() == RequestType.ORDER_BOOK) {
				requests2.add(request);
			}
		}
		return requests2;
	}
	
	public Vector<Response> getResponses(User user) {
		Vector<Response> responses2 = new Vector<Response>();
		for (Response response : responses) {
			if (user.equals(response.isTo())) {
				responses2.add(response);
			}
		}
		return responses2;
	}
	
	public void addRequest(Request request) {
		requests.add(request);
	}
	
	public void removeRequest(Request request) {
		requests.remove(request);
	}
	
	public void addResponse(Response response) {
		responses.add(response);
	}
	
	public int getFreeElectivesCount(Vector<Course> coursesForRegistration, Faculty studentFaculty) {
		int count = 0;
		for (Course course : coursesForRegistration) {
			if (course.getFaculty() != studentFaculty) {
				count++;
			}
		}
		return count;
	}
	
	public int getMajorElectivesCount(Vector<Course> coursesForRegistration, Faculty studentFaculty, int requiredCoursesCount) {
		int count = 0;
		for (Course course : coursesForRegistration) {
			if (course.getFaculty() == studentFaculty) {
				count++;
			}
		}
		return count - requiredCoursesCount;
	}
	
	public Vector<Student> getStudentsWithCompleteCourses() {
		Vector<Student> students = getStudents();
		Vector<Student> students2 = new Vector<Student>();
		for (Student student : students) {
			for (Mark mark : student.getTranscript().getCourseAndMark().values()) {
				if (mark.isComplete()) {
					students2.add(student);
					break;
				}
			}
		}
		return students2;
	}
	/**
	 * Return students average mark by observing all complete courses
	 * Using enumeration we specify the mark that we want to obtain
	 * (gpa, first/second/final attestation)
	 * @param studentMarks enumeration for specifying fields we want to obtain
	 * @return average mark
	 */
	public double getAverageValue(StudentMarks studentMarks) {
		double total = 0;
		Vector<Student> students = getStudentsWithCompleteCourses();
		for (Student student : students) {
			switch (studentMarks) {
				case FIRST_ATTESTATION -> total += student.getTranscript().getAverageFirstAttestationGrade();
				case SECOND_ATTESTATION -> total += student.getTranscript().getAverageSecondAttestationGrade();
				case FINAL -> total += student.getTranscript().getAverageFinalAttestationGrade();
				case GPA -> total += student.getTranscript().getTotalGpa();
			}
			total += student.getTranscript().getTotalGpa();
		}
		return Mark.average(total, students.size());
	}
	/**
	 * Return students minimum mark by observing all complete courses
	 * Using enumeration we specify the marks that we want to obtain
	 * (gpa, first/second/final attestation)
	 * @param studentMarks enumeration for specifying fields we want to obtain
	 * @return minimum mark
	 */
	public double getMinValue(StudentMarks studentMarks) {
		double min = 100;
		Vector<Student> students = getStudentsWithCompleteCourses();
		for (Student student : students) {
			switch (studentMarks) {
				case FIRST_ATTESTATION -> min = Math.min(min, student.getTranscript().getAverageFirstAttestationGrade());
				case SECOND_ATTESTATION -> min = Math.min(min, student.getTranscript().getAverageSecondAttestationGrade());
				case FINAL -> min = Math.min(min, student.getTranscript().getAverageFinalAttestationGrade());
				case GPA -> min = Math.min(min, student.getTranscript().getTotalGpa());
			}	
		}
		return (min < 100) ? min : 0;
	}
	/**
	 * Return students maximum mark by observing all complete courses
	 * Using enumeration we specify the marks that we want to obtain
	 * (gpa, first/second/final attestation)
	 * @param studentMarks enumeration for specifying fields we want to obtain
	 * @return maximum mark
	 */
	public double getMaxValue(StudentMarks studentMarks) {
		double max = 0;
		Vector<Student> students = getStudentsWithCompleteCourses();
		for (Student student : students) {
			switch (studentMarks) {
				case FIRST_ATTESTATION -> max = Math.max(max, student.getTranscript().getAverageFirstAttestationGrade());
				case SECOND_ATTESTATION -> max = Math.max(max, student.getTranscript().getAverageSecondAttestationGrade());
				case FINAL -> max = Math.max(max, student.getTranscript().getAverageFinalAttestationGrade());
				case GPA -> max = Math.max(max, student.getTranscript().getTotalGpa());
			}	
		}
		return max;
	}
}