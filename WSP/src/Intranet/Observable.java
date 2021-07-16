package Intranet;

import java.io.Serializable;
import java.util.Date;

import Enums.RequestType;

public abstract class Observable implements Serializable {
	private User from;
	
	private User to;
	
	private Date date;
	
	private RequestType requestType;
	
	public Observable() {
		// TODO Auto-generated constructor stub
	}
	
	public Observable(User from){
		this.from = from;
		this.date = new Date();
	}
	
	public Observable(User from, User to){
		this.from = from;
		this.to = to;
		this.date = new Date();
	}
	
	public User isFrom() {
		return from;
	}
	
	public void setFrom(User from) {
		this.from = from;
	}
	
	public User isTo() {
		return to;
	}
	
	public void setTo(User to) {
		this.to = to;
	}
	
	public Date getDate() {
		return date;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}
	
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
}
