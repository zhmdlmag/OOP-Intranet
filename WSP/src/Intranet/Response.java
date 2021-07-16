package Intranet;
import Enums.RequestType;
import Enums.ResponseStatus;
import Interfaces.Extractable;

public class Response extends Observable implements Extractable {
	
	private ResponseStatus responseStatus;

	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	public Response(User from, User to, RequestType requestType, ResponseStatus responseStatus) {
		super(from, to);
		setRequestType(requestType);
		this.responseStatus = responseStatus;
	}
	
	public Response(Request request, ResponseStatus responseStatus) {
		this(request.isTo(), request.isFrom(), request.getRequestType(), responseStatus);
	}
	
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	
	@Override
	public Object[] extractData() {
		return new Object[] {getRequestType(), isFrom(), isTo(), getDate(), responseStatus};
	}
}