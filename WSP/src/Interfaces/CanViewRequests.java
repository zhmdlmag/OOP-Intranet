package Interfaces;

import java.util.Vector;

import Intranet.DataBase;
import Intranet.Request;
import Intranet.TableBuilder;
import Intranet.User;

public interface CanViewRequests {
	public default void viewRequests(User user) {
		Vector<Request> requests = DataBase.getInstance().getRequests(user);
		TableBuilder.buildTable(TableBuilder.requestHeaders, requests, "Requests");
	}
}