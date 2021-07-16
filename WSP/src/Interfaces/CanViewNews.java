package Interfaces;

import java.util.Vector;
import Intranet.DataBase;
import Intranet.News;
import Intranet.TableBuilder;

public interface CanViewNews {	
	public default void viewNews() {
		Vector<News> news = DataBase.getInstance().getNews();
		TableBuilder.buildTable(TableBuilder.newsHeaders, news, "News for last day");
	}
}