package Intranet;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Enums.Action;

public abstract class EmployeeView extends UserView {
	
	private static String[] viewCoursesOptions = new String[] {"Options", "Send new message", "Delete message", "Reply to message", "Go back"};
	
	private Employee employee = null;
	
	public EmployeeView() {

	}
	
	public EmployeeView(User user) {
		employee = (Employee) user;
	}
	
	public void viewMessages() {
		Vector<Message> messages = DataBase.getInstance().getMessages(employee);
		TableBuilder.buildTable(TableBuilder.messageHeaders, messages);
	}
	
	public final void manageMail() {
		boolean running = true;
		while (running) {
			viewMessages();
			
			TableBuilder.buildOptionsTable(viewCoursesOptions);
			
			String option = IOUtils.readArray(viewCoursesOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "Send new message" -> sendMessage();
				case "Delete message" -> removeMessage();
				case "Reply to message" -> replyToMessage();
				case "Go back" -> running = false;
			}
		}
	}
	
	public void sendMessage() {
		Vector<Employee> employees = DataBase.getInstance().getEmployees();
		viewEmployees(employees);
		
		Employee to = IOUtils.read(employees, "employee");
		
		if (to != null) employee.sendMessage(to);

		DataBase.getInstance().addLogs(new Log(Action.SEND_MESSAGE, employee));
	}
	
	public void removeMessage() {
		Vector<Message> messages = DataBase.getInstance().getMessages(employee);
		Message message = IOUtils.read(messages, "message");
		IOUtils.remove("Message", employee.removeMessage(message));
	}
	
	public void replyToMessage() {
		Vector<Message> messages = DataBase.getInstance().getMessages(employee);
		Message message = IOUtils.read(messages, "message");
		Employee to = message.isFrom();
		
		if (to != null) employee.sendMessage(to);
		
		DataBase.getInstance().addLogs(new Log(Action.SEND_MESSAGE, employee));
	}
	
	public void viewEmployees(Vector<Employee> employees) {
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(TableBuilder.userHeaders);
		for (Employee employee : employees) {
			tableList.add(employee.extractUserFields());
		}
		TableBuilder.buildTable(tableList, "Employees");
	}
}