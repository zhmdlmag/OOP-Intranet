package Intranet ;

import java.io.Serializable;

import java.util.List;

public class Librarian extends Employee implements Serializable, Comparable<User>, Cloneable
{
	public Librarian(){
		super();
	}
	
	public Librarian(String firstname, String lastname, String password) {
		super(firstname, lastname, password);
	}
		
	public void sendBook(Student student, Book book) {
		student.receiveBook(book);
		DataBase.getInstance().getBook(book);
	}
		
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	@Override
	public int compareTo(User o) {
		return super.compareTo(o);
	}

	public int hashCode() {
		return super.hashCode();	
	}
	
	public boolean equals(Object parameter) {
		return super.equals(parameter);	
	}
}

