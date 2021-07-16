package Intranet ;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import Enums.Day;
import Enums.LessonForm;
import Interfaces.Extractable;

public class Lesson implements Serializable, Extractable
{	
	private LessonForm lessonForm;
		
	private Integer startTime;
		
	private Integer endTime;
		
	private Course course;
	
	private Day day;

	public Lesson() {
		
	}
	
	public Lesson(LessonForm lessonForm, Course course){
		this.lessonForm = lessonForm;
		this.course = course;
	}
	
	public Lesson(LessonForm lessonForm, int startTime, int endTime, Course course, Day day){
		this(lessonForm, course);
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
	}
	
	public Object[] extractData() {
		return new Object[] {lessonForm, course.getName(), day, getStartTime(), getEndTime()};
	}
	
	
	public Course getCourse() {
		return course;
	}
	
	public String getStartTime() {
		return String.format("%s:00:00", startTime);
	}
	
	public Day getDay() {
		return day;
	}
	
	public String getEndTime() {
		return String.format("%s:00:00", endTime);
	}
	
	public int getStartTimeHour() {
		return startTime;
	}
	
	public int getEndTimeHour() {
		return endTime;
	}
	
	public int hashCode() {
		// TODO implement me
		return 0;	
	}
		
	public boolean equals(Object parameter) {
		// TODO implement me
		return false;	
	}
		
	public String toString() {
		return "lesson";	
	}
		
	public Object clone() {
		// TODO implement me
		return null;	
	}
		
	public int compareTo(Object parameter) {
		// TODO implement me
		return 0;	
	}
	
}

