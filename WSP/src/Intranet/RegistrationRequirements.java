package Intranet;

import java.io.Serializable;
import java.util.Vector;

public class RegistrationRequirements implements Serializable {
	private Vector<Course> requiredCourses = new Vector<>();
	
	private int maximumCredits = 21;
	
	private int maxFreeElectivesCount = 2;
	
	private int maxMajorElectivesCount = 2;
	
	public RegistrationRequirements() {

	}
	
	public RegistrationRequirements(Vector<Course> requiredCourses, int maximumCredits, int maxFreeElectivesCount, int maxMajorElectivesCount) {
		this.requiredCourses = requiredCourses;
		this.maximumCredits = maximumCredits;
		this.maxFreeElectivesCount = maxFreeElectivesCount;
		this.maxMajorElectivesCount = maxMajorElectivesCount;
	}
	
	public Vector<Course> getRequiredCourses() {
		return requiredCourses;
	}
	
	public int getMaxFreeElectivesCount() {
		return maxFreeElectivesCount;
	}
	
	public int getMaximumCredits() {
		return maximumCredits;
	}
	
	public int getMaxMajorElectivesCount() {
		return maxMajorElectivesCount;
	}
}
