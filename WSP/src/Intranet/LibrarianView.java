package Intranet;
import Interfaces.CanViewRequests;

public class LibrarianView extends EmployeeView implements CanViewRequests {
	private static String[] librarianViewOptions = new String[] {"Options", "View students orders", "Check mail", "Logout"};
	
	Librarian librarian = null;
	
	public LibrarianView() {

	}
	
	public LibrarianView(User user) {
		super(user);
		this.librarian = (Librarian) user;
	}

	public void run() {
		boolean running = true;
		
		while (running) {
			TableBuilder.buildOptionsTable(librarianViewOptions);
			
			String option = IOUtils.readArray(librarianViewOptions, "option");
			
			if (option == null) continue;
			
			switch (option) {
				case "View students orders" -> viewStudentsOrders();
				case "Check mail" -> manageMail();
				case "Logout" -> running = false;
			}
		}
	}
	
	public void viewStudentsOrders() {
		viewRequests(librarian);
		Request request = IOUtils.read(DataBase.getInstance().getRequests(librarian), "request");
		if (request != null) {
			request.setTo(librarian);
			request.callback();
		}
	}
}