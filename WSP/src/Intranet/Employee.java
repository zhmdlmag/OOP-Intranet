package Intranet ;

import java.io.Serializable;
import java.util.Vector;

public class Employee extends User implements Serializable, Comparable<User>, Cloneable
{	
	private double salary;

	public Employee(){
		super();
	}
	
	public Employee(String firstname, String lastname, String password) {
		super(firstname, lastname, password);
	}

	public void sendMessage(Employee employee) {
		Message message = Factory.getMessage(this, employee);
		DataBase.getInstance().addMessage(message);
	}	
	
	public boolean removeMessage(Message message) {
		return DataBase.getInstance().removeMessage(message);
	}
	
	Vector<Message> getMessages() {
		return DataBase.getInstance().getMessages(this);
	}

	public double getSalary() {
		return salary;	
	}	

	public String toString() {
		return super.toString();	
	}

	public int compareTo(User o) {
		return super.compareTo(o);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}