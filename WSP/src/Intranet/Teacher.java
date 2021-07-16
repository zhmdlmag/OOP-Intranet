package Intranet ;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import Enums.Attestation;
import Enums.Faculty;
import Enums.TeacherTitle;
import Interfaces.Extractable;

public class Teacher extends Employee implements Serializable, Comparable<User>, Cloneable, Extractable
{
	private TeacherTitle title = TeacherTitle.PROFESSOR;
	
	private int rating = 0;
	
	private Faculty faculty = Faculty.FIT;

	public Teacher(){
		super();
	}

	public Teacher(String firstname, String lastname, String password) {
		super(firstname, lastname, password);
	}
	
	
	public Teacher(String firstname, String lastname, String password, TeacherTitle title, Faculty faculty) {
		this(firstname, lastname, password);
		this.title = title;
		this.faculty = faculty;
	}

	public Faculty getFaculty() {
		return faculty;
	}
	
	public TeacherTitle getTitle() {
		return title;
	}
	
	public Vector<Lesson> getLessons() {
		return DataBase.getInstance().getLessons(this);
	}
	
	public int getRating() {
		return rating;
	}

	public Vector<Course> getCourses() {
		Vector<Course> courses = DataBase.getInstance().getCourses(this);
		
		return courses;
	}
	
	public void updateRating() {
		rating++;
	}
	
	public void addCourseFile(Course course) {
		CourseFile courseFile = Factory.getCourseFile();
		course.addCourseFile(courseFile);
	}
	
	public void updateCourseFile(Course course, CourseFile courseFile) {
		CourseFile updatedCourseFile = Factory.updateCourseFile(courseFile);
		course.getCourseFiles().remove(courseFile);
		course.getCourseFiles().add(updatedCourseFile);
	}
	
	public boolean removeCourseFile(Course course, CourseFile courseFile) {
		return course.getCourseFiles().remove(courseFile);
	}
	
	public void putMark(Course course, Student student) { 
  		Attestation attestation = Factory.getAttestation();
  		
  		Double digitalMark = IOUtils.readDouble("mark");
  		
		student.getTranscript().setMark(course, attestation, digitalMark);
	}
	
	public int hashCode() {
		return super.hashCode();	
	}
	
	public boolean equals(Object parameter) {
		return super.equals(parameter);	
	}
	
	public Object[] extractData() {
		return new Object[] {getFirstname(), getLastname(), title, faculty, rating};
	}
	
	public int compareTo(User o) {
		return super.compareTo(o);	
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}