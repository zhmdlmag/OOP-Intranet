package Intranet ;
import java.io.Serializable;
import java.util.Date;

import Enums.NewsType;
import Interfaces.Extractable;

public class News implements Serializable, Extractable
{	
	private String title;
		
	private NewsType type;
		
	private Date date;
		
	private String info;
	
	public News() {

	}
	
	public News(String title, NewsType type, String info) {
		this.title = title;
		this.type = type;
		this.date = new Date();
		this.info = info;
	}
	
	public void setTitle(String title) {
		this.title = title;	
	}
	
	public String getTitle() {
		return title;
	}
		
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}
		
	public void setType(NewsType type) {
		this.type = type;
	}
	
	public NewsType getType() {
		return type;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		News other = (News) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String toString() {
		return String.format("[%s, %s, %s, %s]", title, type, date, info);
	}
	
	@Override
	public Object[] extractData() {
		return new Object[] {title, type, date, info};
	}
		
	public Object clone() throws CloneNotSupportedException {
		News clone = (News) super.clone();
		clone.date = (Date) date.clone();
		return clone;	
	}
		
	public int compareTo(Object parameter) {
		// TODO implement me
		return 0;	
	}
	
}