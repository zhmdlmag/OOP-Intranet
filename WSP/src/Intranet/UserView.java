package Intranet;

public abstract class UserView {
	User user = null;
	
	public UserView() {
		// TODO Auto-generated constructor stub
	}
	
	public UserView(User user) {
		this.user = user;
	}
	
	public abstract void run();
}