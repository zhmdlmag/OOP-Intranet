package Intranet ;

import java.io.Serializable;

public abstract class User implements Serializable, Comparable<User>, Cloneable
{
	private String firstname;
	
	private String lastname;
	
	private String login;
	
	private String password;
	
	public User() {
		
	}

	public User(String firstname, String lastname, String password){
		this.firstname = firstname;
		this.lastname = lastname;
		this.setLogin();
		this.password = password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean setPassword(String oldPassword, String newPassword) {
		if (password.equals(oldPassword)) {
			password = newPassword;
			return true;
		}
		return false;	
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLogin() {
		this.login = Factory.genLogin(firstname, lastname);
	}
	
	public String getLoginWithDomain() {
		return String.format("%s@kbtu.kz", login);
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void viewNews() {
		// TODO implement me	
	}
	
	public void takeSurvey() {
		// TODO implement me	
	}

	@Override
	public String toString() {
		return String.format("%s %s", firstname, lastname);
	}
	
	public Object[] extractUserFields() {
		return new Object[] {firstname, lastname, getLoginWithDomain(), password};
	}
	
	@Override
	public int compareTo(User o) {
		if (firstname.compareTo(o.firstname) != 0) return firstname.compareTo(o.firstname);
		if (lastname.compareTo(o.lastname) != 0) return lastname.compareTo(o.lastname);
		if (login.compareTo(o.login) != 0) return login.compareTo(o.login);
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}