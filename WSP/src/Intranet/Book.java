package Intranet ;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Interfaces.Extractable;

public class Book implements Serializable, Extractable
{	
	private String id;
		
	private String name;
		
	private String content;
		
	private String author;
	
	public Book(){
	
	}
	
	public Book(String name, String content, String author){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.content = content;
		this.author = author;
	}
	
	public String getId() {
		return id;	
	}
		
	public String getName() {
		return name;	
	}
		
	public String getContent() {
		return content;	
	}
		
	public String getAuthor() {
		return author;	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return String.format("[%s, %s, %s]", name, author, content);
	}
	
	@Override
	public Object[] extractData() {
		return new Object[] {name, author, content, DataBase.getInstance().getBookCount(this)};
	}
		
	public Object clone() throws CloneNotSupportedException {
		return super.clone();	
	}
		
	public int compareTo(Object parameter) {
		// TODO implement me
		return 0;	
	}
	
}

