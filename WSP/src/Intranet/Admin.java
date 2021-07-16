package Intranet ;

import java.io.Serializable;

/**
 * 
 * The Admin class represents admins' account in intranet system.
 * This class used for managing users - add/remove/update operations.
 * Can view users log files.
 * @see Employee
 */

public class Admin extends Employee implements Serializable, Comparable<User>
{
	/**
	 * Default Admin constructor
	 */
	public Admin(){
		super();
	}
	/**
	 * Constructor that invokes parent constructor with all fields as parameters
	 * @param firstname
	 * @param lastname
	 * @param password
	 */
	public Admin(String firstname, String lastname, String password) {
		super(firstname, lastname, password);
	}
	/**
	 * Adds new user using Factory to the DataBase
	 * @param userType specifier what user we want to add
	 */
	public void addUser(String userType) {
		User newUser = Factory.getUser(userType);
		DataBase.getInstance().addUser(newUser);
	}
	/**
	 * Removes existing user from the DataBase
	 * @param user a user we want to remove
	 */
	public boolean removeUser(User user) {
		return DataBase.getInstance().removeUser(user);
	}
	/**
	 * Updates existing user in DataBase
	 * @param user a user we want to update
	 */
	public void updateUser(User user) {
		User updatedUser = Factory.updateUser(user);
		DataBase.getInstance().updateUser(user, updatedUser);
	}
	
	public int hashCode() {
		return super.hashCode();	
	}
	
	public boolean equals(Object parameter) {
		return super.equals(parameter);	
	}
	
	public String toString() {
		// TODO implement me
		return super.toString();	
	}
	
	@Override
	public int compareTo(User o) {
		return super.compareTo(o);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}	
}