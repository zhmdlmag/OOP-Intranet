package Intranet ;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Interfaces.Extractable;

public class CourseFile implements Serializable, Cloneable, Extractable {
	
	private String name;
	
	private String content;
	
	private Date date;

	public CourseFile(){
		
	}	
	public CourseFile(String name, String content) {
		this.name = name;
		this.content = content;
		this.date = new Date();
	}
	
	public Object[] extractData() {
		return new Object[] {name, date, content};
	}
	
	public String getName() {
		return name;	
	}
	
	public String getContent() {
		// TODO implement me
		return content;	
	}
	
	public Date getDate() {
		// TODO implement me
		return date;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		// TODO implement me
		return "";	
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		CourseFile clone = (CourseFile) super.clone();
		clone.date = (Date) date.clone();
		return clone;
	}
	
}

