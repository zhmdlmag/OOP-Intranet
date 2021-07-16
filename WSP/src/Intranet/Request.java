package Intranet ;

import Enums.RequestType;
import Enums.ResponseStatus;
import Interfaces.Extractable;

/**
 * The Request class used for getting response from some user,
 * it will be send to the DataBase and after the observer
 * can get it and decide to decline or approve this request.
 * Also this class implements Extractable interface,
 * so it can be passed to the TableBuilder to make
 * the table with specified fields.
 * @see Extractable
 */
public abstract class Request extends Observable implements Extractable
{	
	/**
	 * Default Request constructor
	 */
	public Request() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor that invokes parent constructor with all fields as parameters
	 * @param from
	 * @param to
	 */
	public Request(User from, User to) {
		super(from, to);
	}
	/**
	 * Constructor that invokes parent constructor with user from as parameter
	 * @param from
	 */
	public Request(User from) {
		super(from);
	}
	/**
	 * Extracts all specified fields as an Object array
	 */
	@Override
	public Object[] extractData() {
		return new Object[] {getRequestType(), isFrom(), (isTo() == null) ? "Undefined" : isTo(), getDate()};
	}
	
	/**
	 * Sends response to the database
	 */
	public abstract void callback();
	/**
	 * Returns response status by applying checking processes
	 * @return response status for request
	 */
	public abstract ResponseStatus getResponseStatus();
}