package Intranet;

import java.rmi.server.LogStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Enums.Action;
import Enums.State;

public class AdminView extends EmployeeView {
	
	private static String[] adminViewOptions = new String[] { "Options", "Manage users", "View user logs", "Check mail", "Logout" };

	private static String[] manageUsersOptions = new String[] { "Options", "Add new user", "Remove user", "Update user", "Go back" };
	
	private static String[] addUserOptions = new String[] { "Users", "Student", "Teacher", "Manager", "Admin", "Librarian" };

	private static String[] viewLogsOptions = new String[] { "Options", "View all logs", "View user's logs", "Go back" };
	
	private Admin admin = null;
	
	public AdminView() {

	}
	
	public AdminView(User user) {
		super(user);
		this.admin = (Admin) user;
	}
	
	public void run() {
		boolean running = true;
		while (running) {
			TableBuilder.buildOptionsTable(adminViewOptions);
			
			String option = IOUtils.readArray(adminViewOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Manage users" -> manageUsers();
				case "Check mail" -> manageMail();
				case "View user logs" -> viewLogs();
				case "Logout" -> running = false;
			}
		}
	}
	
	public void manageUsers() {
		boolean running = true;
		while (running) {
			viewUsers();
			
			TableBuilder.buildOptionsTable(manageUsersOptions);
			
			String option = IOUtils.readArray(manageUsersOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Add new user" -> addUser();
				case "Remove user" -> removeUser();
				case "Update user" -> updateUser();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewLogs() {
		boolean running = true;
		while (running) {
			TableBuilder.buildOptionsTable(viewLogsOptions);
			
			String option = IOUtils.readArray(viewLogsOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View all logs" -> viewAllLogs();
				case "View user's logs" -> viewUsersLogs();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void viewAllLogs() {
		TableBuilder.buildTable(TableBuilder.logHeaders, DataBase.getInstance().getLogs(), "All users logs");
		IOUtils.waitKey();
	}
	
	public void viewUsersLogs() {
		viewUsers();
		User user = IOUtils.read(DataBase.getInstance().getUsers(), "user to see logs");

		TableBuilder.buildTable(TableBuilder.logHeaders, DataBase.getInstance().getLogs(user), String.format("User $s %s logs", user.getFirstname(), user.getLastname()));
		IOUtils.waitKey();
	}
	
	public void addUser() {		
		TableBuilder.buildOptionsTable(addUserOptions);
		
		String option = IOUtils.readArray(addUserOptions, "user to create");
		
		admin.addUser(option);
		DataBase.getInstance().addLogs(new Log(Action.MANAGE_USERS, admin));
	}
	
	public void removeUser() {		
		User user = IOUtils.read(DataBase.getInstance().getUsers(), "user to remove");
		if (user.equals(admin)) return;
		IOUtils.remove("User", admin.removeUser(user));
		DataBase.getInstance().addLogs(new Log(Action.MANAGE_USERS, admin));
	}
	
	public void updateUser() {
		User user = IOUtils.read(DataBase.getInstance().getUsers(), "user to update");
		
		if (user != null) admin.updateUser(user);
		DataBase.getInstance().addLogs(new Log(Action.MANAGE_USERS, admin));
	}
	
	public void viewUsers() {
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(TableBuilder.userHeaders);
		Vector<User> users = DataBase.getInstance().getUsers();
		
		for (int i = 0; i < users.size(); ++i) {
			tableList.add(users.get(i).extractUserFields());
		}
		TableBuilder.buildTable(tableList, "Users");
	}
}
