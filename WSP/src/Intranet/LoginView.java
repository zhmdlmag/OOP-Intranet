package Intranet;

import Exceptions.UserNotFoundException;

public class LoginView {
	private static String[] loginViewOptions = new String[] { "Options", "Student", "Teacher", "Manager", "Admin", "Librarian", "Exit" };
	
	public LoginView() {
		// TODO Auto-generated constructor stub
	}
	
	public User run() throws UserNotFoundException {
		IOUtils.writer.println("Welcome to KBTU intranet");

		TableBuilder.buildOptionsTable(loginViewOptions);
		
		String option = IOUtils.readArray(loginViewOptions, "option");
		
		if (option.equals("Exit")) {
			DataBase.getInstance().save();
			System.exit(0);
		}
		
		String login = IOUtils.readString("login");
		
		String password = IOUtils.readString("password");
		
		User user = DataBase.getInstance().login(option, login, password);
		
		if (user == null) {
			throw new UserNotFoundException("User with given login and password not found!");
		}
		
		return user;
	}
}
