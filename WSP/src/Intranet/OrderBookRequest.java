package Intranet ;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Enums.RequestType;
import Enums.ResponseStatus;
import Interfaces.Extractable;

public class OrderBookRequest extends Request implements Serializable, Extractable
{
	private Book book;

	public OrderBookRequest(){

	}

	public OrderBookRequest(User from, Book book){
		super(from);
		setRequestType(RequestType.ORDER_BOOK);
		this.book = book;
	}
	
	public Book getBook() {
		return book;	
	}

	@Override
	public void callback() {
		Student student = (Student) isFrom();
		Librarian librarian = (Librarian) isTo();
		ResponseStatus responseStatus = getResponseStatus();
		if (responseStatus == ResponseStatus.APPROVED) {
			IOUtils.writer.println(String.format("Book %s sended to student %s %s", book.getName(), student.getFirstname(), student.getLastname()));
			librarian.sendBook(student, book);
		}
		DataBase.getInstance().removeRequest(this);
		DataBase.getInstance().addResponse(new Response(this, responseStatus));
	}

	@Override
	public ResponseStatus getResponseStatus() {
		Student student = (Student) isFrom();
		if (!DataBase.getInstance().hasBook(book)) {
			IOUtils.writer.println(String.format("Book %s not found!", book.getName() ));
			return ResponseStatus.DECLINED;
		}
		IOUtils.writer.println(String.format("Book found in storage!", book.getName() ));
		return Factory.getreResponseStatus();
	}
	
}