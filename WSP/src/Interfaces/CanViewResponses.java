package Interfaces;

import java.util.Vector;

import Intranet.DataBase;
import Intranet.IOUtils;
import Intranet.Response;
import Intranet.TableBuilder;
import Intranet.User;

public interface CanViewResponses {
	public default void viewResponses(User user) {
		Vector<Response> responses = DataBase.getInstance().getResponses(user);
		TableBuilder.buildTable(TableBuilder.responseHeaders, responses, "Responses");
		IOUtils.waitKey();
	}
}