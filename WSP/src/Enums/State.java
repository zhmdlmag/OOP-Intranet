package Enums;

import java.util.HashMap;

public enum State {
	LOGIN_MENU("0", "null"), 
	STUDENT_VIEW("1", "Student"), 
	TEACHER_VIEW("2", "Teacher"), 
	MANAGER_VIEW("3", "Manager"), 
	ADMIN_VIEW("4", "Admin"), 
	LIBRARIAN_VIEW("5", "Librarian");
	
	public final String id;
	public final String name;
	public static HashMap<String, State> positionAndState = new HashMap<String, State>();
	
	static {
		positionAndState.put("Student", STUDENT_VIEW);
		positionAndState.put("Teacher", TEACHER_VIEW);
		positionAndState.put("Manager", MANAGER_VIEW);
		positionAndState.put("Admin", ADMIN_VIEW);
		positionAndState.put("Librarian", LIBRARIAN_VIEW);
	}
	
	private State(String id, String name) {
		this.id = id;
		this.name = name;
	}
}