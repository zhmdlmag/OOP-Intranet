package Intranet ;
import java.io.Serializable;
import java.util.Date;

import Interfaces.Extractable;

public class Message implements Serializable, Extractable
{
	private String title;
	
	private Employee from;
	
	private Employee to;
	
	private String message;
	
	private Date date;

	public Message(){

	}

	public Message(String title, Employee from, Employee to, String message){
		this.title = title;
		this.from = from;
		this.to = to;
		this.message = message;
		this.date = new Date();
	}
	
	@Override
	public Object[] extractData() {
		return new Object[] {title, from.getLogin(), to.getLogin(), message, date};
	}

	public Employee isFrom() {
		return from;	
	}
	
	public Employee isTo() {
		return to;	
	}
	
	public String toString() {
		// TODO implement me
		return "";	
	}
	
}