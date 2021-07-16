package Intranet ;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Enums.Faculty;
import Interfaces.Extractable;

public class Course implements Serializable, Extractable {
	
	private String name;
	
	private Teacher teacher;
	
	private String description;
	
	private Vector<CourseFile> courseFiles = new Vector<CourseFile>();
	
	private String cypher;
	
	private int credits;
	
	private int ects;
	
	private Faculty faculty;
	
	private int lectureHours;
	
	private int practiceHours;
	
	private int labWorkHours;
	
	private int maxStudentsCount = 60;
	
	private Vector<Course> prerequisites = new Vector<Course>();
	
	public Course(){
		super();
	}	
	
	public Course(String name, String cypher, int credits, int ects, Faculty faculty) {
		this.name = name;
		this.cypher = cypher;
		this.credits = credits;
		this.ects = ects;
		this.faculty = faculty;
	}
	
	public Course(String name, String cypher, int credits, int ects, Faculty faculty, String description) {
		this(name, cypher, credits, ects, faculty);
		this.description = description;
	}
	
	public Course(String name, String cypher, int credits, int ects, Faculty faculty, String description,
			int lectureHours, int practiceHours, int labWorkHours) {
		this(name, cypher, credits, ects, faculty, description);
		this.lectureHours = lectureHours;
		this.practiceHours = practiceHours;
		this.labWorkHours = labWorkHours;
	}
	
	public Course(String name, Teacher teacher, String description, Vector<CourseFile> courseFiles, String cypher,
			int credits, int ects, Faculty faculty, Vector<Course> prerequisites) {
		this(name, cypher, credits, ects, faculty);
		this.teacher = teacher;
		this.description = description;
		this.courseFiles = courseFiles;
		this.prerequisites = prerequisites;
	}
	
	public void addCourseFile(CourseFile courseFile) {
		courseFiles.add(courseFile);
	}

	public Vector<CourseFile> getCourseFiles() {
		return courseFiles;	
	}
	
	public int getStudentsCount() {
		return DataBase.getInstance().getStudents(this).size();
	}
	
	public int getMaxStudentsCount() {
		return maxStudentsCount;
	}
	
	public Teacher getTeacher() {
		return teacher;	
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public int getLectureHours() {
		return lectureHours;
	}
	
	public int getPracticeHours() {
		return practiceHours;
	}
	
	public int getLabWorkHours() {
		return labWorkHours;
	}
	
	public String getCypher() {
		// TODO implement me
		return cypher;	
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Vector<Course> getPrerequisites() {
		return prerequisites;	
	}
	
	public Faculty getFaculty() {
		// TODO implement me
		return faculty;	
	}
	
	public String getName() {
		return name;
	}
	
	public int getCredits() {
		// TODO implement me
		return credits;	
	}
	
	public int getEcts() {
		// TODO implement me
		return ects;	
	}
	
	@Override
	public String toString() {
		return String.format("| Name: %30s| Cypher: %15s| Credits: %5s| ECTS: %5s| Faculty: %5s|", name, cypher, credits, ects, faculty);
	}
	
	public Object[] extractData() {
		return new Object[] {name, cypher, credits, ects, faculty,(teacher != null) ? teacher.getFirstname() : "Not assigned", getStudentsCount() + "/" + maxStudentsCount, description};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

	public Object clone() throws CloneNotSupportedException {
		Course clone = (Course) super.clone();
		clone.courseFiles = new Vector<CourseFile>(courseFiles);
		clone.prerequisites = new Vector<Course>(prerequisites);
		clone.teacher = (Teacher) teacher.clone();
		return clone;	
	}
	
	public int compareTo(Object parameter) {
		// TODO implement me
		return 0;	
	}
	
}

