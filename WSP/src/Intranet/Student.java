package Intranet ;
import java.util.Vector;

import Enums.Attestation;
import Enums.Faculty;
import Enums.PeriodOfStudy;
import Enums.RequestType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Student extends User implements Serializable, Comparable<User>
{
	private static HashMap<Integer, PeriodOfStudy> periodOfStudy = new HashMap<Integer, PeriodOfStudy>();
	
	private String id;
	
	private Transcript transcript = new Transcript();
	
	private Date yearOfAdmissioin = new Date(119, 8, 1);
	
	private Faculty faculty;
	
	private Vector<Book> libraryBooks = new Vector<Book>();
	
	static {
		periodOfStudy.put(0, PeriodOfStudy.FIRST_FALL);
		periodOfStudy.put(1, PeriodOfStudy.FIRST_SPRING);
		periodOfStudy.put(2, PeriodOfStudy.SECOND_FALL);
		periodOfStudy.put(3, PeriodOfStudy.SECOND_SPRING);
		periodOfStudy.put(4, PeriodOfStudy.THIRD_FALL);
		periodOfStudy.put(5, PeriodOfStudy.THIRD_SPRING);
		periodOfStudy.put(6, PeriodOfStudy.FOURTH_FALL);
		periodOfStudy.put(7, PeriodOfStudy.FOURTH_SPRING);
		periodOfStudy.put(8, PeriodOfStudy.FOURTH_SPRING);
	}
	
	public Student(){
		super();
	}

	public Student(String firstname, String lastname, String password, Faculty faculty) {
		super(firstname, lastname, password);
		this.faculty = faculty;
	}
	
	public void registerForCourses(Vector<Course> courses) {
		Request request = new RegistrationRequest(this, courses);
		DataBase.getInstance().addRequest(request);
	}
	
	public Vector<Lesson> getLessons() {
		return DataBase.getInstance().getLessons(this);
	}
	
	public Vector<Book> getBooks() {	
		return libraryBooks;
	}
	
	public String getId() {
		return id;	
	}
	
	public Transcript getTranscript() {
		return transcript;
	}
	
	public Date getYearOfAdmission() {
		return yearOfAdmissioin;	
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	
	public PeriodOfStudy getPeriodOfStudy() {
		Date currentDate = DataBase.getInstance().getDate();
	    Integer	currentPeriodOfStudy = 2 * (currentDate.getYear() - yearOfAdmissioin.getYear() - 1);
	    
	    if (currentDate.getYear() > yearOfAdmissioin.getYear()) {
	    	currentPeriodOfStudy++;
	    	if (currentDate.getMonth() >= 5) {
	    		currentPeriodOfStudy++;
	    	}
	    }
		return periodOfStudy.get(currentPeriodOfStudy);	
	}
	
	public PeriodOfStudy getNextPeriodOfStudy() {
		Date currentDate = DataBase.getInstance().getDate();
	    Integer	currentPeriodOfStudy = 2 * (currentDate.getYear() - yearOfAdmissioin.getYear() - 1);
	    
	    if (currentDate.getYear() > yearOfAdmissioin.getYear()) {
	    	currentPeriodOfStudy++;
	    	if (currentDate.getMonth() >= 5) {
	    		currentPeriodOfStudy++;
	    	}
	    }
		return periodOfStudy.get(currentPeriodOfStudy + 1);	
	}
	
	public int getYearOfStudy() {
		Date currentDate = DataBase.getInstance().getDate();
		Integer yearOfStudy = currentDate.getYear() - yearOfAdmissioin.getYear();
		if (currentDate.getMonth() >= 5) {
			yearOfStudy++;
		}
		return yearOfStudy;
	}
	
	public int hashCode() {
		// TODO implement me
		return super.hashCode();	
	}
	
	public boolean equals(Object parameter) {
		// TODO implement me
		return super.equals(parameter);	
	}
	
	public String toString() {
		// TODO implement me
		return super.toString();	
	}
	
	public void receiveBook(Book book) {
		// TODO DELETE THIS
		libraryBooks.add(book);
	}
	
	public void orederBook(Book book) {
		OrderBookRequest order = new OrderBookRequest(this, book);
		DataBase.getInstance().addRequest(order);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Student clone = (Student) super.clone();
		clone.transcript = (Transcript) transcript.clone();
		clone.yearOfAdmissioin = (Date) yearOfAdmissioin.clone();
		Vector<Book> filtered = new Vector<Book>();
		for (Book book : libraryBooks) {
			filtered.add((Book) book.clone());
		}
		clone.libraryBooks = filtered;
		return clone;
	}

	@Override
	public int compareTo(User o) {
		return super.compareTo(o);
	}

	public Object[] extractData(Course course) {
		IOUtils.writer.println(course);
		Mark mark = getTranscript().getMark(course);
		if (mark == null) {
			getTranscript().addCourse(course, getPeriodOfStudy());
			mark = getTranscript().getMark(course);
		}
		return new Object[] {getFirstname(), getLastname(), getYearOfStudy(), faculty, 
				mark.getFirstAttestationGrade(), mark.getSecondAttestationGrade(), mark.getFinalGrade()};
	}
	
	public Object[] extractStatisticData() {
		return new Object[] {getFirstname(), getLastname(), getYearOfStudy(), faculty, 
				getTranscript().getTotalGpa()};
	}
}