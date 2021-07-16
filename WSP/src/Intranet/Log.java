package Intranet;
import java.io.Serializable;
import java.util.Date;

import Enums.Action;
import Interfaces.Extractable;

public class Log implements Serializable, Extractable
{
	private User user;
	
	private Action action;
	
	private Date date;
	
	public Log() {
		
	}

	public Log(Action action, User user) {
		this.user = user;
		this.action = action;
		this.date = new Date();
	}

	public User getUser() {
		return user;	
	}
	
	public Action getAction() {
		return action;	
	}
	
	public Date getDate() {
		return date;	
	}
	
	public String toString() {
		// TODO implement me
		return "";	
	}
	
	@Override
	public Object[] extractData() {
		return new Object[] {user, action, date};
	}
	
}