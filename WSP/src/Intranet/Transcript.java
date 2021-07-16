package Intranet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Enums.Attestation;
import Enums.PeriodOfStudy;

public class Transcript implements Serializable, Cloneable
{
	private HashMap<Course, Mark> courseAndMark = new HashMap<Course, Mark>();
	
	private HashMap<Course, PeriodOfStudy> courseAndPeriodOfStudy = new HashMap<Course, PeriodOfStudy>();

	public Transcript(){
		super();
	}

	public Mark getMark(Course course) {
		return courseAndMark.get(course);	
	}
	
	public void setMark(Course course, Attestation attestation, Double digitalMark) {
		Mark mark = courseAndMark.get(course);
		try {
			switch (attestation) {
				case FIRST -> mark.setFirstAttestationGrade(digitalMark);
				case SECOND -> mark.setSecondAttestationGrade(digitalMark);
				case FINAL -> mark.setFinalGrade(digitalMark);
			}
		} catch (Exception e) {
			IOUtils.writer.println(e.getMessage());
		}
	}
	
	public HashMap<Course, Mark> getCourseAndMark() {
		return courseAndMark;
	}
	
	public HashMap<Course, PeriodOfStudy> getCourseAndPeriodOfStudy() {
		return courseAndPeriodOfStudy;
	}
	
	public void addCourse(Course course, PeriodOfStudy periodOfStudy) {
		courseAndMark.put(course, new Mark());
		courseAndPeriodOfStudy.put(course, periodOfStudy);
	}
	
	public void addCourses(Vector<Course> courses, PeriodOfStudy periodOfStudy) {
		for (Course course : courses) {
			addCourse(course, periodOfStudy);			
		}
	}
	
	public double getTotalGpa() {
		double gpaTimesCredits = 0;
		for (HashMap.Entry<Course, Mark> entry : courseAndMark.entrySet()) {
			Course course = entry.getKey();
			Mark mark = entry.getValue();
			if (mark.isComplete()) gpaTimesCredits += course.getCredits() * mark.getGpa();;
		}
		return Mark.average(gpaTimesCredits, getTotalCredits());
	}
	
	public double getAverageFirstAttestationGrade() {
		int total = 0;
		for (Mark mark : courseAndMark.values()) {
			if (mark.isComplete()) total += mark.getFirstAttestationGrade();
		}
		return Mark.average(total, courseAndMark.size());
	}
	
	public double getAverageSecondAttestationGrade() {
		int total = 0;
		for (Mark mark : courseAndMark.values()) {
			if (mark.isComplete()) total += mark.getSecondAttestationGrade();
		}
		return Mark.average(total, courseAndMark.size());
	}
	
	public double getAverageFinalAttestationGrade() {
		int total = 0;
		for (Mark mark : courseAndMark.values()) {
			if (mark.isComplete()) total += mark.getFinalGrade();
		}
		return Mark.average(total, courseAndMark.size());
	}
	
	public int getTotalCredits() {
		int totalCredits = 0;
		for (Course course: courseAndMark.keySet()) {
			totalCredits += course.getCredits();
		}
		return totalCredits;	
	}
	
	public int getTotalEcts() {
		int totalEcts = 0;
		for (Course course: courseAndMark.keySet()) {
			totalEcts += course.getEcts();
		}
		return totalEcts;	
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	public List<Object[]> extractData() {
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(TableBuilder.transcriptHeaders);
		for (HashMap.Entry<Course, Mark> entry : courseAndMark.entrySet()) {
		    Course course = entry.getKey();
		    Mark mark = entry.getValue();
		    PeriodOfStudy periodOfStudy = courseAndPeriodOfStudy.get(course);
		    tableList.add(new Object[] {
		    		course.getName(), 
		    		mark.getDigitalGrade(), 
		    		mark.getTraditionalGrade(), 
		    		mark.getLetterGrade(),
		    		mark.getGpa(),
		    		mark.isCompleteToString(),
		    		periodOfStudy
		    });
		}
		return tableList;
	}

	public Vector<Course> getCourses() {
		Vector<Course> courses = new Vector<Course>();
		for (Course course : courseAndMark.keySet()) {
			courses.add(course);
		}
		return courses;	
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Transcript clone = (Transcript) super.clone();
		clone.courseAndMark = new HashMap<>(courseAndMark);
		clone.courseAndPeriodOfStudy = new HashMap<>(courseAndPeriodOfStudy);
		return clone;
	}
}