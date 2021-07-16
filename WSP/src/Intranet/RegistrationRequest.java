package Intranet;

import java.util.Vector;

import javax.security.auth.callback.Callback;

import Enums.RequestType;
import Enums.ResponseStatus;

public class RegistrationRequest extends Request {
	
	public Vector<Course> courses = new Vector<Course>();
	
	public RegistrationRequest() {
		
	}
	
	public RegistrationRequest(User from, Vector<Course> courses) {
		super(from);	
		setRequestType(RequestType.APPROVE_STUDENTS_REGESTRATION);
		this.courses = courses;
	}
	
	@Override
	public void callback() {
		Student student = (Student) isFrom();
		Manager manager = (Manager) isTo();
		ResponseStatus responseStatus = getResponseStatus();
		if (responseStatus == ResponseStatus.APPROVED) {
			IOUtils.writer.println(String.format("Student %s %s registration approved!", student.getFirstname(), student.getLastname() ));
			manager.approveStudentsRegistration(courses, student);
		}
		DataBase.getInstance().removeRequest(this);
		DataBase.getInstance().addResponse(new Response(this, responseStatus));
	}

	@Override
	public ResponseStatus getResponseStatus() {
		Student student = (Student) isFrom();
		RegistrationRequirements registrationRequirements = DataBase.getInstance().getRegistrationRequirements(student.getNextPeriodOfStudy(), student.getFaculty());
		
		for (Course requiredCourse : registrationRequirements.getRequiredCourses()) {
			if (!courses.contains(requiredCourse)) {
				IOUtils.writer.println(String.format("Student %s %s didn't choose all required courses!", student.getFirstname(), student.getLastname() ));
				return ResponseStatus.DECLINED;
			}
		}
		
		for (Course course : courses) {
			if (course.getStudentsCount() == course.getMaxStudentsCount()) {
				IOUtils.writer.println(String.format("Maximum students count of %s course exceeded, cannot registrate!", course.getName() ));
				return ResponseStatus.DECLINED;
			}
		}
		
		for (Course course : courses) {
			Vector<Course> studentCourses = student.getTranscript().getCourses();
			if (studentCourses.contains(course)) {
				IOUtils.writer.println(String.format("Student %s %s already complete %s course!", student.getFirstname(), student.getLastname(), course.getName() ));
				return ResponseStatus.DECLINED;
			}
		}
		
		for (Course course : courses) {
			for (Course prerequisite : course.getPrerequisites()) {
				if (student.getTranscript().getMark(prerequisite) == null) {
					IOUtils.writer.println(String.format("Student %s %s didn't pass all required prerequisite courses! ( %s )", student.getFirstname(), student.getLastname(), prerequisite.getCypher() ));
					return ResponseStatus.DECLINED;
				}
			}
		}
		
		if (DataBase.getInstance().getCredits(courses) > registrationRequirements.getMaximumCredits()) {
			IOUtils.writer.println(String.format("Student %s %s exceeded the credit limit! (%s of %s)", student.getFirstname(), student.getLastname(), DataBase.getInstance().getCredits(courses), registrationRequirements.getMaximumCredits() ));
			return ResponseStatus.DECLINED; 
		}
		
		if (DataBase.getInstance().getFreeElectivesCount(courses, student.getFaculty()) > registrationRequirements.getMaxFreeElectivesCount()) {
			IOUtils.writer.println(String.format("Student %s %s exceeded free electives count! (%s of %s)", student.getFirstname(), student.getLastname(), DataBase.getInstance().getFreeElectivesCount(courses, student.getFaculty()), registrationRequirements.getMaxFreeElectivesCount()));
			return ResponseStatus.DECLINED; 
		}
		
		if (DataBase.getInstance().getMajorElectivesCount(courses, student.getFaculty(), registrationRequirements.getRequiredCourses().size()) > registrationRequirements.getMaxMajorElectivesCount()) {
			IOUtils.writer.println(String.format("Student %s %s exceeded major electives count! (%s of %s)", student.getFirstname(), student.getLastname(),
					DataBase.getInstance().getMajorElectivesCount(courses, student.getFaculty(), registrationRequirements.getRequiredCourses().size()), registrationRequirements.getMaxMajorElectivesCount()));
			return ResponseStatus.DECLINED;
		}
		IOUtils.writer.println("Registration satisfies to all requirements");
		return Factory.getreResponseStatus();
	}
}