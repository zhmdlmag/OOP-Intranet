package Intranet ;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Enums.RequestType;
import Enums.ResponseStatus;
import Enums.StudentMarks;

/**
 * 
 * This class used for managing news and courses, approving students registration,
 * assigning course to teacher, getting statistics of students performance.
 * @see Employee
 */

public class Manager extends Employee implements Serializable, Comparable<User>, Cloneable
{
	/**
	 * Default Manager constructor
	 */
	public Manager(){
		super();
	}
	/**
	 * Constructor that invokes parent constructor with all fields as parameters
	 * @param firstname
	 * @param lastname
	 * @param password
	 */
	public Manager(String firstname, String lastname, String password) {
		super(firstname, lastname, password);
	}
	/**
	 * Approves students registration for courses
	 * @param courses courses selected by student
	 * @param student 
	 */
	public void approveStudentsRegistration(Vector<Course> courses, Student student) {
		student.getTranscript().addCourses(courses, student.getNextPeriodOfStudy());
	}

	/**
	 * Adds course to the DataBase
	 * @param course course we want to add
	 */
	public void addCourse(Course course) {
		DataBase.getInstance().addCourse(course);
	}
	/**
	 * Assigns course to teacher
	 * @param teacher 
	 * @param course
	 */
	public void assignCourseToTeacher(Teacher teacher, Course course) {
		Request request = new CourseToTeacherRequest(this, teacher, course);
		DataBase.getInstance().addRequest(request);
	}
	/**
	 * Sets teacher to course
	 * @param teacher 
	 * @param course
	 */
	public void setTeacherToCourse(Teacher teacher, Course course) {
		course.setTeacher(teacher);
	}
	
	public String toString() {
		return super.toString();	
	}
	/**
	 * Adds course using Factory 
	 */
	public void addCourse() {
		Course course = Factory.getCourse();
		if (course != null) DataBase.getInstance().addCourse(course);
	}
	/**
	 * Adds news using Factory 
	 */
	public void addNews() {
		News news = Factory.getNews();
		DataBase.getInstance().addNews(news);
	}
	/**
	 * Removes new from DataBase
	 * @param news news we want to remove
	 * @return
	 */
	public boolean removeNews(News news) {
		return DataBase.getInstance().removeNews(news);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int compareTo(User o) {
		return super.compareTo(o);
	}
	
	public int hashCode() {
		return super.hashCode();	
	}
	
	public boolean equals(Object parameter) {
		return super.equals(parameter);	
	}
	/**
	 * Returns the statistics of GPA, first / second / final attestation marks.
	 * @return students statistic report
	 */
	public List<Object[]> extractStatistics() {
		DataBase dataBase = DataBase.getInstance();
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add( new Object[] { "GPA", dataBase.getStudentsWithCompleteCourses().size(), dataBase.getAverageValue(StudentMarks.GPA), dataBase.getMinValue(StudentMarks.GPA), dataBase.getMaxValue(StudentMarks.GPA) });
		tableList.add( new Object[] { "First attestation", dataBase.getStudentsWithCompleteCourses().size(), dataBase.getAverageValue(StudentMarks.FIRST_ATTESTATION), dataBase.getMinValue(StudentMarks.FIRST_ATTESTATION), dataBase.getMaxValue(StudentMarks.FIRST_ATTESTATION) });
		tableList.add( new Object[] { "Second attestation", dataBase.getStudentsWithCompleteCourses().size(), dataBase.getAverageValue(StudentMarks.SECOND_ATTESTATION), dataBase.getMinValue(StudentMarks.SECOND_ATTESTATION), dataBase.getMaxValue(StudentMarks.SECOND_ATTESTATION) });
		tableList.add( new Object[] { "Final attestation", dataBase.getStudentsWithCompleteCourses().size(), dataBase.getAverageValue(StudentMarks.FINAL), dataBase.getMinValue(StudentMarks.FINAL), dataBase.getMaxValue(StudentMarks.FINAL) });
		return tableList;
	}
}