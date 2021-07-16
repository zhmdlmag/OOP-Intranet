package Intranet;

import java.util.Vector;
import Enums.RequestType;
import Enums.ResponseStatus;

public class CourseToTeacherRequest extends Request {
	
	public Course course;
	
	public CourseToTeacherRequest() {
		
	}
	
	public CourseToTeacherRequest(User from, User to, Course course) {
		super(from, to);	
		setRequestType(RequestType.ASSIGN_COURSE_TO_TEACHER);
		this.course = course;
	}

	@Override
	public void callback() {
		Teacher teacher = (Teacher) isTo();
		Manager manager = (Manager) isFrom();
		ResponseStatus responseStatus = getResponseStatus();
		if (responseStatus == ResponseStatus.APPROVED) {
			manager.setTeacherToCourse(teacher, course);
			IOUtils.writer.println(String.format("Teacher %s %s successfully assigned to the %s course!", teacher.getFirstname(), teacher.getLastname(), course.getName() ));
		}
		DataBase.getInstance().removeRequest(this);
		DataBase.getInstance().addResponse(new Response(this, responseStatus));
	}
	
	@Override
	public ResponseStatus getResponseStatus() {
		Teacher teacher = (Teacher) isTo();
		Vector<Course> teacherCourses = DataBase.getInstance().getCourses(teacher);
		if (teacherCourses.contains(course)) {
			IOUtils.writer.println(String.format("Teacher %s %s already assigned to the %s course!", teacher.getFirstname(), teacher.getLastname(), course.getName() ));
			return ResponseStatus.REPEATED_REQUEST;
		}
		TableBuilder.buildTable(TableBuilder.courseHeaders, course);
		return Factory.getreResponseStatus();
	}
}