package Intranet;

import Enums.Action;

public class Application {
	public UserView currentView = null;
	
	public User currentUser = null;

	public void run() {
		try {
			LoginView loginView = new LoginView();
			currentUser = loginView.run();
			if (currentUser == null) return;
			
			if (currentUser instanceof Student) {
				currentView = new StudentView(currentUser);
			} else if (currentUser instanceof Teacher) {
				currentView = new TeacherView(currentUser);
			} else if (currentUser instanceof Manager) {
				currentView = new ManagerView(currentUser);
			} else if (currentUser instanceof Admin) {
				currentView = new AdminView(currentUser);
			} else if (currentUser instanceof Librarian) {
				currentView = new LibrarianView(currentUser);
			}
			currentView.run();
			DataBase.getInstance().addLogs(new Log(Action.LOGIN_TO_ACCOUNT, currentUser));	
		} catch (Exception e) {
			IOUtils.writer.println(e.getMessage());
		}
	}
}